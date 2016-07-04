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
import org.argus.cit.intellij.jawa.lang.psi.JawaParam
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaParamImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamStub
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaParamStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaParamElementType(debugName: String) extends JawaStubElementType[JawaParamStub, JawaParam](debugName) {
  def serialize(stub: JawaParamStub, dataStream: StubOutputStream) {
    dataStream.writeName(stub.getName)
    dataStream.writeName(stub.getTypeText)
  }

  def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaParamStub = {
    val name = dataStream.readName()
    val jawatypeText = dataStream.readName()
    new JawaParamStubImpl(parentStub.asInstanceOf[StubElement[PsiElement]], this, name, jawatypeText)
  }

  def createPsi(stub: JawaParamStub): JawaParam = {
    new JawaParamImpl(stub, this)
  }

  def createStubImpl[ParentPsi <: PsiElement](psi: JawaParam, parentStub: StubElement[ParentPsi]): JawaParamStub = {
    val name: String = psi.getVarDefSymbol.getId.getText
    val jawatypeText: String = psi.getJwType.getJawaType.jawaName
    new JawaParamStubImpl(parentStub, this, name, jawatypeText)
  }

  override def indexStub(t: JawaParamStub, indexSink: IndexSink): Unit = {}
}
