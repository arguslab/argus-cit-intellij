/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.android.infoViewer

import java.io.{File, FileReader}
import java.util.Properties

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.{PersistentStateComponent, State, Storage, StoragePathMacros}
import com.intellij.openapi.progress.{ProgressIndicator, ProgressManager, Task}
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.content.{ContentFactory, ContentManager}
import com.intellij.util.xmlb.annotations.Attribute
import org.argus.amandroid.core.{AndroidGlobalConfig, Apk}
import org.argus.amandroid.core.appInfo.AppInfoCollector
import org.argus.amandroid.core.util.AndroidLibraryAPISummary
import org.argus.cit.intellij.jawa.JawaBundle
import org.argus.jawa.core.{Constants, Global, MsgLevel, PrintReporter}
import org.jetbrains.annotations.NotNull
import org.sireum.util._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
@State(
  name = "ApkInfoView",
  storages = Array(new Storage(StoragePathMacros.WORKSPACE_FILE))
)
class ApkInfoView(@NotNull project: Project) extends PersistentStateComponent[ApkInfoView.State] with Disposable {

  private val myPanels: MList[ApkInfoPanel] = mlistEmpty
  private var myContentManager: ContentManager = _
  private var state = new ApkInfoView.State()

  override def loadState(state: ApkInfoView.State): Unit = this.state = state

  override def getState: ApkInfoView.State = {
    if(this.myContentManager != null) {
      val content = this.myContentManager.getSelectedContent
      this.state.selectedIndex = this.myContentManager.getIndexOfContent(content)
    }
    this.state
  }

  override def dispose(): Unit = {}

  def initToolWindow(@NotNull toolWindow: ToolWindow): Unit = {
    val contentFactory = ContentFactory.SERVICE.getInstance()

    val apkInfoContent = contentFactory.createContent(null, JawaBundle.message("title.apkInfo"), false)
    val apkInfoPanel = new ApkInfoPanel(project, apkInfoContent) {}
    apkInfoContent.setComponent(apkInfoPanel)
    Disposer.register(this, apkInfoPanel)

    val applicationInfoContent = contentFactory.createContent(null, JawaBundle.message("title.applicationInfo"), false)
    val applicationInfoPanel = new ApkInfoPanel(project, applicationInfoContent) {}
    applicationInfoContent.setComponent(applicationInfoPanel)
    Disposer.register(this, applicationInfoPanel)

    val interestingApiStringContent = contentFactory.createContent(null, JawaBundle.message("title.interestApiString"), false)
    val interestingApiStringPanel = new ApkInfoPanel(project, interestingApiStringContent) {}
    interestingApiStringContent.setComponent(interestingApiStringPanel)
    Disposer.register(this, interestingApiStringPanel)

    ProgressManager.getInstance().run(new Task.Backgroundable(project, "Collecting APK Information...") {
      private var apk: Apk = _
      private var global: Global = _
      private var errorMessage: String = _
      private var errorTitle: String = _

      override def run(progressIndicator: ProgressIndicator): Unit = {
        val propFile = new File(project.getBasePath, "argus_cit.properties")
        if(!propFile.exists()) {
          errorMessage = "Unable to show apk info. argus_cit.properties does not exist."
          errorTitle = "argus_cit.properties does not exist"
        } else {
          val reader = new FileReader(propFile)
          val properties = new Properties
          try {
            properties.load(reader)
          } finally {
            reader.close()
          }
          val apkPath = properties.getProperty("apk.path")
          val moduleLocation = properties.getProperty("output.path")
          val src = properties.getProperty("output.src")
          if(!new File(apkPath).exists()) {
            errorMessage = "Unable to show apk info. Apk file " + apkPath + " does not exist."
            errorTitle = "Apk file does not exist"
          } else {
            val apkUri = FileUtil.toUri(apkPath)
            val outputUri = FileUtil.toUri(moduleLocation)
            apk = new Apk(apkUri, outputUri, Set(src))
            val reporter = new PrintReporter(MsgLevel.ERROR)
            global = new Global(apkUri, reporter)
            global.setJavaLib(AndroidGlobalConfig.settings.lib_files)
            global.load(FileUtil.toUri(FileUtil.toFilePath(outputUri) + File.separator + src), Constants.JAWA_FILE_EXT, AndroidLibraryAPISummary)
            AppInfoCollector.collectInfo(apk, global, outputUri)
          }
        }
      }

      override def onSuccess(): Unit = {
        if (project.isDisposed) return
        if (errorMessage != null && myTitle != null) {
          Messages.showWarningDialog(project, errorMessage, errorTitle)
          return
        }
        val presentation = ApkInfoPresentation.prepare(apk, global)
        apkInfoPanel.setText(presentation.apkInfo)
        applicationInfoPanel.setText(presentation.applicationInfo)
        interestingApiStringPanel.setText(presentation.apisAndStrings)
      }
    })

    this.myContentManager = toolWindow.getContentManager
    this.myContentManager.addContent(apkInfoContent)
    this.myContentManager.addContent(applicationInfoContent)
    this.myContentManager.addContent(interestingApiStringContent)

    apkInfoContent.setCloseable(false)
    applicationInfoContent.setCloseable(false)
    interestingApiStringContent.setCloseable(false)

    val content = this.myContentManager.getContent(this.state.selectedIndex)
    this.myContentManager.setSelectedContent({if(content != null) content else apkInfoContent})

    myPanels += apkInfoPanel
    myPanels += applicationInfoPanel
    myPanels += interestingApiStringPanel
  }
}

object ApkInfoView {
  class State {
    @Attribute(value = "selected-index")
    var selectedIndex: Int = 0
  }
}
