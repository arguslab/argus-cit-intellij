/*
 * Copyright (c) 2016. Fengguo Wei and others.
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
  * Created by fgwei on 6/22/16.
  */
class JavaIdentifier(jawaId : PsiElement) extends LightIdentifier(jawaId.getManager, jawaId.getText) {
  override def getTextRange = jawaId.getTextRange

  override def getStartOffsetInParent: Int = jawaId.getStartOffsetInParent

  override def getTextOffset: Int = jawaId.getTextOffset

  override def getContainingFile = jawaId.getContainingFile
}