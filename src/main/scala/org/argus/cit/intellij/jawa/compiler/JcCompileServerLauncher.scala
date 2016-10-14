/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.compiler

import java.io.{File, IOException}
import javax.swing.event.HyperlinkEvent

import com.intellij.notification.{Notification, NotificationListener, NotificationType, Notifications}
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.{JavaSdk, ProjectJdkTable}
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.util.io.FileUtil
import com.intellij.util.PathUtil
import com.intellij.util.net.NetUtils
import gnu.trove.TByteArrayList
import org.argus.cit.intellij.jawa.compiler.JcCompileServerLauncher.ConfigureLinkListener
import org.jetbrains.jps.incremental.BuilderService
import org.argus.cit.intellij.jawa.compiler.JcCompileServerLauncher._
import org.argus.cit.intellij.jawa.extensions._

import collection.JavaConverters._
import scala.util.control.Exception._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JcCompileServerLauncher  extends ApplicationComponent {
  private var serverInstance: Option[ServerInstance] = None

  def initComponent() {}

  def disposeComponent() {
    if (running) stop()
  }

  def tryToStart(project: Project): Boolean = {
    if (!running) {
      val started = start(project)
      if (started) {
        try new RemoteServerRunner(project).send("addDisconnectListener", Seq.empty, null)
        catch {
          case _: Exception =>
        }
      }
      started
    }
    else true
  }

  private def start(project: Project): Boolean = {
    val applicationSettings = JawaCompileServerSettings.getInstance

    if (applicationSettings.COMPILE_SERVER_SDK == null) {
      // Try to find a suitable JDK
      val choice = Option(ProjectRootManager.getInstance(project).getProjectSdk).orElse {
        val all = ProjectJdkTable.getInstance.getSdksOfType(JavaSdk.getInstance()).asScala
        all.headOption
      }

      choice.foreach(sdk => applicationSettings.COMPILE_SERVER_SDK = sdk.getName)

      //       val message = "JVM SDK is automatically selected: " + name +
      //               "\n(can be changed in Application Settings / Scala)"
      //       Notifications.Bus.notify(new Notification("scala", "Scala compile server",
      //         message, NotificationType.INFORMATION))
    }

    findJdkByName(applicationSettings.COMPILE_SERVER_SDK)
      .left.map(_ + "\nPlease either disable Jawa compile server or configure a valid JVM SDK for it.")
      .right.flatMap(start(project, _)) match {
      case Left(error) =>
        val title = "Cannot start Jawa compile server"
        val content = s"<html><body>${error.replace("\n", "<br>")} <a href=''>Configure</a></body></html>"
        Notifications.Bus.notify(new Notification("jawa", title, content, NotificationType.ERROR, ConfigureLinkListener))
        false
      case Right(_) =>
        ApplicationManager.getApplication invokeLater new Runnable {
          override def run() {
            JcCompileServerManager.instance(project).configureWidget()
          }
        }

        true
    }
  }

  private def start(project: Project, jdk: JDK): Either[String, Process] = {
    import org.argus.cit.intellij.jawa.compiler.JcCompileServerLauncher.{compilerJars, jvmParameters}

    compilerJars.partition(_.exists) match {
      case (presentFiles, Seq()) =>
        val bootclasspathArg = Nil
        val classpath = (jdk.tools +: presentFiles).map(_.canonicalPath).mkString(File.pathSeparator)
        val settings = JawaCompileServerSettings.getInstance

        val freePort = JcCompileServerLauncher.findFreePort
        if (settings.COMPILE_SERVER_PORT != freePort) {
          new RemoteServerStopper(settings.COMPILE_SERVER_PORT).sendStop()
          settings.COMPILE_SERVER_PORT = freePort
          ApplicationManager.getApplication.saveSettings()
        }

        val ngRunnerFqn = "org.argus.cit.intellij.jawa.nailgun.NailgunRunner"
        val id = settings.COMPILE_SERVER_ID

        val shutdownDelay = settings.COMPILE_SERVER_SHUTDOWN_DELAY
        val shutdownDelayArg = if (settings.COMPILE_SERVER_SHUTDOWN_IDLE && shutdownDelay >= 0) {
          Seq(s"-Dshutdown.delay=$shutdownDelay")
        } else Nil

        val commands = jdk.executable.canonicalPath +: bootclasspathArg ++: "-cp" +: classpath +: jvmParameters ++: shutdownDelayArg ++:
          ngRunnerFqn +: freePort.toString +: id.toString +: Nil

        val builder = new ProcessBuilder(commands.asJava)

        if (settings.USE_PROJECT_HOME_AS_WORKING_DIR) {
          projectHome(project).foreach(dir => builder.directory(dir))
        }

        catching(classOf[IOException]).either(builder.start())
          .left.map(_.getMessage)
          .right.map { process =>
          val watcher = new ProcessWatcher(process, "jawaCompileServer")
          serverInstance = Some(ServerInstance(watcher, freePort, builder.directory()))
          watcher.startNotify()
          process
        }
      case (_, absentFiles) =>
        val paths = absentFiles.map(_.getPath).mkString(", ")
        Left("Required file(s) not found: " + paths)
    }
  }

  // TODO stop server more gracefully
  def stop() {
    serverInstance.foreach { it =>
      it.destroyProcess()
    }
  }

  def stop(project: Project) {
    stop()

    ApplicationManager.getApplication invokeLater new Runnable {
      override def run() {
        JcCompileServerManager.instance(project).configureWidget()
      }
    }
  }

  def running: Boolean = serverInstance.exists(_.running)

  def errors(): Seq[String] = serverInstance.map(_.errors()).getOrElse(Seq.empty)

  def port: Option[Int] = serverInstance.map(_.port)

  def getComponentName: String = getClass.getSimpleName
}

