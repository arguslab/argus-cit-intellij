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

import java.io.{File, PrintWriter, StringWriter}

import org.argus.jawa.core.io.SourceFile
import org.jetbrains.jps.incremental.messages.BuildMessage.Kind

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
class EventGeneratingClient(listener: Event => Unit, canceled: => Boolean) extends Client {
  def message(kind: Kind, text: String, source: SourceFile, line: Option[Long], column: Option[Long]) {
    listener(MessageEvent(kind, text, source, line, column))
  }

  def trace(exception: Throwable) {
    val lines = {
      val writer = new StringWriter()
      exception.printStackTrace(new PrintWriter(writer))
      writer.toString.split("\\n")
    }
    listener(TraceEvent(exception.getMessage, lines))
  }

  def progress(text: String, done: Option[Float]) {
    listener(ProgressEvent(text, done))
  }

  def debug(text: String) {
    listener(DebugEvent(text))
  }

  def generated(source: SourceFile, module: File, name: String) {
    listener(GeneratedEvent(source, module, name))
  }

  def deleted(module: File) {
    listener(DeletedEvent(module))
  }

  def isCanceled = canceled

  def processed(source: SourceFile) {
    listener(SourceProcessedEvent(source))
  }

  override def compilationEnd() {
    listener(CompilationEndEvent())
  }
}
