/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.api.toplevel

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.PsiFileImpl
import com.intellij.psi.stubs.StubElement
import org.argus.cit.intellij.jawa.JavaArrayFactoryUtil
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaStubElementTypes
import org.argus.cit.intellij.jawa.lang.psi.{JawaClassOrInterfaceDeclaration, JawaPsiElement}
import org.argus.jawa.core.util._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaToplevelElement extends JawaPsiElement {
  def typeDefinitions: Seq[JawaClassOrInterfaceDeclaration] = {
    val buff: MList[JawaClassOrInterfaceDeclaration] = mlistEmpty[JawaClassOrInterfaceDeclaration]
    buff ++= immediateTypeDefinitions
    buff
  }

  def typeDefinitionsArray: Array[JawaClassOrInterfaceDeclaration] = {
    val buff: MList[JawaClassOrInterfaceDeclaration] = mlistEmpty[JawaClassOrInterfaceDeclaration]
    buff ++= immediateTypeDefinitions
    buff.toArray
  }

  def immediateTypeDefinitions: Seq[JawaClassOrInterfaceDeclaration] = {
    val stub: StubElement[_ <: PsiElement] = this match {
      case file: PsiFileImpl => file.getStub
      case _ => null
    }
    if (stub != null) {
      stub.getChildrenByType[JawaClassOrInterfaceDeclaration](JawaStubElementTypes.CLASS, JavaArrayFactoryUtil.JawaClassOrInterfaceDeclarationFactory)
    } else findChildrenByClassJawa(classOf[JawaClassOrInterfaceDeclaration]).toSeq
  }
}
