/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa
package data

import java.io.File

import org.jetbrains.jps.ModuleChunk
import org.jetbrains.jps.incremental.CompileContext
import org.jetbrains.jps.model.java.JpsJavaSdkType
import org.jetbrains.jps.model.module.JpsModule

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
case class CompilerData(javaHome: Option[File])

object CompilerData {
  def from(context: CompileContext, chunk: ModuleChunk): Either[String, CompilerData] = {
    val target = chunk.representativeTarget
    val module = target.getModule

    javaHome(context, module).map(CompilerData(_))
  }

  def javaHome(context: CompileContext, module: JpsModule): Either[String, Option[File]] = {
    val project = context.getProjectDescriptor
    val model = project.getModel

    Option(module.getSdk(JpsJavaSdkType.INSTANCE))
      .toRight("No JDK in module " + module.getName)
      .flatMap { moduleJdk =>

        val globalSettings = SettingsManager.getGlobalSettings(model.getGlobal)

        val jvmSdk = Option(model.getProject.getSdkReferencesTable.getSdkReference(JpsJavaSdkType.INSTANCE))
          .flatMap(references => Option(references.resolve)).map(_.getProperties)

        if (jvmSdk.contains(moduleJdk)) Right(None)
        else {
          val directory = new File(moduleJdk.getHomePath)
          Either.cond(directory.exists, Some(directory), "JDK home directory does not exists: " + directory)
        }
      }
  }
}