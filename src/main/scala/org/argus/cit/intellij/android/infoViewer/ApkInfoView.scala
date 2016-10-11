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

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.{PersistentStateComponent, State, Storage, StoragePathMacros}
import com.intellij.openapi.progress.{ProgressIndicator, ProgressManager, Task}
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.content.{ContentFactory, ContentManager}
import com.intellij.util.xmlb.annotations.Attribute
import org.argus.amandroid.core.Apk
import org.argus.cit.intellij.jawa.JawaBundle
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
    val basicInfoContent = contentFactory.createContent(null, JawaBundle.message("title.basicInfo"), false)
    val basicInfoPanel = new ApkInfoPanel(project, basicInfoContent) {}
    basicInfoContent.setComponent(basicInfoPanel)
    Disposer.register(this, basicInfoPanel)

    ProgressManager.getInstance().run(new Task.Backgroundable(project, "Collecting APK Information...") {
//      private var apk: Apk = _
      private var errorMessage: String = _
//      private var errorTitle = _

      override def run(progressIndicator: ProgressIndicator): Unit = {
        errorMessage = project.getBasePath
      }

      override def onSuccess(): Unit = {
        if (project.isDisposed) return
        basicInfoPanel.setText(errorMessage)
      }
    })

    this.myContentManager = toolWindow.getContentManager
    this.myContentManager.addContent(basicInfoContent)

    basicInfoContent.setCloseable(false)

    val content = this.myContentManager.getContent(this.state.selectedIndex)
    this.myContentManager.setSelectedContent({if(content != null) content else basicInfoContent})

    myPanels += basicInfoPanel
  }
}

object ApkInfoView {
  class State {
    @Attribute(value = "selected-index")
    var selectedIndex: Int = 0
  }
}
