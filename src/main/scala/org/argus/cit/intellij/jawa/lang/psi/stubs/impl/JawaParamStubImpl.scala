/*
 * Copyright (c) 2016. Fengguo Wei and others.
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
import org.argus.cit.intellij.jawa.lang.psi.JawaParam
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamStub

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaParamStubImpl[ParentPsi <: PsiElement](parent: StubElement[ParentPsi],
                                                 elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement])
  extends StubBaseWrapper[JawaParam](parent, elemType) with JawaParamStub {
  private var name: String = _
  private var typeText: String = _

  def this(parent: StubElement[ParentPsi],
           elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
           name: String, typeText: String) = {
    this(parent, elemType.asInstanceOf[IStubElementType[StubElement[PsiElement], PsiElement]])
    this.name = name
    this.typeText = typeText
  }

  def this(parent: StubElement[ParentPsi],
           elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
           name: StringRef, typeText: StringRef) = {
    this(parent, elemType.asInstanceOf[IStubElementType[StubElement[PsiElement], PsiElement]])
    this.name = StringRef.toString(name)
    this.typeText = StringRef.toString(typeText)
  }

  def getName: String = name

  def getTypeText: String = typeText
}
