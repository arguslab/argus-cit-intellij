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

import java.io.{File, IOException}
import java.util

import com.android.tools.idea.npw.deprecated.ConfigureAndroidProjectPath
import com.android.tools.idea.sdk.VersionCheck
import com.android.tools.idea.templates.Template
import com.android.tools.idea.templates.recipe.RenderingContext
import com.android.tools.idea.wizard.WizardConstants
import com.android.tools.idea.wizard.dynamic.{DynamicWizardPath, ScopedStateStore}
import com.android.tools.idea.wizard.dynamic.DynamicWizardStepWithHeaderAndDescription.WizardStepHeaderSettings
import com.android.tools.idea.wizard.dynamic.ScopedStateStore.Scope
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.{ProgressIndicator, ProgressManager, Task}
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.SystemInfo
import com.intellij.openapi.vfs.{LocalFileSystem, VfsUtilCore}
import org.argus.amandroid.core.decompile.{ApkDecompiler, DecompileLayout, DecompilerSettings}
import org.jetbrains.android.sdk.AndroidSdkUtils
import org.sireum.util.FileUtil

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ConfigureArgusProjectPath(parentDisposable: Disposable) extends DynamicWizardPath {
  import ConfigureArgusProjectPath._

  override def init() = {
    ConfigureAndroidProjectPath.putSdkDependentParams(myState)
    addStep(new ConfigureArgusProjectStep(parentDisposable))
  }

  override def validate(): Boolean = {
    if(!AndroidSdkUtils.isAndroidSdkAvailable) {
      setErrorHtml("<html>Your Android SDK is missing, out of date, or is missing templates. " +
        "Please ensure you are using SDK version " + VersionCheck.MIN_TOOLS_REV + " or later.<br>" +
        "You can configure your SDK via <b>Configure | Project Defaults | Project Structure | SDKs</b></html>")
      false
    } else true
  }

  override def getPathName: String = "Configure Argus-Cit Project"

  override def canPerformFinishingActions: Boolean = performFinishingOperation(true)

  override def performFinishingActions(): Boolean = {
    try {
      if (!performFinishingOperation(false)) {
        return false
      }
      val project = getProject
      assert(project != null)

      val projectRoot = VfsUtilCore.virtualToIoFile(project.getBaseDir)
      setGradleWrapperExecutable(projectRoot)
      true
    }
    catch {
      case e: IOException =>
        LOG.error(e)
        false
    }
  }

  private def performFinishingOperation(dryRun: Boolean): Boolean = {
    val path = myState.get(NewProjectWizard.APK_LOCATION_KEY)
    val projectLocation = myState.get(WizardConstants.PROJECT_LOCATION_KEY)
    assert(projectLocation != null)
    val projectRoot = new File(projectLocation)
    val moduleRootPath = myState.get(NewProjectWizard.MODULE_LOCATION_KEY)
    assert(moduleRootPath != null)
    val moduleRoot = new File(moduleRootPath)

    if(!dryRun && !decompileAndSetState(moduleRootPath, path)) {
      return false
    }
    this.myState.put(WizardConstants.IS_LIBRARY_KEY, Boolean.box(false))
    this.myState.put(NewProjectWizard.SRC_DIR_KEY, "src/main/jawa")
    this.myState.put(RES_DIR_KEY, "src/main/res")
    this.myState.put(NewProjectWizard.MANIFEST_DIR_KEY, "src/main")

    val project: Project = this.getProject

    assert(project != null)

    val templateRoot: File = getTemplateRootFolder
    if(templateRoot == null) return false
    val template: Template = Template.createFromPath(templateRoot)
    val context = RenderingContext.Builder.newContext(template, project)
      .withCommandName("New Project")
      .withDryRun(dryRun)
      .withShowErrors(true)
      .withOutputRoot(projectRoot)
      .withModuleRoot(moduleRoot)
      .withParams(myState.flatten())
      .withGradleSync(true)
      .intoTargetFiles(myState.get(WizardConstants.TARGET_FILES_KEY))
      .intoOpenFiles(myState.get(WizardConstants.FILES_TO_OPEN_KEY))
      .intoDependencies(myState.get(WizardConstants.DEPENDENCIES_KEY))
      .build()
    template.render(context)
  }

  private def decompileAndSetState(moduleRootPath: String, path: String): Boolean = {
    try {
      ProgressManager.getInstance().run(new Task.Modal(getProject, "Decompiling APK...", false) {
        override def run(progressIndicator: ProgressIndicator): Unit = {
          progressIndicator.setIndeterminate(true)
          val main = moduleRootPath + File.separator + "src" + File.separator + "main"
          val layout = DecompileLayout(FileUtil.toUri(main), createFolder = false, "jawa", createSeparateFolderForDexes = false)
          val settings = DecompilerSettings(None, dexLog = false, debugMode = false, removeSupportGen = true, forceDelete = true, None, layout)
          ApkDecompiler.decompile(FileUtil.toUri(path), settings)
        }
      })
      val filesToOpen: util.ArrayList[File] = new util.ArrayList[File]()
      val manifestFile = new File(moduleRootPath + File.separator + "src" + File.separator + "main", "AndroidManifest.xml")
      filesToOpen.add(manifestFile)
      this.myState.put(WizardConstants.FILES_TO_OPEN_KEY, filesToOpen)
      true
    } catch {
      case e: Exception =>
        setErrorHtml("<html>Your APK cannot be decompiled. Error message: " + e.getMessage + "</html>")
        LOG.error(e)
        false
    }
  }

  def getTemplateRootFolder: File = {
    var pluginsPath = com.intellij.openapi.util.io.FileUtil.toSystemIndependentName(PathManager.getPluginsPath)
    // Release build?
    var root = LocalFileSystem.getInstance().findFileByPath(com.intellij.openapi.util.io.FileUtil.toSystemIndependentName(pluginsPath + "/Argus-CIT/lib/templates/NewArgusProject"))
    if (root == null) {
      pluginsPath = com.intellij.openapi.util.io.FileUtil.toSystemIndependentName(PathManager.getHomePath)
      // Development build?
      root = LocalFileSystem.getInstance().findFileByPath(com.intellij.openapi.util.io.FileUtil.toSystemIndependentName(pluginsPath + "/../../../out/plugin/Argus-CIT/lib/templates/NewArgusProject"))
    }
    if (root != null) {
      VfsUtilCore.virtualToIoFile(root)
    } else null
  }

}

object ConfigureArgusProjectPath {
  final val LOG = Logger.getInstance(classOf[ConfigureArgusProjectPath])

  protected def buildConfigurationHeader = WizardStepHeaderSettings.createTitleOnlyHeader("New Analysis")

  final val RES_DIR_KEY: ScopedStateStore.Key[String] = ScopedStateStore.createKey("resDir", Scope.PATH, classOf[String])

  /**
    * Set the executable bit on the 'gradlew' wrapper script on Mac/Linux
    * On Windows, we use a separate gradlew.bat file which does not need an
    * executable bit.
    *
    */
  def setGradleWrapperExecutable(projectRoot: File) = {
    if (SystemInfo.isUnix) {
      val gradlewFile = new File(projectRoot, "gradlew")
      if (!gradlewFile.isFile) {
        LOG.error("Could not find gradle wrapper. Command line builds may not work properly.")
      }
      else {
        com.intellij.openapi.util.io.FileUtil.setExecutableAttribute(gradlewFile.getPath, true)
      }
    }
  }
}