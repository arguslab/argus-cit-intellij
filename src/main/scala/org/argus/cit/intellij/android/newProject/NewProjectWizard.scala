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

import java.io._
import java.util
import java.util.Properties
import java.util.zip.{ZipEntry, ZipInputStream}
import javax.swing.Icon

import brut.androlib.ApkDecoder
import brut.androlib.res.data.ResTable
import brut.androlib.res.decoder.{AXmlResourceParser, ResAttrDecoder}
import com.android.SdkConstants
import com.android.tools.idea.IdeInfo
import com.android.tools.idea.gradle.project.importing.GradleProjectImporter
import com.android.tools.idea.gradle.util.{GradleUtil, GradleWrapper}
import com.android.tools.idea.npw.{FormFactor, FormFactorUtils}
import com.android.tools.idea.sdk.{AndroidSdks, IdeSdks}
import com.android.tools.idea.startup.AndroidStudioInitializer
import com.android.tools.idea.templates.{TemplateManager, TemplateMetadata, TemplateUtils}
import com.android.tools.idea.wizard.WizardConstants
import com.android.tools.idea.wizard.dynamic.ScopedStateStore.Scope
import com.android.tools.idea.wizard.dynamic.{DynamicWizard, DynamicWizardHost, ScopedStateStore}
import com.android.tools.idea.wizard.template.TemplateWizard
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction.Simple
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.module.Module
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.project.{Project, ProjectManager}
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.pom.java.LanguageLevel
import com.intellij.util.containers.HashSet
import com.intellij.util.ui.UIUtil
import org.argus.cit.intellij.jawa.icons.Icons
import org.jetbrains.android.sdk.AndroidSdkUtils

