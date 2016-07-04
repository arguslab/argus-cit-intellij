/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.debugger

import com.intellij.debugger.NameMapper
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Computable
import com.intellij.psi.PsiClass
import org.argus.cit.intellij.jawa.lang.psi.JawaClassOrInterfaceDeclaration
import org.jetbrains.annotations.NotNull

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaJVMNameMapper extends NameMapper {
  def getQualifiedName(@NotNull clazz: PsiClass): String = {
    ApplicationManager.getApplication.runReadAction(new Computable[String] {
      def compute: String = {
        clazz match {
          case jc: JawaClassOrInterfaceDeclaration => jc.getTypeDefSymbol.getJawaType.name
          case psiClass => null
        }
      }
    })
  }
}
