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

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{IndexSink, StubElement, StubInputStream, StubOutputStream}
import org.argus.cit.intellij.jawa.lang.psi.JawaJwType
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaJwTypeImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.{JawaJwTypeStub, JawaStubElementTypes}
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaJwTypeStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaJwTypeElementType(debugName: String) extends JawaStubElementType[JawaJwTypeStub, JawaJwType](debugName) {
  def serialize(stub: JawaJwTypeStub, dataStream: StubOutputStream) {
    dataStream.writeName(stub.getJawaTypeText)
  }

  def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaJwTypeStub = {
    val jawatypeText = dataStream.readName()
    new JawaJwTypeStubImpl(parentStub.asInstanceOf[StubElement[PsiElement]], this, jawatypeText)
  }

  def createPsi(stub: JawaJwTypeStub): JawaJwType = {
    new JawaJwTypeImpl(stub, JawaStubElementTypes.TYPE)
  }

  def createStubImpl[ParentPsi <: PsiElement](psi: JawaJwType, parentStub: StubElement[ParentPsi]): JawaJwTypeStub = {
    val jawatypeText: String = psi.getJawaType.jawaName
    new JawaJwTypeStubImpl(parentStub, this, jawatypeText)
  }

  override def indexStub(t: JawaJwTypeStub, indexSink: IndexSink): Unit = {}
}
