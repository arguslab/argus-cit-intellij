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

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.{DumbService, Project}
import com.intellij.openapi.wm.{ToolWindow, ToolWindowFactory}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ApkInfoToolWindowFactory extends ToolWindowFactory {
  override def createToolWindowContent(project: Project, toolWindow: ToolWindow): Unit = {
    DumbService.getInstance(project).runWhenSmart(new Runnable {
      override def run(): Unit = ServiceManager.getService(project, classOf[ApkInfoView]).initToolWindow(toolWindow)
    })
  }
}
