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

import com.android.SdkConstants
import com.android.tools.idea.gradle.project.GradleProjectImporter
import com.android.tools.idea.gradle.util.GradleUtil
import com.android.tools.idea.npw.FormFactorUtils
import com.android.tools.idea.npw.FormFactorUtils.FormFactor
import com.android.tools.idea.sdk.IdeSdks
import com.android.tools.idea.startup.AndroidStudioInitializer
import com.android.tools.idea.templates.TemplateManager
import com.android.tools.idea.wizard.WizardConstants
import com.android.tools.idea.wizard.dynamic.{DynamicWizard, DynamicWizardHost}
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction.Simple
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.module.Module
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.project.{Project, ProjectManager}
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Computable
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.pom.java.LanguageLevel
import com.intellij.util.containers.HashSet
import com.intellij.util.ui.UIUtil
import org.jetbrains.android.sdk.AndroidSdkUtils
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
    val projectName = Option(getState.get(WizardConstants.APPLICATION_NAME_KEY)).getOrElse("Unnamed Project")

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
    val name = myState.get(WizardConstants.APPLICATION_NAME_KEY)
    assert(location != null && name != null)
    new MyAction(location).execute()
    myProject = UIUtil.invokeAndWaitIfNeeded(new Computable[Project] {
      override def compute(): Project = ProjectManager.getInstance().createProject(name, location)
    })
    super.doFinish()
  }

  private class MyAction[T](location: String) extends Simple[T](getProject) {
    override def run(): Unit = {
      VfsUtil.createDirectoryIfMissing(location)
    }
  }

  override def getProject: Project = myProject
}

object NewProjectWizard {
  final def ERROR_MSG_TITLE: String = "Error in New Project Wizard"
  final val LOG = Logger.getInstance(classOf[NewProjectWizard])
}