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
import java.util

import com.intellij.openapi.util.io.FileUtil
import org.argus.jawa.core.io.SourceFile
import org.jetbrains.jps.incremental.CompileContext
import org.jetbrains.jps.incremental.ModuleLevelBuilder.OutputConsumer
import org.jetbrains.jps.incremental.messages.BuildMessage.Kind
import org.jetbrains.jps.incremental.messages.{CompilerMessage, FileDeletedEvent, ProgressMessage}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class IdeClient(compilerName: String,
                context: CompileContext,
                modules: Seq[String],
                consumer: OutputConsumer) extends Client {

  private var hasErrors = false

  def message(kind: Kind, text: String, source: SourceFile, line: Option[Long], column: Option[Long]) {
    if (kind == Kind.ERROR) {
      hasErrors = true
    }

    val file = source.file.file
    val name = compilerName

    val sourcePath = file.getPath

    context.getProjectDescriptor.getProject.getName
    val withoutPointer =
        if (line.isDefined && column.isDefined) {
          val lines = text.split('\n')
          lines.filterNot(_.trim == "^").mkString("\n")
        }
        else text
    context.processMessage(new CompilerMessage(name, kind, withoutPointer, sourcePath,
      -1L, -1L, -1L, line.getOrElse(-1L), column.getOrElse(-1L)))
  }

  def trace(exception: Throwable) {
    context.processMessage(new CompilerMessage(compilerName, exception))
  }

  def progress(text: String, done: Option[Float]) {
    val formattedText = if (text.isEmpty) "" else {
      val decapitalizedText = text.charAt(0).toLower.toString + text.substring(1)
      "%s: %s [%s]".format(compilerName, decapitalizedText, modules.mkString(", "))
    }
    context.processMessage(new ProgressMessage(formattedText, done.getOrElse(-1.0F)))
  }

  def debug(text: String) {
    JawaBuilder.Log.info(text)
  }

  def deleted(module: File) {
    val paths = util.Collections.singletonList(FileUtil.toCanonicalPath(module.getPath))
    context.processMessage(new FileDeletedEvent(paths))
  }

  def isCanceled: Boolean = context.getCancelStatus.isCanceled

  def hasReportedErrors: Boolean = hasErrors
}
