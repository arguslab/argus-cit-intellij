/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

import sbt._

object CitVersions {
  val scalaVersion = "2.11.8"
  val sbtVersion = "0.13.9"
  val ideaVersion = "162.1447.7"
  val sbtStructureVersion = "5.1.2"
  val argusSafVersion = "1.0.3"
  val jawaCompilerVersion = "0.0.2"
}

object Dependencies {
  import CitVersions._

  val sfaLibrary = "com.github.arguslab" %% "saf-library" % argusSafVersion
  val jawaCore = "com.github.arguslab" %% "jawa-core" % argusSafVersion
  val jawaCompiler = "com.github.arguslab" %% "jawa-compiler" % jawaCompilerVersion
}

object DependencyGroups {
  import Dependencies._

  val argus_cit_intellij = Seq(sfaLibrary, jawaCore, jawaCompiler)

}