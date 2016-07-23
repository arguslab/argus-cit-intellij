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

import org.argus.jc.incremental.jawa.{Client, Server}
import org.argus.jc.incremental.jawa.data.CompilationData
import org.jetbrains.jps.incremental.ModuleLevelBuilder.ExitCode
import sbt.inc.{AnalysisStore, FileBasedStore}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class LocalServer extends Server {
  private var cachedCompilerFactory: Option[CompilerFactory] = None
  private val lock = new Object()

  def compile(compilationData: CompilationData, client: Client): ExitCode = {
    val compiler = lock.synchronized {
      val compilerFactory = compilerFactoryFrom()

      client.progress("Instantiating compiler...")
      compilerFactory.createCompiler(compilerData, client, LocalServer.createAnalysisStore)
    }

    if (!client.isCanceled) {
      compiler.compile(compilationData, client)
    }

    client.compilationEnd()
    ExitCode.OK
  }

  private def compilerFactoryFrom(): CompilerFactory = cachedCompilerFactory.getOrElse {
    val factory = new CachingFactory(new CompilerFactoryImpl(), 5, 5, 5)
    cachedCompilerFactory = Some(factory)
    factory
  }
}

object LocalServer {
  private def createAnalysisStore(cacheFile: File): AnalysisStore = {
    val store = FileBasedStore(cacheFile)
    AnalysisStore.sync(AnalysisStore.cached(store))
  }
}