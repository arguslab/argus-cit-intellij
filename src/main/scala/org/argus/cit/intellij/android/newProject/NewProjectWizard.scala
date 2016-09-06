/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.android.newProject

import java.io.{File, FileInputStream, IOException, InputStream}
import java.util
import java.util.zip.{ZipEntry, ZipInputStream}
import javax.swing.Icon
import javax.xml.parsers.DocumentBuilderFactory

import brut.androlib.ApkDecoder
import brut.androlib.res.data.ResTable
import brut.androlib.res.decoder.{AXmlResourceParser, ResAttrDecoder}
import com.android.SdkConstants
import com.android.tools.idea.gradle.project.GradleProjectImporter
import com.android.tools.idea.gradle.util.GradleUtil
import com.android.tools.idea.npw.FormFactorUtils
import com.android.tools.idea.npw.FormFactorUtils.FormFactor
import com.android.tools.idea.sdk.IdeSdks
import com.android.tools.idea.startup.AndroidStudioInitializer
import com.android.tools.idea.templates.TemplateManager
import com.android.tools.idea.wizard.WizardConstants
import com.android.tools.idea.wizard.dynamic.ScopedStateStore.Scope
import com.android.tools.idea.wizard.dynamic.{DynamicWizard, DynamicWizardHost, ScopedStateStore}
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction.Simple
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.module.{Module, ModuleManager, StdModuleTypes}
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.project.{Project, ProjectManager}
import com.intellij.openapi.roots.{ModifiableRootModel, ModuleRootManager, ProjectRootManager}
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Computable
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.pom.java.LanguageLevel
import com.intellij.util.containers.HashSet
import com.intellij.util.ui.UIUtil
import org.argus.amandroid.core.parser.{AndroidXMLHandler, AndroidXMLParser}
import org.argus.cit.intellij.jawa.icons.Icons
import org.jetbrains.android.facet.AndroidFacet
import org.jetbrains.android.sdk.AndroidSdkUtils
import org.jetbrains.android.util.AndroidUtils
import org.sireum.util._

