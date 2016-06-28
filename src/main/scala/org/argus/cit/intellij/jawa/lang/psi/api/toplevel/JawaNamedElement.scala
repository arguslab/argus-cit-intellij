/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.api.toplevel

import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.stubs.NamedStub
import com.intellij.psi._
import com.intellij.psi.search.SearchScope
import com.intellij.psi.util.PsiTreeUtil
import org.argus.cit.intellij.jawa.lang.psi.JawaPsiElement
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaNamedElement extends JawaPsiElement with PsiNameIdentifierOwner with NavigatablePsiElement {
  def name: String
  def nameId: PsiElement
}
