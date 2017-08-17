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

object BintrayResolvers {
  def jbBintrayResolver(name: String, repo: String, patterns: Patterns): URLRepository =
    Resolver.url(name, url(s"http://dl.bintray.com/jetbrains/$repo"))(patterns)

  val scalaPluginDeps: URLRepository = jbBintrayResolver("scala-plugin-deps", "scala-plugin-deps", Resolver.ivyStylePatterns)

  val allResolvers = Seq(scalaPluginDeps)
}

object CitVersions {
  val scalaVersion = "2.12.3"
  val sbtVersion = "0.13.13"
  val ideaVersion = "172.3757.52"
  val sbtStructureVersion = "5.1.2"
  val argusSafVersion = "3.1.1"
  val jgraphtVersion = "1.0.1"
  val json4sVersion = "3.5.0"
}

object Dependencies {
  import CitVersions._

  val scalaLibrary: ModuleID = "org.scala-lang" % "scala-library" % scalaVersion
  val scalaReflect: ModuleID = "org.scala-lang" % "scala-reflect" % scalaVersion

  val safLibrary: ModuleID = "com.github.arguslab" %% "saf-library" % argusSafVersion
  val jawa: ModuleID = "com.github.arguslab" %% "jawa" % argusSafVersion
  val amandroid: ModuleID = "com.github.arguslab" %% "amandroid" % argusSafVersion

  val nailgun: ModuleID = "org.jetbrains" % "nailgun-patched" % "1.0.0"
  val compilerInterfaceSources: ModuleID = "org.jetbrains" % "compiler-interface-sources" % "1.0.0"
  val bundledJline: ModuleID = "org.jetbrains" % "jline" % "1.0.0"
  val incrementalCompiler: ModuleID = "org.jetbrains" % "incremental-compiler" % "1.0.0"

  val guava: ModuleID = "com.google.guava" % "guava" % "21.0"

  val commons_lang3: ModuleID = "org.apache.commons" % "commons-lang3" % "3.5"

  val st4: ModuleID = "org.antlr" % "ST4" % "4.0.8"
  val antlr4_runtime: ModuleID = "org.antlr" % "antlr4-runtime" % "4.7"
  val antlr_runtime: ModuleID = "org.antlr" % "antlr-runtime" % "3.5.2"


  val jgrapht_core: ModuleID = "org.jgrapht" % "jgrapht-core" % jgraphtVersion
  val jgrapht_ext: ModuleID = "org.jgrapht" % "jgrapht-ext" % jgraphtVersion
  val jgraph: ModuleID = "jgraph" % "jgraph" % "5.13.0.0"
  val jgraphx: ModuleID = "org.tinyjee.jgraphx" % "jgraphx" % "2.0.0.1"

  val asm_all: ModuleID = "org.ow2.asm" % "asm-all" % "5.2"

  val ini4j: ModuleID = "org.ini4j" % "ini4j" % "0.5.4"

  val json4s_ext: ModuleID = "org.json4s" %% "json4s-ext" % json4sVersion
  val json4s_native: ModuleID = "org.json4s" %% "json4s-native" % json4sVersion
  val json4s_core: ModuleID = "org.json4s" %% "json4s-core" % json4sVersion
  val json4s_ast: ModuleID = "org.json4s" %% "json4s-ast" % json4sVersion
  val json4s_scalap: ModuleID = "org.json4s" %% "json4s-scalap" % json4sVersion
  val scalaXml: ModuleID = "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
  val joda_time: ModuleID = "joda-time" % "joda-time" % "2.9.5"
  val joda_convert: ModuleID = "org.joda" % "joda-convert" % "1.8.1"
  val paranamer: ModuleID = "com.thoughtworks.paranamer" % "paranamer" % "2.8"
}

object DependencyGroups {
  import Dependencies._

  val sbtBundled = Seq(
    compilerInterfaceSources,
    bundledJline,
    incrementalCompiler
  )

  val saf = Seq(
    safLibrary
  )

  val st = Seq(st4, antlr_runtime)

  val jgrapht = Seq(
    jgrapht_core,
    jgrapht_ext,
    jgraph,
    jgraphx,
    antlr4_runtime
  )

  val jawa_all: Seq[ModuleID] = saf ++ Seq(
    jawa,
    guava,
    commons_lang3,
    scalaReflect
  ) ++ st ++ jgrapht

  val json4s = Seq(
    json4s_ext,
    json4s_native,
    json4s_core,
    json4s_ast,
    json4s_scalap,
    joda_time,
    joda_convert,
    paranamer,
    scalaXml
  )

  val amandroid_all: Seq[ModuleID] = jawa_all ++ Seq(
    amandroid,
    ini4j
  ) ++ json4s

  val argus_cit: Seq[ModuleID] = Seq(
    scalaLibrary
  ) ++ amandroid_all

  val jc: Seq[ModuleID] = Seq(
    nailgun
  ) ++ jawa_all ++ sbtBundled
}