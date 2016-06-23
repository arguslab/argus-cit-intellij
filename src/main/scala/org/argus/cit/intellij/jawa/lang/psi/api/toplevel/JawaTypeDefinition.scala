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

import com.intellij.navigation.NavigationItem
import com.intellij.psi.impl.PsiClassImplUtil
import com.intellij.psi.{PsiClass, PsiElement}
import org.argus.cit.intellij.jawa.lang.psi.JawaPsiElement

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaTypeDefinition extends JawaPsiElement with PsiClass with NavigationItem {
  override def isEquivalentTo(another: PsiElement): Boolean = {
    PsiClassImplUtil.isClassEquivalentTo(this, another)
  }

}
