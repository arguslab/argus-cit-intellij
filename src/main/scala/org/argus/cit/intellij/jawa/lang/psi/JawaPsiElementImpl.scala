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

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.impl.CheckUtil
import com.intellij.psi.impl.source.tree.{LazyParseablePsiElement, SharedImplUtil}
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.PsiElement
import org.argus.cit.intellij.jawa.extensions.inReadAction

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
//abstract class JawaPsiElementImpl(node: ASTNode) extends ASTWrapperPsiElement(node) with JawaPsiElementWrapper {
//
//  override def getContext: PsiElement = {
//    context match {
//      case null => super.getContext
//      case _ => context
//    }
//  }
//
//  override def getStartOffsetInParent: Int = {
//    child match {
//      case null => super.getStartOffsetInParent
//      case _ => child.getStartOffsetInParent
//    }
//  }
//
//  override def getPrevSibling: PsiElement = {
//    child match {
//      case null => super.getPrevSibling
//      case _ => child.getPrevSibling
//    }
//  }
//
//  override def getNextSibling: PsiElement = {
//    child match {
//      case null => super.getNextSibling
//      case _ => child.getNextSibling
//    }
//  }
//
//  // todo override in more specific cases
//  override def replace(newElement: PsiElement): PsiElement = {
//    val newElementCopy = newElement.copy
//    getParent.getNode.replaceChild(getNode, newElementCopy.getNode)
//    newElementCopy
//  }
//
//  override def delete() {
//    getParent match {
//      case x: LazyParseablePsiElement =>
//        CheckUtil.checkWritable(this)
//        x.deleteChildInternal(getNode)
//      case _ => super.delete()
//    }
//  }
//}

//abstract class JawaStubBasedElementImpl[T <: PsiElement](stub: StubElement[T], nodeType: IElementType, node: ASTNode)
//  extends JawaStubBaseElementImplJavaRawTypeHack[T](stub, nodeType, node) with JawaPsiElementWrapper {
//
//  override def getContext: PsiElement = {
//    context match {
//      case null => super.getContext
//      case _ => context
//    }
//  }
//
//  override def getStartOffsetInParent: Int = {
//    child match {
//      case null => super.getStartOffsetInParent
//      case _ => child.getStartOffsetInParent
//    }
//  }
//
//
//  override def getPrevSibling: PsiElement = {
//    child match {
//      case null => super.getPrevSibling
//      case _ => JawaPsiUtil.getPrevStubOrPsiElement(child)
//    }
//  }
//
//  override def getNextSibling: PsiElement = {
//    child match {
//      case null => super.getNextSibling
//      case _ => JawaPsiUtil.getNextStubOrPsiElement(child)
//    }
//  }
//
//  override def getParent: PsiElement = {
//    val stub = getStub
//    if (stub != null) {
//      return stub.getParentStub.getPsi
//    }
//    inReadAction {
//      SharedImplUtil.getParent(getNode)
//    }
//  }
//
//  def getLastChildStub: PsiElement = {
//    val stub = getStub
//    if (stub != null) {
//      val children = stub.getChildrenStubs
//      if (children.size() == 0) return null
//      return children.get(children.size() - 1).getPsi
//    }
//    getLastChild
//  }
//
//  override def delete() {
//    getParent match {
//      case x: LazyParseablePsiElement =>
//        CheckUtil.checkWritable(this)
//        x.deleteChildInternal(getNode)
//      case _ => super.delete()
//    }
//  }
//}