/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.light.LightIdentifier

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JavaIdentifier(jawaId : PsiElement) extends LightIdentifier(jawaId.getManager, jawaId.getText) {
  override def getTextRange = jawaId.getTextRange

  override def getStartOffsetInParent: Int = jawaId.getStartOffsetInParent

  override def getTextOffset: Int = jawaId.getTextOffset

  override def getContainingFile = jawaId.getContainingFile

  override def getParent = jawaId.getParent

  override def getPrevSibling: PsiElement = jawaId.getPrevSibling

  override def getNextSibling: PsiElement = jawaId.getNextSibling

  override def copy: PsiElement = new JavaIdentifier(jawaId)

  override def getNavigationElement: PsiElement = jawaId

  override def isValid: Boolean = jawaId.isValid
}