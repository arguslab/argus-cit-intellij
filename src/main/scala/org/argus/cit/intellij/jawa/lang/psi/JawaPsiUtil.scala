/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi

import com.intellij.psi.{PsiPackage, _}

/**
  * Created by fgwei on 6/22/16.
  */
object JawaPsiUtil {

  def nameContext(x: PsiNamedElement): PsiElement = {
    var parent = x.getParent
    def isAppropriatePsiElement(x: PsiElement): Boolean = {
      x match {
        case _: JawaClassOrInterfaceDeclaration | _: JawaMethodDeclaration | _: JawaInstanceFieldDeclaration | _: JawaStaticFieldDeclaration | _: PsiMethod | _: PsiField
             | _: PsiClass | _: PsiPackage => true
        case _ => false
      }
    }
    if (isAppropriatePsiElement(x)) return x
    while (parent != null && !isAppropriatePsiElement(parent)) parent = parent.getParent
    parent
  }

  object inNameContext {
    def unapply(x: PsiNamedElement): Option[PsiElement] = Option(nameContext(x))
  }
}
