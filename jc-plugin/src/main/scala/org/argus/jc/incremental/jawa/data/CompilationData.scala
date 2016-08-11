/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa.data

import java.io.{File, IOException}
import java.util

import org.argus.jc.incremental.jawa.SettingsManager
import org.argus.jc.incremental.jawa.model.CompileOrder
import org.jetbrains.jps.builders.java.JavaModuleBuildTargetType
import org.jetbrains.jps.incremental.java.JavaBuilder
import org.jetbrains.jps.{ModuleChunk, ProjectPaths}
import org.jetbrains.jps.incremental.{CompileContext, ModuleBuildTarget}
import org.jetbrains.jps.model.java.JpsJavaExtensionService
import org.jetbrains.jps.model.java.compiler.JpsJavaCompilerOptions

import scala.collection.JavaConverters._


/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
case class CompilationData(sources: Seq[File],
                           classpath: Seq[File],
                           output: File,
                           jawaOptions: Seq[String],
                           javaOptions: Seq[String],
                           order: CompileOrder.Value,
                           cacheFile: File,
                           outputToCacheMap: Map[File, File],
                           outputGroups: Seq[(File, File)])

object CompilationData {
  def from(sources: Seq[File], context: CompileContext, chunk: ModuleChunk): Either[String, CompilationData] = {
    val target = chunk.representativeTarget
    val module = target.getModule

    outputsNotSpecified(chunk) match {
      case Some(message) => return Left(message)
      case None =>
    }
    val output = target.getOutputDir
    checkOrCreate(output)

    val classpath = ProjectPaths.getCompilationClasspathFiles(chunk, chunk.containsTests, false, true).asScala.toSeq
    val compilerSettings = SettingsManager.getProjectSettings(module.getProject).getCompilerSettings(chunk)
    val noBootCp = Seq("-nobootcp", "-javabootclasspath", File.pathSeparator)
    val jawaOptions = noBootCp ++: compilerSettings.getCompilerOptions
    val order = compilerSettings.getCompileOrder

    createOutputToCacheMap(context) match {
      case Left(a) => Left(a)
      case Right(outputToCacheMap) =>
        val cacheFile = outputToCacheMap.getOrElse(output,
          throw new RuntimeException("Unknown build target output directory: " + output))

        val relevantOutputToCacheMap = (outputToCacheMap - output).filter(p => classpath.contains(p._1))

        val commonOptions = {
          val encoding = context.getProjectDescriptor.getEncodingConfiguration.getPreferredModuleChunkEncoding(chunk)
          Option(encoding).map(Seq("-encoding", _)).getOrElse(Seq.empty)
        }

        val javaOptions = javaOptionsFor(context, chunk)

        val outputGroups = createOutputGroups(chunk)

        Right(CompilationData(sources, classpath, output, commonOptions ++ jawaOptions, commonOptions ++ javaOptions,
          order, cacheFile, relevantOutputToCacheMap, outputGroups))
    }
  }

  def checkOrCreate(output: File) {
    if (!output.exists()) {
      try {
        if (!output.mkdirs()) throw new IOException("Cannot create output directory: " + output.toString)
      } catch {
        case t: Throwable => throw new IOException("Cannot create output directory: " + output.toString, t)
      }
    }
  }

  def outputsNotSpecified(chunk: ModuleChunk): Option[String] = {
    chunk.getTargets.asScala.find(_.getOutputDir == null)
      .map("Output directory not specified for module " + _.getModule.getName)
  }

  private def javaOptionsFor(context: CompileContext, chunk: ModuleChunk): Seq[String] = {
    val compilerConfig = {
      val project = context.getProjectDescriptor.getProject
      JpsJavaExtensionService.getInstance.getOrCreateCompilerConfiguration(project)
    }

    val options = new util.ArrayList[String]()

    addCommonJavacOptions(options, compilerConfig.getCurrentCompilerOptions)

    val annotationProcessingProfile = {
      val module = chunk.representativeTarget.getModule
      compilerConfig.getAnnotationProcessingProfile(module)
    }

    JavaBuilder.addCompilationOptions(options, context, chunk, annotationProcessingProfile)

    options.asScala
  }

  // TODO JavaBuilder.loadCommonJavacOptions should be public
  def addCommonJavacOptions(options: util.ArrayList[String], compilerOptions: JpsJavaCompilerOptions) {
    if (compilerOptions.DEBUGGING_INFO) {
      options.add("-g")
    }

    if (compilerOptions.DEPRECATION) {
      options.add("-deprecation")
    }

    if (compilerOptions.GENERATE_NO_WARNINGS) {
      options.add("-nowarn")
    }

    if (!compilerOptions.ADDITIONAL_OPTIONS_STRING.isEmpty) {
      // TODO extract VM options
      options.addAll(compilerOptions.ADDITIONAL_OPTIONS_STRING.split("\\s+").toSeq.asJava)
    }
  }

  private def createOutputToCacheMap(context: CompileContext): Either[String, Map[File, File]] = {
    val targetToOutput = targetsIn(context).map(target => (target, target.getOutputDir))

    outputClashesIn(targetToOutput).toLeft {
      val paths = context.getProjectDescriptor.dataManager.getDataPaths

      for ((target, output) <- targetToOutput.toMap)
        yield (output, new File(paths.getTargetDataRoot(target), "cache.dat"))
    }
  }

  private def createOutputGroups(chunk: ModuleChunk): Seq[(File, File)] = {
    for {
      target <- chunk.getTargets.asScala.toSeq
      module = target.getModule
      output = target.getOutputDir
      sourceRoot <- module.getSourceRoots.asScala.map(_.getFile)
      if sourceRoot.exists
    } yield (sourceRoot, output)
  }

  private def targetsIn(context: CompileContext): Seq[ModuleBuildTarget] = {
    def isExcluded(target: ModuleBuildTarget): Boolean = false

    val buildTargetIndex = context.getProjectDescriptor.getBuildTargetIndex
    val targets = JavaModuleBuildTargetType.ALL_TYPES.asScala.flatMap(buildTargetIndex.getAllTargets(_).asScala)

    targets.distinct.filterNot { target =>
      buildTargetIndex.isDummy(target) || isExcluded(target)
    }
  }

  private def outputClashesIn(targetToOutput: Seq[(ModuleBuildTarget, File)]): Option[String] = {
    val outputToTargetsMap = targetToOutput.groupBy(_._2).mapValues(_.map(_._1))

    val errors = outputToTargetsMap.collect {
      case (output, targets) if output != null && targets.length > 1 =>
        val targetNames = targets.map(_.getPresentableName).mkString(", ")
        "Output path %s is shared between: %s".format(output, targetNames)
    }

    if (errors.isEmpty) None else Some(errors.mkString("\n") +
      "\nPlease configure separate output paths to proceed with the compilation." +
      "\nTIP: you can use Project Artifacts to combine compiled classes if needed.")
  }
}