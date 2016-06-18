/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

import sbt.Keys._
import sbt._

import scala.language.{implicitConversions, postfixOps}

object Common {
  def newProject(projectName: String, base: File): Project =
    Project(projectName, base).settings(
      name := projectName,
      organization := "com.github.arguslab",
      scalaVersion := CitVersions.scalaVersion,
      unmanagedSourceDirectories in Compile += baseDirectory.value / "src",
      unmanagedSourceDirectories in Test += baseDirectory.value / "test",
      unmanagedResourceDirectories in Compile += baseDirectory.value / "resources"
    )

  def newProject(projectName: String): Project =
    newProject(projectName, file(projectName))

  def unmanagedJarsFrom(sdkDirectory: File, subdirectories: String*): Classpath = {
    val sdkPathFinder = subdirectories.foldLeft(PathFinder.empty) { (finder, dir) =>
      finder +++ (sdkDirectory / dir)
    }
    (sdkPathFinder * globFilter("*.jar")).classpath
  }

  def ivyHomeDir: File =
    Option(System.getProperty("sbt.ivy.home")).fold(Path.userHome / ".ivy2")(file)
}