/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.api.base

import com.intellij.psi.PsiModifierList
import com.intellij.psi.tree.IElementType
import org.argus.cit.intellij.jawa.lang.psi.{JawaAccessFlagAnnotation, JawaPsiElement}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaModifierList extends JawaPsiElement with PsiModifierList {
  def has(prop : IElementType) : Boolean

  //only one access modifier can occur in a particular modifier list
  def accessModifier: JawaAccessFlagAnnotation

  def getModifiersStrings: Array[String]

  def hasExplicitModifiers: Boolean
}
