///*
// * Copyright (c) 2016. Fengguo Wei and others.
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Detailed contributors are listed in the CONTRIBUTOR.md
// */
//
//package org.argus.jc.incremental.jawa.data
//
//import java.io.File
//
//import org.jetbrains.jps.ModuleChunk
//import org.jetbrains.jps.builders.java.JavaBuilderUtil
//import org.jetbrains.jps.incremental.CompileContext
//import org.jetbrains.jps.model.java.JpsJavaSdkType
//import org.jetbrains.jps.model.module.JpsModule
//
//import collection.JavaConverters._
//
///**
//  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
//  */
//case class CompilerData(compilerJars: Option[CompilerJars], javaHome: Option[File])
//
//object CompilerData {
//  def from(context: CompileContext, chunk: ModuleChunk): Either[String, CompilerData] = {
//    val project = context.getProjectDescriptor
//    val target = chunk.representativeTarget
//    val module = target.getModule
//
//    val compilerJars = compilerJarsIn(module).flatMap { case jars: CompilerJars =>
//      val absentJars = jars.files.filter(!_.exists)
//      Either.cond(absentJars.isEmpty,
//        Some(jars),
//        "Jawa compiler JARs not found (module '" + chunk.representativeTarget().getModule.getName + "'): "
//          + absentJars.map(_.getPath).mkString(", "))
//    }
//    compilerJars.flatMap { jars =>
//      javaHome(context, module).map(CompilerData(jars, _))
//    }
//  }
//
//  def javaHome(context: CompileContext, module: JpsModule): Either[String, Option[File]] = {
//    val project = context.getProjectDescriptor
//    val model = project.getModel
//
//    Option(module.getSdk(JpsJavaSdkType.INSTANCE))
//      .toRight("No JDK in module " + module.getName)
//      .flatMap { moduleJdk =>
//
//        val globalSettings = SettingsManager.getGlobalSettings(model.getGlobal)
//
//        val jvmSdk = if (globalSettings.isCompileServerEnabled && JavaBuilderUtil.CONSTANT_SEARCH_SERVICE.get(context) != null) {
//          Option(globalSettings.getCompileServerSdk).flatMap { sdkName =>
//            val libraries = model.getGlobal.getLibraryCollection.getLibraries(JpsJavaSdkType.INSTANCE).asScala
//            libraries.find(_.getName == sdkName).map(_.getProperties)
//          }
//        } else {
//          Option(model.getProject.getSdkReferencesTable.getSdkReference(JpsJavaSdkType.INSTANCE))
//            .flatMap(references => Option(references.resolve)).map(_.getProperties)
//        }
//
//        if (jvmSdk.contains(moduleJdk)) Right(None)
//        else {
//          val directory = new File(moduleJdk.getHomePath)
//          Either.cond(directory.exists, Some(directory), "JDK home directory does not exists: " + directory)
//        }
//      }
//  }
//}