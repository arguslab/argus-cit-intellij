/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.compiler

import java.util.UUID

import com.intellij.compiler.server.BuildManagerListener
import com.intellij.notification.{Notification, NotificationType, Notifications}
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.compiler.{CompileContext, CompileTask, CompilerManager}
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.module.{Module, ModuleManager}
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.{CompilerModuleExtension, ModuleRootManager}
import com.intellij.openapi.ui.Messages

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ServerMediator(project: Project) extends ProjectComponent {

  private def isJawaProject = project.hasJawa
  private val settings = JawaCompileServerSettings.getInstance

  private val connection = project.getMessageBus.connect
  private val serverLauncher = new BuildManagerListener {
    override def beforeBuildProcessStarted(project: Project, uuid: UUID): Unit = {}

    override def buildStarted(project: Project, sessionId: UUID, isAutomake: Boolean): Unit = {
      if (settings.COMPILE_SERVER_ENABLED && isJawaProject) {
        invokeAndWait {
          CompileServerManager.instance(project).configureWidget()
        }

        if (CompileServerLauncher.needRestart(project)) {
          CompileServerLauncher.instance.stop()
        }

        if (!CompileServerLauncher.instance.running) {
          invokeAndWait {
            CompileServerLauncher.instance.tryToStart(project)
          }
        }
      }
    }

    override def buildFinished(project: Project, sessionId: UUID, isAutomake: Boolean): Unit = {}
  }

  connection.subscribe(BuildManagerListener.TOPIC, serverLauncher)

  private val checkSettingsTask = new CompileTask {
    def execute(context: CompileContext): Boolean = {
      if (isJawaProject) {
        if (!checkCompilationSettings()) false
        else true
      }
      else true
    }
  }

  private val checkCompileServerDottyTask = new CompileTask {
    override def execute(context: CompileContext): Boolean = true
  }

  CompilerManager.getInstance(project).addBeforeTask(checkSettingsTask)
  CompilerManager.getInstance(project).addBeforeTask(checkCompileServerDottyTask)

  private def checkCompilationSettings(): Boolean = {
    def hasClashes(module: Module) = module.hasJawa && {
      val extension = CompilerModuleExtension.getInstance(module)
      val production = extension.getCompilerOutputUrl
      val test = extension.getCompilerOutputUrlForTests
      production == test
    }
    val modulesWithClashes = ModuleManager.getInstance(project).getModules.toSeq.filter(hasClashes)

    var result = true

    if (modulesWithClashes.nonEmpty) {
      invokeAndWait {
        val choice =
          if (!ApplicationManager.getApplication.isUnitTestMode) {
            Messages.showYesNoDialog(project,
              "Production and test output paths are shared in: " + modulesWithClashes.map(_.getName).mkString(" "),
              "Shared compile output paths in Jawa module(s)",
              "Split output path(s) automatically", "Cancel compilation", Messages.getErrorIcon)
          }
          else Messages.YES

        val splitAutomatically = choice == Messages.YES

        if (splitAutomatically) {
          inWriteAction {
            modulesWithClashes.foreach { module =>
              val model = ModuleRootManager.getInstance(module).getModifiableModel
              val extension = model.getModuleExtension(classOf[CompilerModuleExtension])

              val outputUrlParts = extension.getCompilerOutputUrl match {
                case null => Seq.empty
                case url => url.split("/").toSeq
              }
              val nameForTests = if (outputUrlParts.lastOption.contains("classes")) "test-classes" else "test"

              extension.inheritCompilerOutputPath(false)
              extension.setCompilerOutputPathForTests((outputUrlParts.dropRight(1) :+ nameForTests).mkString("/"))

              model.commit()
            }

            project.save()
          }
        }

        result = splitAutomatically
      }
    }

    result
  }

  def getComponentName: String = getClass.getSimpleName

  def initComponent() {}

  def disposeComponent() {}

  def projectOpened() {}

  def projectClosed() {}
}