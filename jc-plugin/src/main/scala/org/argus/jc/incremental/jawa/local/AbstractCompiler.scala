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

import com.google.common.base.Preconditions
import hu.ssh.progressbar.{AbstractProgressBar, Progress, ProgressBar}
import org.argus.jawa.compiler.log.Severity
import org.argus.jawa.core.{DefaultReporter, Reporter}
import org.argus.jawa.core.io.Position
import org.jetbrains.jps.incremental.messages.BuildMessage.Kind
import org.argus.jawa.compiler.log.Logger

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
abstract class AbstractCompiler extends Compiler {

  def getReporter(client: Client): Reporter = new ClientReporter(client)

  def getLogger(client: Client): Logger = new ClientLogger(client)

  def getProgress(client: Client): ProgressBar= new ClientProgress(client)

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

  private class ClientProgress(client: Client, totalSteps: Int) extends AbstractProgressBar(totalSteps) {

    def this(client: Client) = this(client, 30)

    override def updateProgressBar(progress: Progress): Unit = {
      client.progress("", Some(progress.getPercentage))
      !client.isCanceled
    }

    override def finishProgressBar(): Unit = {
    }

    override def withTotalSteps(totalSteps: Int): ProgressBar = {
      Preconditions.checkArgument(totalSteps != 0)
      new ClientProgress(client, totalSteps)
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
      val line = (try Some(pos.line) catch {case _: UnsupportedOperationException => None}).map(_.toLong)
      val column = (try Some(pos.column) catch {case _: UnsupportedOperationException => None}).map(_.toLong)

      client.message(kind, msg, source, line, column)
    }
  }

}

