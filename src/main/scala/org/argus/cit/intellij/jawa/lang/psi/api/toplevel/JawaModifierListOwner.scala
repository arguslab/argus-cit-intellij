/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.api.toplevel

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.{PsiElement, PsiModifier, PsiModifierListOwner}
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.util.PsiUtilCore
import com.intellij.util.indexing.FileBasedIndex
import org.argus.cit.intellij.jawa.JavaArrayFactoryUtil
import org.argus.cit.intellij.jawa.lang.psi.{JawaPsiElement, JawaStubBasedElementImpl, JawaElementTypes}
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaModifierList

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaModifierListOwner extends JawaPsiElement with PsiModifierListOwner {
  override def getModifierList: JawaModifierList = {
    this match {
      case st: JawaStubBasedElementImpl[_] =>
        val stub: StubElement[_ <: PsiElement] = st.getStub
        if (stub != null) {
          val array = stub.getChildrenByType(JawaElementTypes.ACCESS_FLAG_ANNOTATION, JavaArrayFactoryUtil.JawaModifierListFactory)
          if (array.isEmpty) {
            val faultyContainer: VirtualFile = PsiUtilCore.getVirtualFile(this)
            if (faultyContainer != null && faultyContainer.isValid) {
              FileBasedIndex.getInstance.requestReindex(faultyContainer)
            }
            throw new Throwable("Stub hasn't ScModifierList child: " + faultyContainer)
          }
          else return array.apply(0)
        }
      case _ =>
    }
    findChildByClassJawa(classOf[JawaModifierList])
  }

  def hasModifierProperty(name: String): Boolean = {
    hasModifierPropertyInner(name)
  }

  def hasModifierPropertyScala(name: String): Boolean = {
    if (name == PsiModifier.PUBLIC) {
      return !hasModifierPropertyScala("private") && !hasModifierPropertyScala("protected")
    }
    hasModifierPropertyInner(name)
  }

  def hasAbstractModifier: Boolean = hasModifierPropertyInner("abstract")

  def hasFinalModifier: Boolean = hasModifierPropertyInner("final")

  private def hasModifierPropertyInner(name: String): Boolean = {
    this match {
      case st: JawaStubBasedElementImpl[_] =>
        val stub: StubElement[_ <: PsiElement] = st.getStub
        if (stub != null) {
          val mod = stub.findChildStubByType(JawaElementTypes.ACCESS_FLAG_ANNOTATION)
          if (mod != null) {
            return mod.getPsi.hasModifierProperty(name: String)
          }
          return false
        }
      case _ =>
    }
    if (getModifierList != null) getModifierList.hasModifierProperty(name: String)
    else false
  }

  def setModifierProperty(name: String, value: Boolean) {
    getModifierList.setModifierProperty(name, value)
  }
}
