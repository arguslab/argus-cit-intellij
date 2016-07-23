/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa.local

import java.io.File

import sbt.compiler.AnalyzingCompiler
import sbt.inc.AnalysisStore

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
class CachingFactory(delegate: CompilerFactory, compilersLimit: Int, analysisLimit: Int, scalacLimit: Int) extends CompilerFactory {
  private val compilerCache = new Cache[CompilerData, Compiler](compilersLimit)

  private val analysisCache = new Cache[File, AnalysisStore](analysisLimit)

  private val jawacCache = new Cache[(SbtData, Option[CompilerJars]), Option[AnalyzingCompiler]](scalacLimit)

  def createCompiler(compilerData: CompilerData, client: Client, fileToStore: File => AnalysisStore): Compiler = {
    val cachingFileToStore = (file: File) => analysisCache.getOrUpdate(file)(fileToStore(file))

    compilerCache.getOrUpdate(compilerData) {
      delegate.createCompiler(compilerData, client, cachingFileToStore)
    }
  }

  def getScalac(sbtData: SbtData, compilerJars: Option[CompilerJars], client: Client): Option[AnalyzingCompiler] = {
    scalacCache.getOrUpdate((sbtData, compilerJars)) {
      delegate.getScalac(sbtData, compilerJars, client)
    }
  }
}