object JcCompileServerLauncher {
  def instance: JcCompileServerLauncher = ApplicationManager.getApplication.getComponent(classOf[JcCompileServerLauncher])

  def compilerJars: Seq[File] = {
    val jcBuildersJar = new File(PathUtil.getJarPathForClass(classOf[BuilderService]))
    val utilJar = new File(PathUtil.getJarPathForClass(classOf[FileUtil]))
    val trove4jJar = new File(PathUtil.getJarPathForClass(classOf[TByteArrayList]))

    val pluginRoot = pluginPath
    val jcRoot = new File(pluginRoot, "jc")

    Seq(
      jcBuildersJar,
      utilJar,
      trove4jJar,
      new File(pluginRoot, "scala-library.jar"),
      new File(pluginRoot, "saf-library.jar"),
      new File(pluginRoot, "jawa-core.jar"),
      new File(pluginRoot, "amandroid-core.jar"),
      new File(pluginRoot, "jawa-nailgun-runner.jar"),
      new File(pluginRoot, "compiler-settings.jar"),
      new File(jcRoot, "nailgun.jar"),
      new File(jcRoot, "incremental-compiler.jar"),
      new File(jcRoot, "asm-all.jar"),
      new File(jcRoot, "jawa-compiler.jar"),
      new File(jcRoot, "jawa-jc-plugin.jar")
    )
  }

  def pluginPath: String = {
    if (ApplicationManager.getApplication.isUnitTestMode) new File(System.getProperty("plugin.path"), "lib").getCanonicalPath
    else new File(PathUtil.getJarPathForClass(getClass)).getParent
  }

  def jvmParameters: Seq[String] = {
    val settings = JawaCompileServerSettings.getInstance
    val xmx = settings.COMPILE_SERVER_MAXIMUM_HEAP_SIZE |> { size =>
      if (size.isEmpty) Nil else List("-Xmx%sm".format(size))
    }

    val (userMaxPermSize, otherParams) = settings.COMPILE_SERVER_JVM_PARAMETERS.split(" ").partition(_.contains("-XX:MaxPermSize"))

    val defaultMaxPermSize = Some("-XX:MaxPermSize=256m")
    val needMaxPermSize = settings.COMPILE_SERVER_SDK < "1.8"
    val maxPermSize = if (needMaxPermSize) userMaxPermSize.headOption.orElse(defaultMaxPermSize) else None

    xmx ++ otherParams ++ maxPermSize
  }

  def ensureServerRunning(project: Project) {
    val launcher = JcCompileServerLauncher.instance

    if (needRestart(project)) launcher.stop()

    if (!launcher.running) launcher.tryToStart(project)
  }

  def needRestart(project: Project): Boolean = {
    val serverInstance = JcCompileServerLauncher.instance.serverInstance
    serverInstance match {
      case None => true
      case Some(instance) =>
        val useProjectHome = JawaCompileServerSettings.getInstance().USE_PROJECT_HOME_AS_WORKING_DIR
        val workingDirChanged = useProjectHome && projectHome(project) != serverInstance.map(_.workingDir)
        workingDirChanged
    }
  }

  def ensureNotRunning(project: Project) {
    val launcher = JcCompileServerLauncher.instance
    if (launcher.running) launcher.stop(project)
  }

  def findFreePort: Int = {
    val port = JawaCompileServerSettings.getInstance().COMPILE_SERVER_PORT
    if (NetUtils.canConnectToSocket("localhost", port))
      NetUtils.findAvailableSocketPort()
    else port
  }

  private def projectHome(project: Project): Option[File] = {
    for {
      dir <- Option(project.getBaseDir)
      path <- Option(dir.getCanonicalPath)
      file = new File(path)
      if file.exists()
    } yield file
  }

  object ConfigureLinkListener extends NotificationListener.Adapter {
    def hyperlinkActivated(notification: Notification, event: HyperlinkEvent) {
      JcCompileServerManager.showCompileServerSettingsDialog()
      notification.expire()
    }
  }
}

private case class ServerInstance(watcher: ProcessWatcher, port: Int, workingDir: File) {
  private var stopped = false

  def running: Boolean = !stopped && watcher.running

  def errors(): Seq[String] = watcher.errors()

  def destroyProcess() {
    stopped = true
    watcher.destroyProcess()
  }
}