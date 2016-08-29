/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
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
import org.argus.cit.intellij.jawa.lang.psi.JawaTypeSymbol
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaTypeSymbolImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.{JawaStubElementTypes, JawaTypeSymbolStub}
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaTypeSymbolStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaTypeSymbolElementType(debugName: String) extends JawaStubElementType[JawaTypeSymbolStub, JawaTypeSymbol](debugName) {
  def serialize(stub: JawaTypeSymbolStub, dataStream: StubOutputStream) {
    dataStream.writeName(stub.getJawaType.jawaName)
  }

  def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaTypeSymbolStub = {
    val jawatypeText = dataStream.readName()
    new JawaTypeSymbolStubImpl(parentStub.asInstanceOf[StubElement[PsiElement]], this, jawatypeText)
  }

  def createPsi(stub: JawaTypeSymbolStub): JawaTypeSymbol = {
    new JawaTypeSymbolImpl(stub, JawaStubElementTypes.TYPE_SYMBOL)
  }

  def createStubImpl[ParentPsi <: PsiElement](psi: JawaTypeSymbol, parentStub: StubElement[ParentPsi]): JawaTypeSymbolStub = {
    val jawatypeText: String = psi.getJawaType.jawaName
    new JawaTypeSymbolStubImpl(parentStub, this, jawatypeText)
  }

  override def indexStub(t: JawaTypeSymbolStub, indexSink: IndexSink): Unit = {}
}
