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

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class CompilerFactoryImpl extends CompilerFactory {
  def createCompiler(compilerData: CompilerData, client: Client, fileToStore: File => AnalysisStore): Compiler = {

    val jawac: Option[JawaBy] = getScalac(sbtData, compilerData.compilerJars, client)

    if (scalac.isDefined) new IdeaIncrementalCompiler(scalac.get)
    else throw new IllegalStateException("Could not create jawac instance")

  }

  def getJawac(compilerJars: Option[CompilerJars], client: Client): Option[AnalyzingCompiler] = {
    getScalaInstance(compilerJars).map { scala =>
      val compiledIntefaceJar = getOrCompileInterfaceJar(sbtData.interfacesHome, sbtData.sourceJar,
        sbtData.interfaceJar, scala, sbtData.javaClassVersion, client)

      IC.newScalaCompiler(scala, compiledIntefaceJar, ClasspathOptions.javac(compiler = false))
    }
  }

}
