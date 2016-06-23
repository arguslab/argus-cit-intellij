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

import com.intellij.psi.impl.source.PsiFileImpl
import com.intellij.psi.stubs.StubElement
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

  def getFirstStubOrPsiElement(elem: PsiElement): PsiElement = {
    elem match {
      case st: JawaStubBasedElementImpl[_] if st.getStub != null =>
        val stub = st.getStub
        val childrenStubs = stub.getChildrenStubs
        if (childrenStubs.size() > 0) childrenStubs.get(0).getPsi
        else null
      case file: PsiFileImpl if file.getStub != null =>
        val stub = file.getStub
        val childrenStubs = stub.getChildrenStubs
        if (childrenStubs.size() > 0) childrenStubs.get(0).getPsi
        else null
      case _ => elem.getFirstChild
    }
  }

  def getPrevStubOrPsiElement(elem: PsiElement): PsiElement = {
    def workWithStub(stub: StubElement[_ <: PsiElement]): PsiElement = {
      val parent = stub.getParentStub
      if (parent == null) return null

      val children = parent.getChildrenStubs
      val index = children.indexOf(stub)
      if (index == -1) {
        elem.getPrevSibling
      } else if (index == 0) {
        null
      } else {
        children.get(index - 1).getPsi
      }
    }
    elem match {
      case st: JawaStubBasedElementImpl[_] =>
        val stub = st.getStub
        if (stub != null) return workWithStub(stub)
      case file: PsiFileImpl =>
        val stub = file.getStub
        if (stub != null) return workWithStub(stub)
      case _ =>
    }
    elem.getPrevSibling
  }

  def getNextStubOrPsiElement(elem: PsiElement): PsiElement = {
    elem match {
      case st: JawaStubBasedElementImpl[_] if st.getStub != null =>
        val stub = st.getStub
        val parent = stub.getParentStub
        if (parent == null) return null

        val children = parent.getChildrenStubs
        val index = children.indexOf(stub)
        if (index == -1) {
          elem.getNextSibling
        } else if (index >= children.size - 1) {
          null
        } else {
          children.get(index + 1).getPsi
        }
      case _ => elem.getNextSibling
    }
  }
}
