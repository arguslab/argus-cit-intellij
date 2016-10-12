/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
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
  val ideaVersion = "163.5644.15"
  val sbtStructureVersion = "5.1.2"
  val argusSafVersion = "1.1.2"
  val jawaCompilerVersion = "1.0.4"
}

object Dependencies {
  import CitVersions._

  val scalaLibrary = "org.scala-lang" % "scala-library" % scalaVersion

  val sfaLibrary = "com.github.arguslab" %% "saf-library" % argusSafVersion
  val jawaCore = "com.github.arguslab" %% "jawa-core" % argusSafVersion
  val amandroidCore = "com.github.arguslab" %% "amandroid-core" % argusSafVersion
  val jawaCompiler = "com.github.arguslab" %% "jawa-compiler" % jawaCompilerVersion

  val nailgun = "org.jetbrains" % "nailgun-patched" % "1.0.0"
  val compilerInterfaceSources = "org.jetbrains" % "compiler-interface-sources" % "1.0.0"
  val bundledJline = "org.jetbrains" % "jline" % "1.0.0"
  val incrementalCompiler = "org.jetbrains" % "incremental-compiler" % "1.0.0"
}

object DependencyGroups {
  import Dependencies._

  val sbtBundled = Seq(
    compilerInterfaceSources,
    bundledJline,
    incrementalCompiler
//    sbtInterface
  )

  val jawa = Seq(sfaLibrary, jawaCore)

  val amandroid = jawa ++ Seq(amandroidCore)

}