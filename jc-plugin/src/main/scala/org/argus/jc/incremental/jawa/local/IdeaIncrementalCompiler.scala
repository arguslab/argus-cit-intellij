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

import org.argus.jawa.compiler.compile.JawaCompiler
import org.argus.jc.incremental.jawa.Client
import org.argus.jc.incremental.jawa.data.CompilationData

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class IdeaIncrementalCompiler extends AbstractCompiler {
  def compile(compilationData: CompilationData, client: Client): Unit = {
    val progress = getProgress(client)
    val reporter = getReporter(client)
    val logger = getLogger(client)

    new JawaCompiler().compile(compilationData.sources.toList, Seq(compilationData.output), reporter, logger, progress)
  }

}