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

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.progress.Task.Backgroundable
import com.intellij.openapi.progress.{ProgressIndicator, ProgressManager, Task}
import com.intellij.openapi.ui.popup.JBPopupFactory

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ShowApkInfoAction extends AnAction {
  override def actionPerformed(anActionEvent: AnActionEvent): Unit = {
//    val dataContext = anActionEvent.getDataContext
//    val project = anActionEvent.getProject
//    val bestPopupLocation = JBPopupFactory.getInstance().guessBestPopupLocation(dataContext)
//    ProgressManager.getInstance().run(new Backgroundable(project, "Collecting information...") {
//      override def run(progressIndicator: ProgressIndicator): Unit = {
//        if(project.isJawa)
//      }
//    })
  }
}
