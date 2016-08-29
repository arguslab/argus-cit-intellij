/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{IStubElementType, StubElement}
import com.intellij.util.io.StringRef
import org.argus.cit.intellij.jawa.lang.psi.JawaJwType
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaJwTypeStub
import org.argus.jawa.core.JawaType

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaJwTypeStubImpl[ParentPsi <: PsiElement](parent: StubElement[ParentPsi],
                                                  elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement])
  extends StubBaseWrapper[JawaJwType](parent, elemType) with JawaJwTypeStub {

  private var jawatypeText: StringRef = _

  def this(parent: StubElement[ParentPsi],
           elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
           jawatypeText: String) = {
    this(parent, elemType.asInstanceOf[IStubElementType[StubElement[PsiElement], PsiElement]])
    this.jawatypeText = StringRef.fromString(jawatypeText)
  }

  def this(parent: StubElement[ParentPsi],
           elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
           jawatypeText: StringRef) = {
    this(parent, elemType.asInstanceOf[IStubElementType[StubElement[PsiElement], PsiElement]])
    this.jawatypeText = jawatypeText
  }

  override def getJawaTypeText: String = StringRef.toString(jawatypeText)

  override def getJawaType: JawaType = new JawaType(getJawaTypeText)
}
