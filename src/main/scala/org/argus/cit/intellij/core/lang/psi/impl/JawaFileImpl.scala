/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.lang.psi.impl

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.argus.cit.intellij.core.lang.{JawaFileType, JawaLanguage}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFileImpl(viewProvider: FileViewProvider) extends PsiFileBase(viewProvider, JawaLanguage.Instance) {
  override def getFileType: FileType = JawaFileType.INSTANCE
  override def toString: String = "Jawa File"
  override def getIcon(flags: Int) = super.getIcon(flags)
}
