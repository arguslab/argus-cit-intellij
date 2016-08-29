/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.structureview.presentations

import javax.swing.Icon

import com.intellij.navigation.ColoredItemPresentation
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class JawaItemPresentation(protected val element: PsiElement) extends ColoredItemPresentation {
  def getLocationString: String = null

  def getIcon(open: Boolean): Icon = element.getIcon(0)

  def getTextAttributesKey: TextAttributesKey = null
}