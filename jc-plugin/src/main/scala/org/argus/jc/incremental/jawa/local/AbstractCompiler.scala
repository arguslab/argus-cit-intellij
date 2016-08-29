/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa
package local

import java.io.File

import org.argus.jawa.compiler.compile.CompileProgress
import org.argus.jawa.compiler.log.Severity
import org.argus.jawa.core.{DefaultReporter, Reporter}
import org.argus.jawa.core.io.{Position, SourceFile}
import org.jetbrains.jps.incremental.messages.BuildMessage.Kind
import org.argus.jawa.compiler.log.Logger

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
abstract class AbstractCompiler extends Compiler {

  def getReporter(client: Client): Reporter = new ClientReporter(client)

  def getLogger(client: Client): Logger = new ClientLogger(client)

  def getProgress(client: Client): CompileProgress = new ClientProgress(client)

  private class ClientLogger(val client: Client) extends Logger {
    override def error(msg: String) {
      client.error(msg)
    }

    override def warn(msg: String) {
      client.warning(msg)
    }

    override def info(msg: String) {
      client.progress(msg)
    }

    override def debug(msg: String) {
      val lines = msg.trim.split('\n')
      client.debug("\n\n" + lines.mkString("\n") + "\n")
    }

    def trace(exception: Throwable) {
      client.trace(exception)
    }
  }

  private class ClientProgress(client: Client) extends CompileProgress {
    def generated(source: SourceFile, module: File, name: String) {
      client.progress("Generated " + module.getName)
      client.generated(source, module, name)
    }

    def deleted(module: File) {
      client.deleted(module)
    }

    def startUnit(unitPath: String) {
      val unitName = new File(unitPath).getName
      client.progress("Compile on " + unitName)
    }

    def advance(current: Int, total: Int) = {
      client.progress("", Some(current.toFloat / total.toFloat))
      !client.isCanceled
    }
  }

  private class ClientReporter(client: Client) extends DefaultReporter {

    def log(pos: Position, msg: String, sev: Severity) {
      val kind = sev match {
        case Severity.Info => Kind.INFO
        case Severity.Warn => Kind.WARNING
        case Severity.Error => Kind.ERROR
      }

      val source = pos.source
      val line = (try Some(pos.line) catch {case e: UnsupportedOperationException => None}).map(_.toLong)
      val column = (try Some(pos.column) catch {case e: UnsupportedOperationException => None}).map(_.toLong)

      client.message(kind, msg, source, line, column)
    }
  }

}

