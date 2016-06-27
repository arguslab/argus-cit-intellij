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

import com.intellij.psi.{PsiClass, PsiElement}
import com.intellij.psi.impl.java.stubs.index.JavaStubIndexKeys
import com.intellij.psi.stubs.{IndexSink, StubElement, StubInputStream, StubOutputStream}
import com.intellij.util.io.StringRef
import org.argus.cit.intellij.jawa.lang.psi.JawaClassOrInterfaceDeclaration
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaClassOrInterfaceStub
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaClassOrInterfaceStubImpl

/**
  *  @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaClassDefinitionElementType(debugName: String) extends JawaStubElementType[JawaClassOrInterfaceStub, JawaClassOrInterfaceDeclaration](debugName) {
  override def createStubImpl[ParentPsi <: PsiElement](psi: JawaClassOrInterfaceDeclaration, parent: StubElement[ParentPsi]): JawaClassOrInterfaceStub = {
    val file = psi.getContainingFile
    val fileName = if (file != null && file.getVirtualFile != null) file.getVirtualFile.getName else null
    val javaName = psi.getName
    new JawaClassOrInterfaceStubImpl[ParentPsi](parent, this, psi.name, psi.qualifiedName, psi.getQualifiedName,
      fileName, javaName)
  }

  def serialize(stub: JawaClassOrInterfaceStub, dataStream: StubOutputStream) {
    dataStream.writeName(stub.getName)
    dataStream.writeName(stub.qualName)
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
    val qualName = dataStream.readName
    val javaQualName = dataStream.readName()
    val fileName = dataStream.readName
    val length = dataStream.readInt
    val methodNames = new Array[StringRef](length)
    for (i <- 0 until length) methodNames(i) = dataStream.readName
    val parent = parentStub.asInstanceOf[StubElement[PsiElement]]
    val javaName = dataStream.readName()
    new JawaClassOrInterfaceStubImpl(parent, this, name, qualName, javaQualName, fileName, methodNames,
      javaName)
  }

  def indexStub(stub: JawaClassOrInterfaceStub, sink: IndexSink) = {
    val name = ScalaNamesUtil.cleanFqn(stub.getName)
    if (name != null) {
      sink.occurrence(ScalaIndexKeys.SHORT_NAME_KEY, name)
    }
    val javaName = ScalaNamesUtil.cleanFqn(stub.javaName)
    sink.occurrence(JavaStubIndexKeys.CLASS_SHORT_NAMES, javaName)
    sink.occurrence(ScalaIndexKeys.ALL_CLASS_NAMES, javaName)

    val javaFqn = ScalaNamesUtil.cleanFqn(stub.javaQualName)
    if (javaFqn != null) {
      sink.occurrence[PsiClass, java.lang.Integer](JavaStubIndexKeys.CLASS_FQN, javaFqn.hashCode)
      val i = javaFqn.lastIndexOf(".")
      val pack =
        if (i == -1) ""
        else javaFqn.substring(0, i)
      sink.occurrence(ScalaIndexKeys.JAVA_CLASS_NAME_IN_PACKAGE_KEY, pack)
    }

    val fqn = ScalaNamesUtil.cleanFqn(stub.qualName)
    if (fqn != null && !stub.isLocal) {
      sink.occurrence[PsiClass, java.lang.Integer](ScalaIndexKeys.FQN_KEY, fqn.hashCode)
      val i = fqn.lastIndexOf(".")
      val pack =
        if (i == -1) ""
        else fqn.substring(0, i)
      sink.occurrence(ScalaIndexKeys.CLASS_NAME_IN_PACKAGE_KEY, pack)
    }
  }
}
