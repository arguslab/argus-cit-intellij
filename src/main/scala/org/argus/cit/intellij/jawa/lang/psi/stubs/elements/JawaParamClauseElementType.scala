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
import org.argus.cit.intellij.jawa.lang.psi.JawaParamClause
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaParamClauseImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamClauseStub
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaParamClauseStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaParamClauseElementType(debugName: String) extends JawaStubElementType[JawaParamClauseStub, JawaParamClause](debugName) {
  def serialize(stub: JawaParamClauseStub, dataStream: StubOutputStream) {
  }

  def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaParamClauseStub = {
    new JawaParamClauseStubImpl(parentStub.asInstanceOf[StubElement[PsiElement]], this)
  }

  def createPsi(stub: JawaParamClauseStub): JawaParamClause = {
    new JawaParamClauseImpl(stub, this)
  }

  def createStubImpl[ParentPsi <: PsiElement](psi: JawaParamClause, parentStub: StubElement[ParentPsi]): JawaParamClauseStub = {
    new JawaParamClauseStubImpl(parentStub, this)
  }

  override def indexStub(t: JawaParamClauseStub, indexSink: IndexSink): Unit = {}
}
