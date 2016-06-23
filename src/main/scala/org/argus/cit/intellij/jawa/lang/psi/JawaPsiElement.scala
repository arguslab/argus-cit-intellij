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

import com.intellij.psi.PsiElement
import com.intellij.psi.tree.{IElementType, TokenSet}
import org.argus.cit.intellij.jawa.lang.psi.api.JawaFile

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaPsiElement extends PsiElement {
  protected var context: PsiElement = null
  protected var child: PsiElement = null

  def isInCompiledFile = getContainingFile match {
    case file: JawaFile => file.isCompiled
    case _ => false
  }

  def setContext(element: PsiElement, child: PsiElement) {
    context = element
    this.child = child
  }

  def getSameElementInContext: PsiElement = {
    child match {
      case null => this
      case _ => child
    }
  }

  def getDeepSameElementInContext: PsiElement = {
    child match {
      case null => this
      case _ if child == context => this
      case child: JawaPsiElement => child.getDeepSameElementInContext
      case _ => child
    }
  }

  def startOffsetInParent: Int = {
    child match {
      case s: JawaPsiElement => s.startOffsetInParent
      case _ => getStartOffsetInParent
    }
  }

  protected def findChildByClassJawa[T >: Null <: JawaPsiElement](clazz: Class[T]): T

  protected def findChildrenByClassJawa[T >: Null <: JawaPsiElement](clazz: Class[T]): Array[T]

  protected def findChild[T >: Null <: JawaPsiElement](clazz: Class[T]): Option[T] = findChildByClassJawa(clazz) match {
    case null => None
    case e => Some(e)
  }

  def findLastChildByType[T <: PsiElement](t: IElementType): T = {
    var node = getNode.getLastChildNode
    while (node != null && node.getElementType != t) {
      node = node.getTreePrev
    }
    if (node == null) null.asInstanceOf[T]
    else node.getPsi.asInstanceOf[T]
  }

  def findFirstChildByType(t: IElementType) = {
    var node = getNode.getFirstChildNode
    while (node != null && node.getElementType != t) {
      node = node.getTreeNext
    }
    if (node == null) null else node.getPsi
  }

  def findChildrenByType(t: IElementType): List[PsiElement] = {
    val buffer = new collection.mutable.ArrayBuffer[PsiElement]
    var node = getNode.getFirstChildNode
    while (node != null) {
      if (node.getElementType == t) buffer += node.getPsi
      node = node.getTreeNext
    }
    buffer.toList
  }

  def findLastChildByType(set: TokenSet) = {
    var node = getNode.getLastChildNode
    while (node != null && !set.contains(node.getElementType)) {
      node = node.getTreePrev
    }
    if (node == null) null else node.getPsi
  }

  protected def findLastChild[T >: Null <: JawaPsiElement](clazz: Class[T]): Option[T] = {
    var child = getLastChild
    while (child != null && !clazz.isInstance(child)) {
      child = child.getPrevSibling
    }
    if (child == null) None else Some(child.asInstanceOf[T])
  }

  /**
    * Override in inheritors
    */
  def accept(visitor: JawaVisitor) {
    visitor.visitElement(this)
  }

  /**
    * Override in inheritors
    */
  def acceptChildren(visitor: JawaVisitor) {
    for (c <- getChildren; if c.isInstanceOf[JawaPsiElement]) {
      c.asInstanceOf[JawaPsiElement].accept(visitor)
    }
  }
}
