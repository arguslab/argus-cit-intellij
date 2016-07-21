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

import java.io.{BufferedReader, File, InputStreamReader, Reader}
import java.util.concurrent.Future

import com.intellij.execution.TaskExecutor
import com.intellij.execution.process._
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.{JavaSdk, ProjectJdkTable}
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.util.io.FileUtil
import com.intellij.util.Consumer
import com.intellij.util.io.BaseDataReader

import _root_.scala.collection.JavaConverters._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class NonServerRunner (project: Project, errorHandler: Option[ErrorHandler] = None) {
  private val SERVER_CLASS_NAME = "org.jetbrains.jps.incremental.scala.remote.Main"

  private def classPath(jdk: JDK) = (jdk.tools +: CompileServerLauncher.compilerJars).map(
    file => FileUtil toCanonicalPath file.getPath).mkString(File.pathSeparator)

  private val jvmParameters = CompileServerLauncher.jvmParameters

  def buildProcess(args: Seq[String], listener: String => Unit): CompilationProcess = {
    val sdk = Option(ProjectRootManager.getInstance(project).getProjectSdk) getOrElse {
      val all = ProjectJdkTable.getInstance.getSdksOfType(JavaSdk.getInstance())

      if (all.isEmpty) {
        error("No JDK available")
        return null
      }

      all.get(0)
    }

    CompileServerLauncher.compilerJars.foreach {
      case p => assert(p.exists(), p.getPath)
    }

    scala.compiler.findJdkByName(sdk.getName) match {
      case Left(msg) =>
        error(msg)
        null
      case Right(jdk) =>
        val commands = ((FileUtil toCanonicalPath jdk.executable.getPath) +: "-cp" +: classPath(jdk) +: jvmParameters :+
          SERVER_CLASS_NAME).++(args)

        val builder = new ProcessBuilder(commands.asJava)

        new CompilationProcess {
          var myProcess: Option[Process] = None
          var myCallbacks: Seq[() => Unit] = Seq.empty

          override def addTerminationCallback(callback: => Unit) {
            myCallbacks = myCallbacks :+ (() => callback)
          }

          override def run() {
            val p = builder.start()
            myProcess = Some(p)

            val reader = new BufferedReader(new InputStreamReader(p.getInputStream))
            new MyBase64StreamReader(reader, listener)

            val processWaitFor = new ProcessWaitFor(p, new TaskExecutor {
              override def executeTask(task: Runnable): Future[_] = BaseOSProcessHandler.ExecutorServiceHolder.submit(task)
            })

            processWaitFor.setTerminationCallback(new Consumer[Integer] {
              override def consume(t: Integer) {
                myCallbacks.foreach(c => c())
              }
            })
          }

          override def stop() {
            myProcess foreach (_.destroy())
            myProcess = None
          }
        }
    }
  }

  private def error(message: String) {
    errorHandler.foreach(_.error(message))
  }

  private class MyBase64StreamReader(private val reader: Reader, listener: String => Unit) extends BaseDataReader(null) {
    start()

    private val charBuffer = new Array[Char](8192)
    private val text = new StringBuilder

    def executeOnPooledThread(runnable: Runnable): Future[_] =
      BaseOSProcessHandler.ExecutorServiceHolder.submit(runnable)

    def onTextAvailable(text: String) {
      try {
        listener(text)
      }
      catch {
        case _: Exception =>
      }
    }

    override def close() {
      reader.close()
    }

    override def readAvailable(): Boolean = {
      var read = false

      while (reader.ready()) {
        val n = reader.read(charBuffer)

        if (n > 0) {
          read = true

          for (i <- 0 until n) {
            charBuffer(i) match {
              case '=' if i == 0 && text.isEmpty =>
              case '=' if i == n - 1 || charBuffer.charAt(i + 1) != '=' =>
                if ( (text.length +1) % 4 == 0 ) text.append('=') else if ( (text.length + 2) % 4 == 0 ) text.append("==")
                onTextAvailable(text.toString())
                text.clear()
              case '\n' if text.nonEmpty && text.startsWith("Listening") =>
                text.clear()
              case c => text.append(c)
            }
          }
        }
      }

      read
    }
  }
}

trait ErrorHandler {
  def error(message: String): Unit
}