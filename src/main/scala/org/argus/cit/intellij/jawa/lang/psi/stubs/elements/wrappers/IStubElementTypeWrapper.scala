/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.elements.wrappers

import java.io.IOException

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{IStubElementType, StubElement, StubInputStream}
import org.argus.cit.intellij.jawa.lang.JawaFileType

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class IStubElementTypeWrapper[StubT <: StubElement[PsiT], PsiT <: PsiElement](debugName: String)
  extends IStubElementType[StubT, PsiT](debugName, JawaFileType.JAWA_LANGUAGE) {

  def createStub(psi: PsiT, parentStub: StubElement[_ <: PsiElement] ): StubT = createStubImpl (psi, parentStub)

  def createStubImpl[ParentPsi <: PsiElement] (psi: PsiT, parentStub: StubElement[ParentPsi] ): StubT

  @throws[IOException]
  def deserialize (dataStream: StubInputStream, parentStub: StubElement[_ <: PsiElement] ): StubT =
    deserializeImpl(dataStream, parentStub)

  def deserializeImpl(dataStream: StubInputStream, parentStub: Any): StubT
}