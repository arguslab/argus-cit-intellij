/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa

import _root_.java.io._
import java.net.InetAddress

import com.intellij.openapi.diagnostic.{Logger => JpsLogger}
import org.argus.jc.incremental.jawa.data.{CompilationData, CompilerData}
import org.argus.jc.incremental.jawa.local.LocalServer
import org.argus.jc.incremental.jawa.remote.RemoteServer
import org.jetbrains.jps.ModuleChunk
import org.jetbrains.jps.builders.java.JavaBuilderUtil
import org.jetbrains.jps.incremental._
import org.jetbrains.jps.incremental.messages.ProgressMessage
import org.jetbrains.jps.model.module.JpsModule

import _root_.scala.collection.JavaConverters._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaBuilder {
  def compile(context: CompileContext,
              chunk: ModuleChunk,
              sources: Seq[File],
              modules: Set[JpsModule],
              client: Client): Either[String, ModuleLevelBuilder.ExitCode] = {

    context.processMessage(new ProgressMessage("Reading compilation settings..."))

    for {
      compilerData <- CompilerData.from(context, chunk)
      compilationData <- CompilationData.from(sources, context, chunk)
    } yield {
      val server = getServer(context)
      server.compile(compilerData, compilationData, client)
    }
  }

  def hasBuildModules(chunk: ModuleChunk): Boolean = {
    import _root_.scala.collection.JavaConversions._
    chunk.getModules.exists(_.getName.endsWith("-build")) // gen-idea doesn't use the SBT module type
  }

  def projectSettings(context: CompileContext) = SettingsManager.getProjectSettings(context.getProjectDescriptor.getProject)

  def isMakeProject(context: CompileContext): Boolean = JavaBuilderUtil.isCompileJavaIncrementally(context) && {
    for {
      chunk <- context.getProjectDescriptor.getBuildTargetIndex.getSortedTargetChunks(context).asScala
      target <- chunk.getTargets.asScala
    } {
      if (!context.getScope.isAffected(target)) return false
    }
    true
  }

  val Log = JpsLogger.getInstance(JawaBuilder.getClass.getName)

  // Cached local localServer
  private var cachedServer: Option[Server] = None

  private val lock = new Object()

  def localServer = {
    lock.synchronized {
      val server = cachedServer.getOrElse(new LocalServer())
      cachedServer = Some(server)
      server
    }
  }

  private def cleanLocalServerCache() {
    lock.synchronized {
      cachedServer = None
    }
  }

  private def getServer(context: CompileContext): Server = {
    val settings = SettingsManager.getGlobalSettings(context.getProjectDescriptor.getModel.getGlobal)

    if (settings.isCompileServerEnabled && JavaBuilderUtil.CONSTANT_SEARCH_SERVICE.get(context) != null) {
      cleanLocalServerCache()
      new RemoteServer(InetAddress.getByName(null), settings.getCompileServerPort)
    } else {
      localServer
    }
  }
}
