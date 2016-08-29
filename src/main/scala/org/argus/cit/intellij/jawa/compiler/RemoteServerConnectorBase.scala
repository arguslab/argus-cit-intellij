/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

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
//package org.argus.cit.intellij.jawa.compiler
//
//import java.io.File
//
//import com.intellij.openapi.application.ApplicationManager
//import com.intellij.openapi.module.Module
//import com.intellij.openapi.roots.OrderEnumerator
//import com.intellij.openapi.util.io.FileUtil
//import com.intellij.util.{PathUtil, PlatformUtils}
//
///**
//  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
//  */
//abstract class RemoteServerConnectorBase(module: Module, filesToCompile: Seq[File], outputDir: File) {
//
//  checkFilesToCompile(filesToCompile)
//
//  def this(module: Module, fileToCompile: File, outputDir: File) = {
//    this(module, Seq(fileToCompile), outputDir)
//  }
//
////  private val libRoot = {
////    if (ApplicationManager.getApplication.isUnitTestMode) {
////      if (PlatformUtils.isIdeaCommunity) new File("./out/plugin/Jawa/lib").getAbsoluteFile
////      else new File("../../out/plugin/Jawa/lib").getAbsoluteFile
////    }
////    else new File(PathUtil.getJarPathForClass(getClass)).getParentFile
////  }
//
////  private val libCanonicalPath = PathUtil.getCanonicalPath(libRoot.getPath)
//
//  private val sourceRoot = filesToCompile.head.getParentFile
//
//  private val javaParameters = Array.empty[String]
//
////  private val compilerClasspath = scalaSdk.compilerClasspath
//
////  private val compilerSettingsJar = new File(libCanonicalPath, "compiler-settings.jar")
////
////  protected val runnersJar = new File(libCanonicalPath, "scala-plugin-runners.jar")
//
////  val additionalCp = compilerClasspath :+ runnersJar :+ compilerSettingsJar :+ outputDir
////
////  protected def classpath: String = {
////    val classesRoots = assemblyClasspath().toSeq map (f => new File(f.getCanonicalPath stripSuffix "!" stripSuffix "!/"))
////    (classesRoots ++ additionalCp).mkString("\n")
////  }
//
//  import _root_.scala.language.implicitConversions
//
//  implicit def file2path(file: File): String = FileUtil.toCanonicalPath(file.getAbsolutePath)
//  implicit def option2string(opt: Option[String]): String = opt getOrElse ""
//  implicit def files2paths(files: Iterable[File]): String = files map file2path mkString "\n"
//  implicit def array2string(arr: Array[String]): String = arr mkString "\n"
//
//  /**
//    *     Seq(
//      fileToPath(sbtData.interfaceJar),
//      fileToPath(sbtData.sourceJar),
//      fileToPath(sbtData.interfacesHome),
//      sbtData.javaClassVersion,
//      optionToString(compilerJarPaths),
//      optionToString(javaHomePath),
//      filesToPaths(compilationData.sources),
//      filesToPaths(compilationData.classpath),
//      fileToPath(compilationData.output),
//      sequenceToString(compilationData.scalaOptions),
//      sequenceToString(compilationData.javaOptions),
//      compilationData.order.toString,
//      fileToPath(compilationData.cacheFile),
//      filesToPaths(outputs),
//      filesToPaths(caches),
//      incrementalType.name,
//      filesToPaths(sourceRoots),
//      filesToPaths(outputDirs),
//      sequenceToString(worksheetFiles),
//      nameHashing.name
//    )
//    */
//  def arguments = Seq[String](
////    sbtData.interfaceJar,
////    sbtData.sourceJar,
////    sbtData.interfacesHome,
////    sbtData.javaClassVersion,
////    compilerClasspath,
//    findJdk,
//    filesToCompile,
////    classpath,
//    outputDir,
////    scalaParameters,
//    javaParameters,
////    compilerSettings.compileOrder.toString,
//    "", //cache file
//    "",
//    "",
////    IncrementalityType.IDEA.name(),
//    sourceRoot,
//    outputDir
//  )
//
//  protected def configurationError(message: String) = throw new IllegalArgumentException(message)
//
//  protected def settings = JawaCompileServerSettings.getInstance()
//
//  private def assemblyClasspath() = OrderEnumerator.orderEntries(module).compileOnly().getClassesRoots
//
////  private def compilerSettings: JawaCompilerSettings = module.scalaCompilerSettings
//
//  private def findJdk = findJdkByName(settings.COMPILE_SERVER_SDK) match {
//    case Right(jdk) => jdk.executable
//    case Left(msg) =>
//      configurationError(s"Cannot find jdk ${settings.COMPILE_SERVER_SDK} for compile server, underlying message: $msg" )
//  }
//
//  private def checkFilesToCompile(files: Seq[File]) = {
//    if (files.isEmpty)
//      throw new IllegalArgumentException("Non-empty list of files expected")
//
//    files.find(!_.exists()).foreach(f =>
//      throw new IllegalArgumentException(s"File ${f.getCanonicalPath} does not exists" )
//    )
//
//    if (files.map(_.getParent).distinct.size != 1)
//      throw new IllegalArgumentException("All files should be in the same directory")
//  }
//}