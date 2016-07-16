/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs

import com.intellij.psi.stubs.{DefaultStubBuilder, StubElement}
import com.intellij.psi.{PsiElement, PsiFile}
import com.intellij.util.io.StringRef
import org.argus.cit.intellij.jawa.lang.JawaLanguage
import org.argus.cit.intellij.jawa.lang.psi.api.JawaFile
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaFileStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFileStubBuilder extends DefaultStubBuilder {
  protected override def createStubForFile(file: PsiFile): StubElement[_ <: PsiElement] = {
    val s: JawaFile = file.getViewProvider.getPsi(JawaLanguage.Instance).asInstanceOf[JawaFile]
    new JawaFileStubImpl(s, StringRef.fromString(s.packageName), StringRef.fromString(s.sourceName), s.isCompiled)
  }
}
