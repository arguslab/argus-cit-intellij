

lazy val `argus-cit-intellij` = project in file(".")

name := "argus-cit-intellij"
organization := "com.github.arguslab"
scalaVersion := "2.11.8"
sbtVersion := "0.13.9"

licenses := ("Eclipse-1.0" -> url("http://www.opensource.org/licenses/eclipse-1.0.php")) :: Nil // this is required! otherwise Bintray will reject the code
homepage := Some(url("https://github.com/arguslab/argus-cit-intellij"))

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "com.github.arguslab" %% "jawa-compiler" % "0.0.2"