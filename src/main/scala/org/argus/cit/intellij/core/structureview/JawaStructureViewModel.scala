/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.structureview

import com.intellij.ide.structureView.{StructureViewModel, StructureViewModelBase, StructureViewTreeElement}
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiFile
import org.argus.cit.intellij.core.lang.psi.impl.JawaFileImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">FenggWei</a>
  */
class JawaStructureViewModel(psiFile: PsiFile) extends StructureViewModelBase(psiFile, new JawaStructureViewElement(psiFile)) with StructureViewModel.ElementInfoProvider {

  override def getSorters: Array[Sorter] = List(Sorter.ALPHA_SORTER).toArray

  override def isAlwaysLeaf(element: StructureViewTreeElement): Boolean = element.isInstanceOf[JawaFileImpl]

  override def isAlwaysShowsPlus(structureViewTreeElement: StructureViewTreeElement): Boolean = false
}
