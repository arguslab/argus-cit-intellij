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
import org.argus.cit.intellij.jawa.lang.psi.JawaExtendsAndImplementsClause
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaExtendsAndImplementsClauseImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaExtendsAndImplementsClauseStub
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaExtendsAndImplementsClausesStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaExtendsClauseElementType(debugName: String) extends JawaStubElementType[JawaExtendsAndImplementsClauseStub, JawaExtendsAndImplementsClause](debugName) {
  def serialize(stub: JawaExtendsAndImplementsClauseStub, dataStream: StubOutputStream) {
    dataStream.writeName(stub.getJawaTypeText)
  }

  def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaExtendsAndImplementsClauseStub = {
    val jawatypeText = dataStream.readName()
    new JawaExtendsAndImplementsClausesStubImpl(parentStub.asInstanceOf[StubElement[PsiElement]], this, jawatypeText)
  }

  def createPsi(stub: JawaExtendsAndImplementsClauseStub): JawaExtendsAndImplementsClause = {
    new JawaExtendsAndImplementsClauseImpl(stub, this)
  }

  def createStubImpl[ParentPsi <: PsiElement](psi: JawaExtendsAndImplementsClause, parentStub: StubElement[ParentPsi]): JawaExtendsAndImplementsClauseStub = {
    val jawatypeText: String = psi.getJawaType.jawaName
    new JawaExtendsAndImplementsClausesStubImpl(parentStub, this, jawatypeText)
  }

  override def indexStub(t: JawaExtendsAndImplementsClauseStub, indexSink: IndexSink): Unit = {}
}
