/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa
package remote

import java.io.PrintStream
import java.util.{Timer, TimerTask}

import com.intellij.util.Base64Converter
import com.martiansoftware.nailgun.NGContext
import org.argus.jawa.core.io.SourceFile
import org.argus.jc.incremental.jawa.local.LocalServer
import org.jetbrains.jps.incremental.messages.BuildMessage.Kind

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
object Main {
  private val Server = new LocalServer()

  private var shutdownTimer: Timer = _

  def nailMain(context: NGContext) {
    cancelShutdown()
    make(context.getArgs.toSeq, context.out, standalone = false)
    resetShutdownTimer(context)
  }
  
  def main(args: Array[String]) {
    make(args, System.out, standalone = true)
  }
  
  private def make(arguments: Seq[String], out: PrintStream, standalone: Boolean) {
    var hasErrors = false

    val client = {
      val eventHandler = (event: Event) => {
        val encode = Base64Converter.encode(event.toBytes)
        out.write((if (standalone && !encode.endsWith("=")) encode + "=" else encode).getBytes)
      }
      new EventGeneratingClient(eventHandler, out.checkError) {
        override def error(text: String, source: SourceFile, line: Option[Long], column: Option[Long]) {
          hasErrors = true
          super.error(text, source, line, column)
        }

        override def message(kind: Kind, text: String, source: SourceFile, line: Option[Long], column: Option[Long]) {
          if (kind == Kind.ERROR) hasErrors = true
          super.message(kind, text, source, line, column)
        }
      }
    }

    val oldOut = System.out
    // Suppress any stdout data, interpret such data as error
    System.setOut(System.err)
    
    try {
      val args = {
        val strings = arguments.map {
          arg => 
            val s = new String(Base64Converter.decode(arg.getBytes), "UTF-8")
            if (s == "#STUB#") "" else s
        }
        Arguments.from(strings)
      }
      
      Server.compile(args.compilerData, args.compilationData, client)
    } catch {
      case e: Throwable => 
        client.trace(e)
    } finally {
      System.setOut(oldOut)
    }
  }

  private def cancelShutdown() = {
    if (shutdownTimer != null) shutdownTimer.cancel()
  }

  private def resetShutdownTimer(context: NGContext) {
    val delay = Option(System.getProperty("shutdown.delay")).map(_.toInt)
    delay.foreach { t =>
      val delayMs = t * 60 * 1000
      val shutdownTask = new TimerTask {
        override def run(): Unit = context.getNGServer.shutdown(true)
      }
      shutdownTimer = new Timer()
      shutdownTimer.schedule(shutdownTask, delayMs)
    }
  }
}
