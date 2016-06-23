/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.structureview

import com.intellij.ide.structureView.{StructureViewBuilder, StructureViewModel, TreeBasedStructureViewBuilder}
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaStructureViewFactory extends PsiStructureViewFactory {
  override def getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder = {
    new TreeBasedStructureViewBuilder {
      override def createStructureViewModel(editor: Editor): StructureViewModel = new JawaStructureViewModel(psiFile)
    }
  }
}
