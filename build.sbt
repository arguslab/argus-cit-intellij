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

lazy val sdkDirectory = SettingKey[File]("sdk-directory", "Path to SDK directory where unmanaged Jars and IDEA are located")

sdkDirectory in ThisBuild := baseDirectory.in(ThisBuild).value / "SDK"

ideaBuild in ThisBuild := CitVersions.ideaVersion

ideaDownloadDirectory in ThisBuild := sdkDirectory.value / "ideaSDK"

onLoad in Global := ((s: State) => { "updateIdea" :: s}) compose (onLoad in Global).value

addCommandAlias("downloadIdea", "updateIdea")

addCommandAlias("packagePlugin", "pluginPackager/package")

addCommandAlias("packagePluginZip", "pluginCompressor/package")

lazy val argus_cit_intellij: Project =
  newProject("argus-cit-intellij", file("."))
    .dependsOn(compiler_settings)
    .enablePlugins(SbtIdeaPlugin)
    .settings(argusSafSettings)
    .settings(
      libraryDependencies ++= DependencyGroups.jawa,
      aggregate.in(updateIdea) := false
    )

lazy val nailgun_runners =
  newProject("nailgun-runners", file("nailgun-runners"))
    .settings(libraryDependencies += Dependencies.nailgun)

lazy val jc_plugin  =
  newProject("jc-plugin", file("jc-plugin"))
    .dependsOn(compiler_settings)
    .enablePlugins(SbtIdeaPlugin)
    .settings(
      libraryDependencies ++= Seq(Dependencies.nailgun) ++ DependencyGroups.sbtBundled ++ DependencyGroups.jawa ++ Seq(Dependencies.jawaCompiler)
    )

lazy val compiler_settings =
  newProject("compiler-settings", file("compiler-settings"))
    .enablePlugins(SbtIdeaPlugin)
    .settings(libraryDependencies ++= Seq(Dependencies.nailgun) ++ DependencyGroups.jawa)

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

lazy val plugin_packager =
  newProject("plugin-packager")
    .settings(
      artifactPath := packagedPluginDir.value,
      dependencyClasspath <<= (
        dependencyClasspath in (argus_cit_intellij, Compile),
        dependencyClasspath in (jc_plugin, Compile)
        ).map { (a,b) => a ++ b },
      mappings := {
        import Packaging.PackageEntry._
        val crossLibraries = List(Dependencies.sfaLibrary, Dependencies.jawaCore, Dependencies.jawaCompiler)
        val librariesToCopyAsIs = DependencyGroups.jawa.filterNot(lib =>
          crossLibraries.contains(lib))
        val jc = Seq(
          Artifact(pack.in(jc_plugin, Compile).value,
            "lib/jc/jawa-jc-plugin.jar"),
          Library(Dependencies.nailgun,
            "lib/jc/nailgun.jar"),
          Library(Dependencies.compilerInterfaceSources,
            "lib/jc/compiler-interface-sources.jar"),
          Library(Dependencies.incrementalCompiler,
            "lib/jc/incremental-compiler.jar"),
          Library(Dependencies.bundledJline,
            "lib/jc/jline.jar"),
          Library(Dependencies.jawaCompiler,
            "lib/jc/jawa-compiler.jar")
        )
        val lib = Seq(
          Artifact(pack.in(argus_cit_intellij, Compile).value,
            "lib/argus-cit-plugin.jar"),
          Artifact(pack.in(compiler_settings, Compile).value,
            "lib/compiler-settings.jar"),
          Artifact(pack.in(nailgun_runners, Compile).value,
            "lib/jawa-nailgun-runner.jar"),
          Library(Dependencies.scalaLibrary,
            "lib/scala-library.jar"),
          Library(Dependencies.sfaLibrary,
            "lib/saf-library.jar"),
          Library(Dependencies.jawaCore,
            "lib/jawa-core.jar")
        ) ++
        crossLibraries.map { lib =>
          Library(lib.copy(name = lib.name + "_2.11"), s"lib/${lib.name}.jar")
        } ++
        librariesToCopyAsIs.map { lib =>
          Library(lib, s"lib/${lib.name}.jar")
        }
        Packaging.convertEntriesToMappings(jc ++ lib, dependencyClasspath.value)
      },
      pack := {
        Packaging.packagePlugin(mappings.value, artifactPath.value)
        artifactPath.value
      }
    )

lazy val plugin_compressor =
  newProject("plugin-compressor")
    .settings(
      artifactPath := baseDirectory.in(ThisBuild).value / "out" / "argus-cit-plugin.zip",
      pack := {
        Packaging.compressPackagedPlugin(pack.in(plugin_packager).value, artifactPath.value)
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