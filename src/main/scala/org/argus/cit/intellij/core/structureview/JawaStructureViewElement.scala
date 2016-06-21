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

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.{SortableTreeElement, TreeElement}
import com.intellij.navigation.{ItemPresentation, NavigationItem}
import com.intellij.psi.{PsiElement, PsiNamedElement}
import org.argus.cit.intellij.core.lang.psi.impl.JawaFileImpl
import org.sireum.util._

/**
  * @author <a href="mailto:fgwei521@gmail.com">FenggWei</a>
  */
class JawaStructureViewElement(element: PsiElement) extends StructureViewTreeElement with SortableTreeElement {
  override def getValue: AnyRef = element

  override def getAlphaSortKey: String = element match {
    case named: PsiNamedElement => named.getName
    case _ => null
  }

  override def getChildren: Array[TreeElement] = {
    val children: MSet[TreeElement] = msetEmpty
    element match {
      case file: JawaFileImpl =>

      case _ =>
    }
    children.toArray
  }

  override def getPresentation: ItemPresentation = ???

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
