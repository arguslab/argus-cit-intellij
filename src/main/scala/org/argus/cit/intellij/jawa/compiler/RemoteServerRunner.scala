/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.compiler

import java.net.{ConnectException, InetAddress, UnknownHostException}

import com.intellij.openapi.project.Project
import org.argus.jc.incremental.jawa.Client
import org.argus.jc.incremental.jawa.remote.RemoteResourceOwner

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class RemoteServerRunner(project: Project) extends RemoteResourceOwner {
  protected val address = InetAddress.getByName(null)

  protected val port = JawaCompileServerSettings.getInstance().COMPILE_SERVER_PORT

  def buildProcess(arguments: Seq[String], client: Client) = new CompilationProcess {
    val COUNT = 10

    var callbacks: Seq[() => Unit] = Seq.empty

    override def addTerminationCallback(callback: => Unit) {
      this.callbacks = this.callbacks :+ (() => callback)
    }

    override def run() {
      try {
        for (i <- 0 until (COUNT - 1)) {
          try {
            Thread.sleep(i*20)
            send(serverAlias, arguments, client)
            return
          } catch {
            case _: ConnectException => Thread.sleep(100)
          }
        }

        send(serverAlias, arguments, client)
      } catch {
        case e: ConnectException =>
          val message = "Cannot connect to compile server at %s:%s".format(address.toString, port)
          client.error(message)
          client.debug(s"$message\n${e.toString}\n${e.getStackTrace.mkString("\n")}")
        case e: UnknownHostException =>
          val message = "Unknown IP address of compile server host: " + address.toString
          client.error(message)
          client.debug(s"$message\n${e.toString}\n${e.getStackTrace.mkString("\n")}")
      } finally {
        callbacks.foreach(a => a())
      }
    }

    override def stop() {
      CompileServerLauncher.ensureNotRunning(project)
    }
  }
}

class RemoteServerStopper(val port: Int) extends RemoteResourceOwner {
  override protected val address: InetAddress = InetAddress.getByName(null)

  def sendStop(): Unit =
    try {
      val stopCommand = "stop_" + JawaCompileServerSettings.getInstance().COMPILE_SERVER_ID
      send(stopCommand, Seq(s"--nailgun-port $port"), null)
    } catch {
      case _: Exception =>
    }
}