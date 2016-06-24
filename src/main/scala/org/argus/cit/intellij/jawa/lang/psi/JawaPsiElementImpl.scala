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
import com.intellij.psi.tree.{IElementType, TokenSet}
import com.intellij.psi.{PsiElement, PsiElementVisitor}
import org.argus.cit.intellij.jawa.extensions.inReadAction
import org.argus.cit.intellij.jawa.lang.psi.api.JawaFile

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class JawaPsiElementImpl(node: ASTNode) extends ASTWrapperPsiElement(node) with JawaPsiElement {
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

  override def accept(visitor: PsiElementVisitor) {
    visitor match {
      case visitor: JawaVisitor => super.accept(visitor)
      case _ => super.accept(visitor)
    }
  }

  /**
    * Override in inheritors
    */
  def acceptChildren(visitor: JawaVisitor) {
    for (c <- getChildren; if c.isInstanceOf[JawaPsiElement]) {
      c.asInstanceOf[JawaPsiElement].accept(visitor)
    }
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

  protected def findLastChild[T >: Null <: JawaPsiElement](clazz: Class[T]): Option[T] = {
    var child = getLastChild
    while (child != null && !clazz.isInstance(child)) {
      child = child.getPrevSibling
    }
    if (child == null) None else Some(child.asInstanceOf[T])
  }

  def findLastChildByType(set: TokenSet) = {
    var node = getNode.getLastChildNode
    while (node != null && !set.contains(node.getElementType)) {
      node = node.getTreePrev
    }
    if (node == null) null else node.getPsi
  }

  override def getContext: PsiElement = {
    context match {
      case null => super.getContext
      case _ => context
    }
  }

  override def getStartOffsetInParent: Int = {
    child match {
      case null => super.getStartOffsetInParent
      case _ => child.getStartOffsetInParent
    }
  }

  override def getPrevSibling: PsiElement = {
    child match {
      case null => super.getPrevSibling
      case _ => child.getPrevSibling
    }
  }

  override def getNextSibling: PsiElement = {
    child match {
      case null => super.getNextSibling
      case _ => child.getNextSibling
    }
  }

  override def findLastChildByType[T <: PsiElement](t: IElementType): T = {
    var node = getNode.getLastChildNode
    while (node != null && node.getElementType != t) {
      node = node.getTreePrev
    }
    if (node == null) null.asInstanceOf[T]
    else node.getPsi.asInstanceOf[T]
  }

  override def findFirstChildByType(t: IElementType) = {
    var node = getNode.getFirstChildNode
    while (node != null && node.getElementType != t) {
      node = node.getTreeNext
    }
    if (node == null) null else node.getPsi
  }

  protected def findChild[T >: Null <: JawaPsiElement](clazz: Class[T]): Option[T] = findChildByClassJawa(clazz) match {
    case null => None
    case e => Some(e)
  }

  protected def findChildrenByClassJawa[T >: Null <: JawaPsiElement](clazz: Class[T]): Array[T] =
    findChildrenByClass[T](clazz)

  protected def findChildByClassJawa[T >: Null <: JawaPsiElement](clazz: Class[T]): T = findChildByClass[T](clazz)

  // todo override in more specific cases
  override def replace(newElement: PsiElement): PsiElement = {
    val newElementCopy = newElement.copy
    getParent.getNode.replaceChild(getNode, newElementCopy.getNode)
    newElementCopy
  }

  override def delete() {
    getParent match {
      case x: LazyParseablePsiElement =>
        CheckUtil.checkWritable(this)
        x.deleteChildInternal(getNode)
      case _ => super.delete()
    }
  }
}

abstract class JawaStubBasedElementImpl[T <: PsiElement](stub: StubElement[T], nodeType: IElementType, node: ASTNode)
  extends JawaStubBaseElementImplJavaRawTypeHack[T](stub, nodeType, node) with JawaPsiElement {

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

  override def accept(visitor: PsiElementVisitor) {
    visitor match {
      case visitor: JawaVisitor => super.accept(visitor)
      case _ => super.accept(visitor)
    }
  }

  /**
    * Override in inheritors
    */
  def acceptChildren(visitor: JawaVisitor) {
    for (c <- getChildren; if c.isInstanceOf[JawaPsiElement]) {
      c.asInstanceOf[JawaPsiElement].accept(visitor)
    }
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

  protected def findChild[T >: Null <: JawaPsiElement](clazz: Class[T]): Option[T] = findChildByClassJawa(clazz) match {
    case null => None
    case e => Some(e)
  }

  protected def findLastChild[T >: Null <: JawaPsiElement](clazz: Class[T]): Option[T] = {
    var child = getLastChild
    while (child != null && !clazz.isInstance(child)) {
      child = child.getPrevSibling
    }
    if (child == null) None else Some(child.asInstanceOf[T])
  }

  override def getContext: PsiElement = {
    context match {
      case null => super.getContext
      case _ => context
    }
  }

  override def getStartOffsetInParent: Int = {
    child match {
      case null => super.getStartOffsetInParent
      case _ => child.getStartOffsetInParent
    }
  }


  override def getPrevSibling: PsiElement = {
    child match {
      case null => super.getPrevSibling
      case _ => JawaPsiUtil.getPrevStubOrPsiElement(child)
    }
  }

  override def getNextSibling: PsiElement = {
    child match {
      case null => super.getNextSibling
      case _ => JawaPsiUtil.getNextStubOrPsiElement(child)
    }
  }

  override def getParent: PsiElement = {
    val stub = getStub
    if (stub != null) {
      return stub.getParentStub.getPsi
    }
    inReadAction {
      SharedImplUtil.getParent(getNode)
    }
  }

  def getLastChildStub: PsiElement = {
    val stub = getStub
    if (stub != null) {
      val children = stub.getChildrenStubs
      if (children.size() == 0) return null
      return children.get(children.size() - 1).getPsi
    }
    getLastChild
  }

  override def findLastChildByType[T <: PsiElement](t: IElementType): T = {
    var node = getNode.getLastChildNode
    while (node != null && node.getElementType != t) {
      node = node.getTreePrev
    }
    if (node == null) null.asInstanceOf[T]
    else node.getPsi.asInstanceOf[T]
  }

  def findLastChildByType(set: TokenSet) = {
    var node = getNode.getLastChildNode
    while (node != null && !set.contains(node.getElementType)) {
      node = node.getTreePrev
    }
    if (node == null) null else node.getPsi
  }

  override def findFirstChildByType(t: IElementType) = {
    var node = getNode.getFirstChildNode
    while (node != null && node.getElementType != t) {
      node = node.getTreeNext
    }
    if (node == null) null else node.getPsi
  }

  protected def findChildrenByClassJawa[T >: Null <: JawaPsiElement](clazz: Class[T]): Array[T] =
    findChildrenByClass[T](clazz)

  protected def findChildByClassJawa[T >: Null <: JawaPsiElement](clazz: Class[T]): T = findChildByClass[T](clazz)

  override def delete() {
    getParent match {
      case x: LazyParseablePsiElement =>
        CheckUtil.checkWritable(this)
        x.deleteChildInternal(getNode)
      case _ => super.delete()
    }
  }
}