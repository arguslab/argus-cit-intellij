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
package remote

import java.io.File

import com.intellij.openapi.util.io.FileUtil
import org.argus.jc.incremental.jawa.data.{CompilationData, CompilerData}
import org.argus.jc.incremental.jawa.model.CompileOrder

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
case class Arguments(compilerData: CompilerData, compilationData: CompilationData) {
  import Arguments._

  def asStrings: Seq[String] = {
    val (outputs, caches) = compilationData.outputToCacheMap.toSeq.unzip

    val (sourceRoots, outputDirs) = compilationData.outputGroups.unzip

    val javaHomePath = compilerData.javaHome.map(fileToPath)

    Seq(
      optionToString(javaHomePath),
      filesToPaths(compilationData.sources),
      filesToPaths(compilationData.classpath),
      fileToPath(compilationData.output),
      sequenceToString(compilationData.jawaOptions),
      sequenceToString(compilationData.javaOptions),
      compilationData.order.toString,
      fileToPath(compilationData.cacheFile),
      filesToPaths(outputs),
      filesToPaths(caches),
      filesToPaths(sourceRoots),
      filesToPaths(outputDirs)
    )
  }
}

object Arguments {
  private val Delimiter = "\n"

  def from(strings: Seq[String]): Arguments = strings match {
    case Seq(
    StringToOption(javaHomePath),
    PathsToFiles(sources),
    PathsToFiles(classpath),
    PathToFile(output),
    StringToSequence(scalaOptions),
    StringToSequence(javaOptions),
    order,
    PathToFile(cacheFile),
    PathsToFiles(outputs),
    PathsToFiles(caches),
    PathsToFiles(sourceRoots),
    PathsToFiles(outputDirs)) =>

      val javaHome = javaHomePath.map {
        case PathToFile(file) => file
      }

      val compilerData = CompilerData(javaHome)

      val outputToCacheMap = outputs.zip(caches).toMap

      val outputGroups = sourceRoots zip outputDirs

      val compilationData = CompilationData(sources, classpath, output, scalaOptions, javaOptions,
        CompileOrder.withName(order), cacheFile, outputToCacheMap, outputGroups)

      Arguments(compilerData, compilationData)
  }

  private def fileToPath(file: File): String = FileUtil.toCanonicalPath(file.getPath)

  private def filesToPaths(files: Iterable[File]): String = sequenceToString(files.map(fileToPath))

  private def optionToString(s: Option[String]): String = s.getOrElse("")

  private def sequenceToString(strings: Iterable[String]): String = strings.mkString(Delimiter)

  private val PathToFile = extractor[String, File] { path: String =>
    new File(path)
  }

  private val PathsToFiles = extractor[String, Seq[File]] { paths: String =>
    if (paths.isEmpty) Seq.empty else paths.split(Delimiter).map(new File(_)).toSeq
  }

  private val StringToOption = extractor[String, Option[String]] { s: String =>
    if (s.isEmpty) None else Some(s)
  }

  private val StringToSequence = extractor[String, Seq[String]] { s: String =>
    if (s.isEmpty) Seq.empty else s.split(Delimiter).toSeq
  }
}
