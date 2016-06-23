/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi

import com.intellij.extapi.psi.StubBasedPsiElementBase
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.{PsiElement, StubBasedPsiElement}
import com.intellij.psi.stubs.{IStubElementType, StubElement}
import com.intellij.psi.tree.IElementType

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class JawaStubBaseElementImplJavaRawTypeHack[T <: PsiElement](stub: StubElement[T], nodeType: IElementType, node: ASTNode)
  extends StubBasedPsiElementBase[StubElement[T]](stub, nodeType, node)
  with StubBasedPsiElement[StubElement[T]] {
  private val LOG: Logger = Logger.getInstance(classOf[JawaStubBaseElementImplJavaRawTypeHack[_ <: PsiElement]])
  override def getElementType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement]  = {
    if (getStub != null) getStub.getStubType
    else getNode.getElementType.asInstanceOf[IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement]]
  }
}
