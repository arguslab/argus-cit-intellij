import Common._
import com.dancingrobot84.sbtidea.SbtIdeaPlugin
import com.dancingrobot84.sbtidea.Tasks.{updateIdea => updateIdeaTask}
import sbt.Keys.{`package` => pack}

licenses in ThisBuild := ("Eclipse-1.0" -> url("http://www.opensource.org/licenses/eclipse-1.0.php")) :: Nil // this is required! otherwise Bintray will reject the code
homepage in ThisBuild := Some(url("https://github.com/arguslab/argus-cit-intellij"))

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

val argusSafSettings = Defaults.coreDefaultSettings ++ Seq(
  libraryDependencies += "org.scala-lang" % "scala-compiler" % CitVersions.scalaVersion,
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
)

lazy val sdkDirectory = SettingKey[File]("sdk-directory", "Path to SDK directory where unmanagedJars and IDEA are located")

sdkDirectory in ThisBuild := baseDirectory.in(ThisBuild).value / "SDK"

ideaBuild in ThisBuild := CitVersions.ideaVersion

ideaDownloadDirectory in ThisBuild := sdkDirectory.value / "ideaSDK"

onLoad in Global := ((s: State) => { "updateIdea" :: s}) compose (onLoad in Global).value

addCommandAlias("downloadIdea", "updateIdea")

addCommandAlias("packagePlugin", "pluginPackager/package")

addCommandAlias("packagePluginZip", "pluginCompressor/package")

lazy val argus_cit_intellij: Project =
  newProject("argus-cit-intellij", file("."))
  .enablePlugins(SbtIdeaPlugin)
  .settings(argusSafSettings)
  .settings(
    libraryDependencies ++= DependencyGroups.argus_cit_intellij,
    aggregate.in(updateIdea) := false
  )


// Utility projects

lazy val idea_runner =
  newProject("idea-runner", file("idea-runner"))
    .dependsOn(Seq(argus_cit_intellij).map(_ % Provided): _*)
    .settings(
      autoScalaLibrary := false,
      unmanagedJars in Compile := ideaMainJars.in(argus_cit_intellij).value,
      // run configuration
      fork in run := true,
      mainClass in (Compile, run) := Some("com.intellij.idea.Main"),
      javaOptions in run ++= Seq(
        "-Xmx800m",
        "-XX:ReservedCodeCacheSize=64m",
        "-XX:MaxPermSize=250m",
        "-XX:+HeapDumpOnOutOfMemoryError",
        "-ea",
        "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005",
        "-Didea.is.internal=true",
        "-Didea.debug.mode=true",
        "-Didea.system.path=$USER_HOME$/.IdeaData/IDEA-14/scala/system",
        "-Didea.config.path=$USER_HOME$/.IdeaData/IDEA-14/scala/config",
        "-Dapple.laf.useScreenMenuBar=true",
        s"-Dplugin.path=${baseDirectory.value.getParentFile}/out/plugin",
        "-Didea.ProcessCanceledException=disabled"
      )
    )

// Packaging projects

lazy val packagedPluginDir = settingKey[File]("Path to packaged, but not yet compressed plugin")

packagedPluginDir in ThisBuild := baseDirectory.in(ThisBuild).value / "out" / "plugin" / "Argus-CIT"

lazy val pluginPackager =
  newProject("pluginPackager")
    .settings(
      artifactPath := packagedPluginDir.value,
      dependencyClasspath <<= (
        dependencyClasspath in (argus_cit_intellij, Compile)
        ),
      mappings := {
        import Packaging.PackageEntry._
        val crossLibraries = List(Dependencies.sfaLibrary, Dependencies.jawaCore, Dependencies.jawaCompiler)
        val librariesToCopyAsIs = DependencyGroups.argus_cit_intellij.filterNot(lib =>
          crossLibraries.contains(lib))
        val lib = Seq(
          Artifact(pack.in(argus_cit_intellij, Compile).value,
            "lib/argus-cit-plugin.jar")
        ) ++
        crossLibraries.map { lib =>
          Library(lib.copy(name = lib.name + "_2.11"), s"lib/${lib.name}.jar")
        } ++
        librariesToCopyAsIs.map { lib =>
          Library(lib, s"lib/${lib.name}.jar")
        }
        Packaging.convertEntriesToMappings(lib, dependencyClasspath.value)
      },
      pack := {
        Packaging.packagePlugin(mappings.value, artifactPath.value)
        artifactPath.value
      }
    )

lazy val pluginCompressor =
  newProject("pluginCompressor")
    .settings(
      artifactPath := baseDirectory.in(ThisBuild).value / "out" / "argus-cit-plugin.zip",
      pack := {
        Packaging.compressPackagedPlugin(pack.in(pluginPackager).value, artifactPath.value)
        artifactPath.value
      }
    )

updateIdea <<= (ideaBaseDirectory, ideaBuild.in(ThisBuild), streams).map {
  (baseDir, build, streams) =>
    try {
      updateIdeaTask(baseDir, build, Seq.empty, streams)
    } catch {
      case e : sbt.TranslatedException if e.getCause.isInstanceOf[java.io.FileNotFoundException] =>
        val newBuild = build.split('.').init.mkString(".") + "-EAP-CANDIDATE-SNAPSHOT"
        streams.log.warn(s"Failed to download IDEA $build, trying $newBuild")
        IO.deleteIfEmpty(Set(baseDir))
        updateIdeaTask(baseDir, newBuild, Seq.empty, streams)
    }
}