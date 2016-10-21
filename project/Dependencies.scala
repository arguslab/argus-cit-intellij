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
  val ideaVersion = "162.2228.15"
  val sbtStructureVersion = "5.1.2"
  val argusSafVersion = "1.1.4"
  val jawaCompilerVersion = "1.0.6"
  val jacksonVersion = "2.2.3"
  val jgraphtVersion = "0.9.2"
  val json4sVersion = "3.3.0"
}

object Dependencies {
  import CitVersions._

  val scalaLibrary = "org.scala-lang" % "scala-library" % scalaVersion
  val scalaReflect = "org.scala-lang" % "scala-reflect" % scalaVersion
  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.5"
  val scalaParserCombinators = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

  val akka_actor = "com.typesafe.akka" %% "akka-actor" % "2.4.7"
  val config = "com.typesafe" % "config" % "1.3.0"
  val scala_java8_compat = "org.scala-lang.modules" %% "scala-java8-compat" % "0.7.0"

  val safLibrary = "com.github.arguslab" %% "saf-library" % argusSafVersion
  val jawaCore = "com.github.arguslab" %% "jawa-core" % argusSafVersion
  val amandroidCore = "com.github.arguslab" %% "amandroid-core" % argusSafVersion
  val jawaCompiler = "com.github.arguslab" %% "jawa-compiler" % jawaCompilerVersion

  val nailgun = "org.jetbrains" % "nailgun-patched" % "1.0.0"
  val compilerInterfaceSources = "org.jetbrains" % "compiler-interface-sources" % "1.0.0"
  val bundledJline = "org.jetbrains" % "jline" % "1.0.0"
  val incrementalCompiler = "org.jetbrains" % "incremental-compiler" % "1.0.0"

  val guava = "com.google.guava" % "guava" % "19.0"
  val blueprints_core = "com.tinkerpop.blueprints" % "blueprints-core" % "2.6.0"
  val hppc = "com.carrotsearch" % "hppc" % "0.6.0"
  val jackson_databind = "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
  val jackson_annotations = "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion
  val jackson_core = "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion
  val commons_configuration = "commons-configuration" % "commons-configuration" % "1.6"
  val commons_digester = "commons-digester" % "commons-digester" % "1.8"
  val commons_beanutils = "commons-beanutils" % "commons-beanutils" % "1.7.0"
  val commons_lang = "commons-lang" % "commons-lang" % "2.4"
  val jettison = "org.codehaus.jettison" % "jettison" % "1.3.3"
  val stax_api = "stax" % "stax-api" % "1.0.1"

  val st4 = "org.antlr" % "ST4" % "4.0.8"
  val antlr_runtime = "org.antlr" % "antlr-runtime" % "3.5.2"

  val commons_lang3 = "org.apache.commons" % "commons-lang3" % "3.4"

  val jgrapht_core = "org.jgrapht" % "jgrapht-core" % jgraphtVersion
  val jgrapht_ext = "org.jgrapht" % "jgrapht-ext" % jgraphtVersion
  val jgraph = "jgraph" % "jgraph" % "5.13.0.0"
  val jgraphx = "org.tinyjee.jgraphx" % "jgraphx" % "2.0.0.1"

  val asm_all = "org.ow2.asm" % "asm-all" % "5.1"

  val ini4j = "org.ini4j" % "ini4j" % "0.5.4"

  val json4s_ext = "org.json4s" %% "json4s-ext" % json4sVersion
  val json4s_native = "org.json4s" %% "json4s-native" % json4sVersion
  val json4s_core = "org.json4s" %% "json4s-core" % json4sVersion
  val json4s_ast = "org.json4s" %% "json4s-ast" % json4sVersion
  val json4s_scalap = "org.json4s" %% "json4s-scalap" % json4sVersion
  val joda_time = "joda-time" % "joda-time" % "2.8.2"
  val joda_convert = "org.joda" % "joda-convert" % "1.7"
  val paranamer = "com.thoughtworks.paranamer" % "paranamer" % "2.8"
}

object DependencyGroups {
  import Dependencies._

  val sbtBundled = Seq(
    compilerInterfaceSources,
    bundledJline,
    incrementalCompiler
//    sbtInterface
  )

  val saf = Seq(
    safLibrary
  )

  val jackson = Seq(
    jackson_databind,
    jackson_annotations,
    jackson_core
  )

  val commons = Seq(
    commons_configuration,
    commons_digester,
    commons_beanutils,
    commons_lang
  )

  val blueprints = Seq(
    blueprints_core,
    hppc,
    jettison,
    stax_api
  ) ++ jackson ++ commons

  val st = Seq(st4, antlr_runtime)

  val jgrapht = Seq(
    jgrapht_core,
    jgrapht_ext,
    jgraph,
    jgraphx
  )

  val jawa = saf ++ Seq(
    jawaCore,
    guava,
    commons_lang3,
    scalaReflect
  ) ++ blueprints ++ st ++ jgrapht

  val akka = Seq(
    akka_actor,
    config,
    scala_java8_compat
  )

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

  val amandroid = jawa ++ Seq(
    amandroidCore,
    ini4j
  ) ++ akka ++ json4s

  val argus_cit = Seq(
    scalaLibrary,
    scalaParserCombinators
  ) ++ amandroid

  val jc = Seq(
    nailgun,
    jawaCompiler
  ) ++ jawa ++ sbtBundled
}