/**
  * Presents a wizard to the user to create a new project.
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class NewProjectWizard(project: Project, module: Module, host: DynamicWizardHost) extends DynamicWizard(project, module, "New Project", host) {
  import NewProjectWizard._

  private var myProject: Project = _

  setTitle("Create New Project")

  override def init(): Unit = {
    checkSdk()
    addPaths()
    initState()
    super.init()
  }

  protected def checkSdk(): Unit = {
    if(!AndroidSdkUtils.isAndroidSdkAvailable || !TemplateManager.templatesAreValid()) {
      val title = "SDK problem"
      val msg = "<html>Your Android SDK is missing, out of date, or is missing templates.<br>" +
        "You can configure your SDK via <b>Configure | Project Defaults | Project Structure | SDKs</b></html>"
      Messages.showErrorDialog(msg, title)
      throw new IllegalStateException("Android SDK missing")
    }
  }

  protected def addPaths(): Unit = addPath(new ConfigureArgusProjectPath(getDisposable))

  protected def initState(): AnyVal = {
    myState.put(WizardConstants.GRADLE_PLUGIN_VERSION_KEY, SdkConstants.GRADLE_PLUGIN_RECOMMENDED_VERSION)
    myState.put(WizardConstants.GRADLE_VERSION_KEY, SdkConstants.GRADLE_LATEST_VERSION)
    myState.put(WizardConstants.IS_GRADLE_PROJECT_KEY, Boolean.box(true))
    myState.put(WizardConstants.IS_NEW_PROJECT_KEY, Boolean.box(true))
    myState.put(WizardConstants.TARGET_FILES_KEY, new HashSet[File]())
    myState.put(WizardConstants.FILES_TO_OPEN_KEY, new util.ArrayList[File]())
    val mavenUrl = System.getProperty(TemplateWizard.MAVEN_URL_PROPERTY)
    if (mavenUrl != null) myState.put(WizardConstants.MAVEN_URL_KEY, mavenUrl)
    val data = AndroidSdks.getInstance().tryToChooseAndroidSdk()
    if(data != null) myState.put(WizardConstants.SDK_DIR_KEY, data.getLocation.getPath)
  }

  override def getWizardActionDescription: String = String.format("Create %1$s", getState.get(WizardConstants.APPLICATION_NAME_KEY))

  override def performFinishingActions(): Unit = {
    ApplicationManager.getApplication.invokeLater(() => runFinish())
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
    val wrapperPropertiesFilePath = GradleWrapper.getDefaultPropertiesFilePath(rootLocation)
    try {
      GradleWrapper.get(wrapperPropertiesFilePath).updateDistributionUrl(SdkConstants.GRADLE_LATEST_VERSION);
    } catch {
      case e: IOException => LOG.warn("Failed to update Gradle wrapper file", e)
    }
    val projectName = Option(getState.get(WizardConstants.APPLICATION_NAME_KEY)) match {
      case Some(name) => name
      case None => "Unnamed Project"
    }

    var initialLanguageLevel: LanguageLevel = null
    FormFactor.values foreach { factor =>
      val version = getState.get(FormFactorUtils.getLanguageLevelKey(factor))
      if(version != null) {
        val level = LanguageLevel.parse(version.toString)
        if(level != null && (initialLanguageLevel == null || level.isAtLeast(initialLanguageLevel))) {
          initialLanguageLevel = level
        }
      }
    }

    if(!IdeInfo.getInstance.isAndroidStudio) {
      val jdk = IdeSdks.getInstance.getJdk
      if(jdk != null) {
        ApplicationManager.getApplication.runWriteAction(new Runnable {
          override def run(): Unit = ProjectRootManager.getInstance(myProject).setProjectSdk(jdk)
        })
      }
    }
    try {
      val listener = new PostStartupGradleSyncListener(() => {
        val targetFiles = myState.get(WizardConstants.TARGET_FILES_KEY)
        assert(targetFiles != null)
        TemplateUtils.reformatAndRearrange(myProject, targetFiles)
        val filesToOpen = myState.get(WizardConstants.FILES_TO_OPEN_KEY)
        assert(filesToOpen != null)
        TemplateUtils.openEditors(myProject, filesToOpen, true)
      })

      val request = new GradleProjectImporter.Request
      request.setLanguageLevel(initialLanguageLevel).setProject(myProject)
      projectImporter.importProject(projectName, rootLocation, request, listener)
      createArgusCitProperties()
    } catch {
      case e: IOException =>
        Messages.showErrorDialog(e.getMessage, ERROR_MSG_TITLE)
        LOG.error(e)
      case e: ConfigurationException =>
        Messages.showErrorDialog(e.getMessage, ERROR_MSG_TITLE)
        LOG.error(e)
    }
  }

  private def createArgusCitProperties() = {
    val location = myState.get(WizardConstants.PROJECT_LOCATION_KEY)
    val moduleLocation = myState.get(MODULE_LOCATION_KEY)
    val apkPath = myState.get(NewProjectWizard.APK_LOCATION_KEY)
    val outputOffset = myState.get(NewProjectWizard.MANIFEST_DIR_KEY)
    val src = myState.get(NewProjectWizard.SRC_DIR_KEY)
    val properties = new Properties()
    properties.put("apk.path", apkPath)
    properties.put("output.path", moduleLocation + File.separator + outputOffset)
    if(src.startsWith(outputOffset)) properties.put("output.src", src.replaceFirst(outputOffset + File.separator, ""))
    else properties.put("output.src", src)
    val propFile = new File(location, "argus_cit.properties")
    val writer = new FileWriter(propFile)
    try {
      properties.store(writer, this.getClass.getName)
    } finally {
      writer.close()
    }
  }

  override def getProgressTitle: String = "Creating project..."

  override def doFinishAction(): Unit = if (checkFinish()) {
    try {
      doFinish()
    } catch {
      case e: IOException => LOG.error(e)
    }
  }

  override def doFinish(): Unit = {
    val location = myState.get(WizardConstants.PROJECT_LOCATION_KEY)
    val path = myState.get(NewProjectWizard.APK_LOCATION_KEY)
    assert(location != null && path != null)
    new MyAction(location).execute()
    val moduleLocation = location + File.separator + "app"
    new MyAction(moduleLocation).execute()
    myState.put(MODULE_LOCATION_KEY, moduleLocation)
    myState.put(PROJECT_NAME_KEY, "app")
    val name = new File(path).getName.substring(0, new File(path).getName.lastIndexOf("."))
    myProject = UIUtil.invokeAndWaitIfNeeded(() => ProjectManager.getInstance().createProject(name, location))
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
  final val LOG: Logger = Logger.getInstance(classOf[NewProjectWizard])
  final val APK_LOCATION_KEY: ScopedStateStore.Key[String] = ScopedStateStore.createKey("apkLocation", Scope.WIZARD, classOf[String])
  final val MODULE_LOCATION_KEY: ScopedStateStore.Key[String] = ScopedStateStore.createKey(TemplateMetadata.ATTR_PROJECT_OUT, Scope.WIZARD, classOf[String])
  final val PROJECT_NAME_KEY: ScopedStateStore.Key[String] = ScopedStateStore.createKey("projectName", Scope.WIZARD, classOf[String])
  final val SRC_DIR_KEY: ScopedStateStore.Key[String] = ScopedStateStore.createKey("srcDir", Scope.WIZARD, classOf[String])
  final val MANIFEST_DIR_KEY: ScopedStateStore.Key[String] = ScopedStateStore.createKey("manifestDir", Scope.WIZARD, classOf[String])

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
      case _: EntryFound =>
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