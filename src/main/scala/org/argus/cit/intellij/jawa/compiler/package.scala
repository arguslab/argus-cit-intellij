/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa

import java.io.File

import com.intellij.openapi.projectRoots.{JavaSdkType, ProjectJdkTable, SdkType}
import com.intellij.openapi.util.io.FileUtil

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
package object compiler {
  case class JDK(executable: File, tools: File)

  def findJdkByName(sdkName: String): Either[String, JDK] = {
    Option(sdkName).toRight("No JVM SDK configured").right.flatMap { name =>

      val projectSdk = Option(ProjectJdkTable.getInstance().findJdk(name))
        .toRight("JVM SDK does not exists: " + sdkName)

      projectSdk.right.flatMap { sdk =>
        sdk.getSdkType match {
          case jdkType: SdkType with JavaSdkType =>
            val vmExecutable = Either.cond(jdkType.sdkHasValidPath(sdk),
              new File(jdkType.getVMExecutablePath(sdk)), "Not valid SDK path: " + sdkName)

            vmExecutable.right.flatMap { executable =>
              val tools = new File(jdkType.getToolsPath(sdk)) // TODO properly handle JDK 6 on Mac OS
            val toolsPresent = true //tools.exists()
              Either.cond(toolsPresent, JDK(executable, tools), "SDK tools not found: " + tools)
            }
          case _ => Left("Not a Java SDK: " + sdkName)
        }
      }
    }
  }

  implicit class RichFile(val file: File) extends AnyVal {
    def canonicalPath: String = FileUtil.toCanonicalPath(file.getPath)
  }
}
