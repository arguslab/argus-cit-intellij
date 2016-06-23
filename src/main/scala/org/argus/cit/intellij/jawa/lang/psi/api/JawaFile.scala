/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.api

import com.intellij.psi.PsiClassOwnerEx
import com.intellij.psi.impl.source.PsiFileWithStubSupport
import org.argus.cit.intellij.jawa.lang.psi.JawaPsiElement
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaToplevelElement
import org.jetbrains.annotations.Nullable

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaFile extends JawaPsiElement with JawaToplevelElement with PsiClassOwnerEx with PsiFileWithStubSupport {
  def getPackageName: String

  @Nullable
  def packageName: String

  def isCompiled: Boolean

  def sourceName: String
}