/**
  * Presents a wizard to the user to create a new project.
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class NewProjectWizard(project: Project, module: Module, host: DynamicWizardHost) extends DynamicWizard(project, module, "New Project", host) {
  import NewProjectWizard._

  private var myProject: Project = _

  setTitle("Create New Project")

  override def init() = {
    checkSdk()
    addPaths()
    initState()
    super.init()
  }

  protected def checkSdk() = {
    if(!AndroidSdkUtils.isAndroidSdkAvailable || !TemplateManager.templatesAreValid()) {
      val title = "SDK problem"
      val msg = "<html>Your Android SDK is missing, out of date, or is missing templates.<br>" +
        "You can configure your SDK via <b>Configure | Project Defaults | Project Structure | SDKs</b></html>"
      Messages.showErrorDialog(msg, title)
      throw new IllegalStateException("Android SDK missing")
    }
  }

  protected def addPaths() = addPath(new ConfigureArgusProjectPath(getDisposable))

  protected def initState() = {
    myState.put(WizardConstants.GRADLE_PLUGIN_VERSION_KEY, SdkConstants.GRADLE_PLUGIN_RECOMMENDED_VERSION)
    myState.put(WizardConstants.GRADLE_VERSION_KEY, SdkConstants.GRADLE_LATEST_VERSION)
    myState.put(WizardConstants.IS_GRADLE_PROJECT_KEY, Boolean.box(true))
    myState.put(WizardConstants.IS_NEW_PROJECT_KEY, Boolean.box(true))
    myState.put(WizardConstants.TARGET_FILES_KEY, new HashSet[File]())
    myState.put(WizardConstants.FILES_TO_OPEN_KEY, new util.ArrayList[File]())
    val data = AndroidSdkUtils.tryToChooseAndroidSdk()
    if(data != null) myState.put(WizardConstants.SDK_DIR_KEY, data.getLocation.getPath)
  }

  override def getWizardActionDescription: ResourceUri = String.format("Create %1$s", getState.get(WizardConstants.APPLICATION_NAME_KEY))

  override def performFinishingActions(): Unit = {
    ApplicationManager.getApplication.invokeLater(new Runnable {
      override def run(): Unit = runFinish()
    })
  }

  private def runFinish(): Unit = {
    if(ApplicationManager.getApplication.isUnitTestMode) return
    val projectImporter = GradleProjectImporter.getInstance()
    val rootPath = getState.get(WizardConstants.PROJECT_LOCATION_KEY)
    if(rootPath == null) {
      LOG.error("No root path specified for project")
      return
    }
    val rootLocation = new File(rootPath)
    val wrapperPropertiesFilePath = GradleUtil.getGradleWrapperPropertiesFilePath(rootLocation)
    try {
      GradleUtil.updateGradleDistributionUrl(SdkConstants.GRADLE_LATEST_VERSION, wrapperPropertiesFilePath)
    } catch {
      case e: IOException => LOG.warn("Failed to update Gradle wrapper file", e)
    }
    val projectName = Option(getState.get(WizardConstants.APPLICATION_NAME_KEY)) match {
      case Some(name) => name
      case None => "Unnamed Project"
    }

    var initialLanguageLevel: LanguageLevel = null
    val iterator = FormFactor.iterator()
    while(iterator.hasNext) {
      val factor = iterator.next()
      val version = getState.get(FormFactorUtils.getLanguageLevelKey(factor))
      if(version != null) {
        val level = LanguageLevel.parse(version.toString)
        if(level != null && (initialLanguageLevel == null || level.isAtLeast(initialLanguageLevel))) {
          initialLanguageLevel = level
        }
      }
    }

    if(!AndroidStudioInitializer.isAndroidStudio) {
      val jdk = IdeSdks.getJdk()
      if(jdk != null) {
        ApplicationManager.getApplication.runWriteAction(new Runnable {
          override def run(): Unit = ProjectRootManager.getInstance(myProject).setProjectSdk(jdk)
        })
      }
    }
    try {
      val targetFiles = myState.get(WizardConstants.TARGET_FILES_KEY)
      assert(targetFiles != null)
      val filesToOpen = myState.get(WizardConstants.FILES_TO_OPEN_KEY)
      assert(filesToOpen != null)
      val listener = new ArgusReformattingGradleSyncListener(targetFiles, filesToOpen)
      projectImporter.importNewlyCreatedProject(projectName, rootLocation, listener, myProject, initialLanguageLevel)
    } catch {
      case e: IOException =>
        Messages.showErrorDialog(e.getMessage, ERROR_MSG_TITLE)
        LOG.error(e)
      case e: ConfigurationException =>
        Messages.showErrorDialog(e.getMessage, ERROR_MSG_TITLE)
        LOG.error(e)
    }
  }

  override def getProgressTitle: ResourceUri = "Creating project..."

  override def doFinishAction(): Unit = checkFinish() match {
    case true =>
      try {
        doFinish()
      } catch {
        case e: IOException => LOG.error(e)
      }
    case false =>
  }

  override def doFinish(): Unit = {
    val location = myState.get(WizardConstants.PROJECT_LOCATION_KEY)
    val path = myState.get(NewProjectWizard.APK_LOCATION_KEY)
    assert(location != null && path != null)
    new MyAction(location).execute()
    val moduleLocation = location + File.separator + "app"
    new MyAction(moduleLocation).execute()
    myState.put(MODULE_LOCATION_KEY, moduleLocation)
    val name = new File(path).getName.substring(0, new File(path).getName.lastIndexOf("."))
    myProject = UIUtil.invokeAndWaitIfNeeded(new Computable[Project] {
      override def compute(): Project = ProjectManager.getInstance().createProject(name, location)
    })
    val module_vf = getProject.getBaseDir.findFileByRelativePath("app")
    val depModule: Module = ApplicationManager.getApplication.runWriteAction(new Computable[Module]() {
      def compute: Module = {
        val depModule: Module = ModuleManager.getInstance(myProject).newModule(module_vf.getPath + File.separator + module_vf.getName + ".iml", StdModuleTypes.JAVA.getId)
        val model: ModifiableRootModel = ModuleRootManager.getInstance(depModule).getModifiableModel
        model.addContentEntry(module_vf)
        model.commit()
        depModule
      }
    })
    if (AndroidFacet.getInstance(depModule) == null) AndroidUtils.addAndroidFacetInWriteAction(depModule, module_vf, true)
    AndroidSdkUtils.setupAndroidPlatformIfNecessary(depModule, false)
    super.doFinish()
  }

  private class MyAction[T](location: String) extends Simple[T](getProject) {
    override def run(): Unit = {
      VfsUtil.createDirectoryIfMissing(location)
    }
  }

  override def getIcon: Icon = Icons.ARGUS_TITLE

  override def getProject: Project = myProject
}

object NewProjectWizard {
  final def ERROR_MSG_TITLE: String = "Error in New Project Wizard"
  final val LOG = Logger.getInstance(classOf[NewProjectWizard])
  final val APK_LOCATION_KEY: ScopedStateStore.Key[String] = ScopedStateStore.createKey("apkLocation", Scope.WIZARD, classOf[String])
  final val MODULE_LOCATION_KEY: ScopedStateStore.Key[String] = ScopedStateStore.createKey("moduleOut", Scope.WIZARD, classOf[String])

  def loadPackageNameFromManifestFile(apk: File): String = {

    val decoder = new ApkDecoder
    decoder.setApkFile(apk)
    val restable: ResTable = decoder.getResTable


    case class EntryFound() extends RuntimeException
    var packageName = ""
    var archive: ZipInputStream = null
    try {
      archive = new ZipInputStream(new FileInputStream(apk))
      var entry: ZipEntry = null
      entry = archive.getNextEntry
      while (entry != null) {
        val entryName = entry.getName
        if(entryName == "AndroidManifest.xml"){
          packageName = getPackageNameFromManifest(archive, restable)
          throw EntryFound()
        }
        entry = archive.getNextEntry
      }
    } catch {
      case ie: InterruptedException => throw ie
      case ef: EntryFound =>
    } finally {
      if (archive != null)
        archive.close()
    }
    packageName
  }

  protected def getPackageNameFromManifest(manifestIS: InputStream, restable: ResTable): String = {
    var pkg: String = ""

    val parser = new AXmlResourceParser()
    parser.open(manifestIS)
    var typ = parser.next()
    while (typ != 0x00000001) {
      // XmlPullParser.END_DOCUMENT
      typ match {
        case 0x00000000 => // XmlPullParser.START_DOCUMENT
        case 0x00000002 => //XmlPullParser.START_TAG
          val tagName = parser.getName
          if (tagName.equals("manifest")) {
            pkg = getAttributeValue(parser, "package", restable)
          }
        case 0x00000003 => //XmlPullParser.END_TAG
        case 0x00000004 => //XmlPullParser.TEXT
      }
      typ = parser.next()
    }
    pkg
  }

  private def getAttributeValue(parser: AXmlResourceParser, attributeName: String, restable: ResTable): String = {
    val count = parser.getAttributeCount
    for (i <- 0 until count){
      if (parser.getAttributeName(i).equals(attributeName)) {
        val decoder = new ResAttrDecoder
        decoder.setCurrentPackage(restable.getCurrentResPackage)
        parser.setAttrDecoder(decoder)
        return parser.getAttributeValue(i)
      }
    }
    null
  }

}