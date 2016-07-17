/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.elements

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.cache.RecordUtil
import com.intellij.psi.impl.java.stubs.index.JavaStubIndexKeys
import com.intellij.psi.stubs.{IndexSink, StubElement, StubInputStream, StubOutputStream}
import org.argus.cit.intellij.jawa.lang.psi.JawaFieldDeclaration
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaFieldDeclarationImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.{JawaFieldStub, JawaStubElementTypes}
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaFieldStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFieldDeclarationElementType(debugName: String) extends JawaStubElementType[JawaFieldStub, JawaFieldDeclaration](debugName) {
  override def createStubImpl[ParentPsi <: PsiElement](psi: JawaFieldDeclaration, parent: StubElement[ParentPsi]): JawaFieldStub = {
    val fqnText = psi.getFQN
    val typText = psi.getJwType.getJawaType.jawaName
    val flag = psi.getAccessFlagAnnotation.getModifiers
    new JawaFieldStubImpl[ParentPsi](parent, this, psi.name, fqnText, typText, flag)
  }

  def serialize(stub: JawaFieldStub, dataStream: StubOutputStream) {
    dataStream.writeName(stub.getName)
    dataStream.writeName(stub.getFQN)
    dataStream.writeName(stub.getType.jawaName)
    dataStream.writeInt(stub.getFlag)
  }

  override def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaFieldStub = {
    val name = dataStream.readName
    val fqnText = dataStream.readName
    val typText = dataStream.readName
    val flag = dataStream.readInt
    val parent = parentStub.asInstanceOf[StubElement[PsiElement]]
    new JawaFieldStubImpl(parent, this, name, fqnText, typText, flag)
  }

  def indexStub(stub: JawaFieldStub, sink: IndexSink) = {
    val name = stub.getName
    if (name != null) {
      sink.occurrence(JavaStubIndexKeys.FIELDS, name)
      if (RecordUtil.isStaticNonPrivateMember(stub)) {
        sink.occurrence(JavaStubIndexKeys.JVM_STATIC_MEMBERS_NAMES, name)
        sink.occurrence(JavaStubIndexKeys.JVM_STATIC_MEMBERS_TYPES, stub.getType.simpleName)
      }
    }
  }

  def createElement(node: ASTNode): PsiElement = new JawaFieldDeclarationImpl(node)

  def createPsi(stub: JawaFieldStub) = new JawaFieldDeclarationImpl(stub, JawaStubElementTypes.FIELD)
}

