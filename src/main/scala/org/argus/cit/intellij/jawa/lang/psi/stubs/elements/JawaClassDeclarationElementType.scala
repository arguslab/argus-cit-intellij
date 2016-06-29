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
import com.intellij.psi.{PsiClass, PsiElement}
import com.intellij.psi.impl.java.stubs.index.JavaStubIndexKeys
import com.intellij.psi.stubs.{IndexSink, StubElement, StubInputStream, StubOutputStream}
import com.intellij.util.io.StringRef
import org.argus.cit.intellij.jawa.lang.psi.JawaClassOrInterfaceDeclaration
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaClassOrInterfaceDeclarationImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.{JawaClassOrInterfaceStub, JawaStubElementTypes}
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaClassOrInterfaceStubImpl

import collection.JavaConversions._

/**
  *  @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaClassDeclarationElementType(debugName: String) extends JawaStubElementType[JawaClassOrInterfaceStub, JawaClassOrInterfaceDeclaration](debugName) {
  override def createStubImpl[ParentPsi <: PsiElement](psi: JawaClassOrInterfaceDeclaration, parent: StubElement[ParentPsi]): JawaClassOrInterfaceStub = {
    val file = psi.getContainingFile
    val methodNames = psi.getMethodDeclarationList.map {
      md => md.getSignatureAnnotation.getSignatureSymbol.getSignature.methodName
    }.toArray
    val fileName = if (file != null && file.getVirtualFile != null) file.getVirtualFile.getName else null
    val javaName = psi.getName
    new JawaClassOrInterfaceStubImpl[ParentPsi](parent, this, psi.name, psi.getQualifiedName, fileName, methodNames, javaName)
  }

  def serialize(stub: JawaClassOrInterfaceStub, dataStream: StubOutputStream) {
    dataStream.writeName(stub.getName)
    dataStream.writeName(stub.javaQualName)
    dataStream.writeName(stub.sourceFileName)
    val methodNames = stub.methodNames
    dataStream.writeInt(methodNames.length)
    for (name <- methodNames) dataStream.writeName(name)
    dataStream.writeBoolean(stub.isDeprecated)
    dataStream.writeName(stub.javaName)
  }

  override def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaClassOrInterfaceStub = {
    val name = dataStream.readName
    val javaQualName = dataStream.readName
    val fileName = dataStream.readName
    val length = dataStream.readInt
    val methodNames = new Array[StringRef](length)
    for (i <- 0 until length) methodNames(i) = dataStream.readName
    val parent = parentStub.asInstanceOf[StubElement[PsiElement]]
    val javaName = dataStream.readName
    new JawaClassOrInterfaceStubImpl(parent, this, name, javaQualName, fileName, methodNames,
      javaName)
  }

  def indexStub(stub: JawaClassOrInterfaceStub, sink: IndexSink) = {
    val javaName = stub.javaName
    sink.occurrence(JavaStubIndexKeys.CLASS_SHORT_NAMES, javaName)

    val javaFqn = stub.javaQualName
    if (javaFqn != null) {
      sink.occurrence[PsiClass, java.lang.Integer](JavaStubIndexKeys.CLASS_FQN, javaFqn.hashCode)
    }
  }

  def createElement(node: ASTNode): PsiElement = new JawaClassOrInterfaceDeclarationImpl(node)

  def createPsi(stub: JawaClassOrInterfaceStub) = new JawaClassOrInterfaceDeclarationImpl(stub, JawaStubElementTypes.CLASS)
}
