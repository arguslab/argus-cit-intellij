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

import org.argus.jc.incremental.jawa.Client
import org.argus.jc.incremental.jawa.data.CompilerData

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
class CachingFactory(delegate: CompilerFactory, compilersLimit: Int) extends CompilerFactory {
  private val compilerCache = new Cache[CompilerData, Compiler](compilersLimit)

  def createCompiler(compilerData: CompilerData, client: Client): Compiler = {
    compilerCache.getOrUpdate(compilerData) {
      delegate.createCompiler(compilerData, client)
    }
  }
}
