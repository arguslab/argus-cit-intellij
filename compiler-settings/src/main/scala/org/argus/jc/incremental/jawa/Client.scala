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

import java.io.File

import org.argus.jawa.core.io.{NoSourceFile, SourceFile}
import org.jetbrains.jps.incremental.messages.BuildMessage.Kind

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait Client {
  def message(kind: Kind, text: String, source: SourceFile = NoSourceFile, line: Option[Long] = None, column: Option[Long] = None)

  def error(text: String, source: SourceFile = NoSourceFile, line: Option[Long] = None, column: Option[Long] = None) {
    message(Kind.ERROR, text, source, line, column)
  }

  def warning(text: String, source: SourceFile = NoSourceFile, line: Option[Long] = None, column: Option[Long] = None) {
    message(Kind.WARNING, text, source, line, column)
  }

  def info(text: String, source: SourceFile = NoSourceFile, line: Option[Long] = None, column: Option[Long] = None) {
    message(Kind.INFO, text, source, line, column)
  }

  def trace(exception: Throwable)

  def progress(text: String, done: Option[Float] = None)

  def debug(text: String)

  def generated(source: SourceFile, module: File, name: String)

  def processed(source: SourceFile)

  def deleted(module: File)

  def isCanceled: Boolean

  def compilationEnd() {}
}
