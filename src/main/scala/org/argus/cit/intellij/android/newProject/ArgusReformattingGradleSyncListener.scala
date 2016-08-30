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

import java.io.File
import java.util

import com.android.tools.idea.gradle.project.NewProjectImportGradleSyncListener
import com.android.tools.idea.templates.TemplateUtils
import com.intellij.ide.startup.StartupManagerEx
import com.intellij.openapi.project.Project

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ArgusReformattingGradleSyncListener(targetFile: util.Collection[File], filesToOpen: util.Collection[File]) extends NewProjectImportGradleSyncListener {
  override def syncSucceeded(project: Project): Unit = {
    val manager = StartupManagerEx.getInstanceEx(project)
    if(manager.postStartupActivityPassed()) reformatRearrangeAndOpen(project)
    else manager.registerPostStartupActivity(new Runnable {
      override def run(): Unit = reformatRearrangeAndOpen(project)
    })
  }
  private def reformatRearrangeAndOpen(project: Project): Unit = {
    TemplateUtils.reformatAndRearrange(project, targetFile)
    TemplateUtils.openEditors(project, filesToOpen, true)
  }
}
