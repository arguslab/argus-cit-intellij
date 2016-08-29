/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.structureview.elements

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.navigation.{ItemPresentation, NavigationItem}
import com.intellij.psi.{PsiElement, PsiNamedElement}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class JawaStructureViewElement(protected val element: PsiElement, val inherited: Boolean) extends StructureViewTreeElement with SortableTreeElement {
  override def getValue: Object = element

  override def getAlphaSortKey: String = element match {
    case named: PsiNamedElement => named.getName
    case _ => null
  }

  override def canNavigateToSource: Boolean = element match {
    case ni: NavigationItem => ni.canNavigateToSource
    case _ => false
  }

  override def canNavigate: Boolean = element match {
    case ni: NavigationItem => ni.canNavigate
    case _ => false
  }

  override def navigate(requestFocus: Boolean): Unit = element match {
    case ni: NavigationItem => ni.navigate(requestFocus)
    case _ =>
  }
}
