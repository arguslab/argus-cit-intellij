/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.project

import com.intellij.ProjectTopics
import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.{ModuleRootAdapter, ModuleRootEvent}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaProjectEvents(project: Project) extends AbstractProjectComponent(project) {
  private var listeners: List[JawaProjectListener] = Nil

  private val connection = project.getMessageBus.connect()

  override def projectOpened()= {
    connection.subscribe(ProjectTopics.PROJECT_ROOTS, new ModuleRootAdapter {
      override def rootsChanged(event: ModuleRootEvent) {
        listeners.foreach(_.onJawaProjectChanged())
      }
    })
  }

  override def projectClosed() {
    listeners = Nil
    connection.disconnect()
  }

  def addJawaProjectListener(listener: JawaProjectListener) {
    listeners ::= listener
  }

  def removeJawaProjectListener(listener: JawaProjectListener) {
    listeners = listeners.filterNot(_ == listener)
  }
}

trait JawaProjectListener {
  def onJawaProjectChanged()
}