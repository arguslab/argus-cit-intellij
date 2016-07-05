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
import org.argus.cit.intellij.jawa.lang.psi.JawaFieldDeclaration
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaFieldStub

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFieldStubImpl[ParentPsi <: PsiElement](parent: StubElement[ParentPsi],
                                                 elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement])
  extends StubBaseWrapper[JawaFieldDeclaration](parent, elemType) with JawaFieldStub {

  private var name: StringRef = _
  private var FQN: StringRef = _

  def this(parent: StubElement[ParentPsi],
           elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
           name: String, FQN: String) = {
    this(parent, elemType.asInstanceOf[IStubElementType[StubElement[PsiElement], PsiElement]])
    this.name = StringRef.fromString(name)
    this.FQN = StringRef.fromString(FQN)
  }

  def this(parent: StubElement[ParentPsi],
           elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
           name: StringRef, FQN: StringRef) = {
    this(parent, elemType.asInstanceOf[IStubElementType[StubElement[PsiElement], PsiElement]])
    this.name = name
    this.FQN = FQN
  }

  override def getName: String = StringRef.toString(name)
  override def getFQN: String = StringRef.toString(FQN)
}
