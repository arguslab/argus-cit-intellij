/*
 * Copyright (c) 2017. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.android.newProject

import com.android.tools.idea.gradle.project.importing.NewProjectImportGradleSyncListener
import com.intellij.ide.startup.StartupManagerEx
import com.intellij.openapi.project.Project

/**
  * Created by fgwei on 8/16/17.
  */
class PostStartupGradleSyncListener(runnable: Runnable) extends NewProjectImportGradleSyncListener {

  override def syncSucceeded(project: Project): Unit = {
    val manager = StartupManagerEx.getInstanceEx(project)
    if(manager.postStartupActivityPassed()) {
      runnable.run()
    } else {
      manager.registerPostStartupActivity(runnable)
    }

  }
}
