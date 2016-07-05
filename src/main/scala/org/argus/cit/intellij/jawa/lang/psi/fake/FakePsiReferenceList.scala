/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.fake

import com.intellij.lang.Language
import com.intellij.psi._
import com.intellij.psi.impl.light.LightElement

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class FakePsiReferenceList(manager: PsiManager, language: Language, role: PsiReferenceList.Role) extends LightElement(manager, language) with PsiReferenceList {
  override def getText: String = ""

  def getRole: PsiReferenceList.Role = role

  def getReferenceElements: Array[PsiJavaCodeReferenceElement] = PsiJavaCodeReferenceElement.EMPTY_ARRAY

  def getReferencedTypes: Array[PsiClassType] = PsiClassType.EMPTY_ARRAY

  override def toString: String = ""

  override def copy: PsiElement = new FakePsiReferenceList(manager, language, role)
}