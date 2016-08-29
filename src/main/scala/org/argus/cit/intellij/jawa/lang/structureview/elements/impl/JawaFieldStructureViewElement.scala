/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.structureview.elements.impl

import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import org.argus.cit.intellij.jawa.lang.psi.JawaFieldDeclaration
import org.argus.cit.intellij.jawa.lang.structureview.elements.JawaStructureViewElement
import org.argus.cit.intellij.jawa.lang.structureview.presentations.impl.JawaFieldItemPresentation
import org.sireum.util._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFieldStructureViewElement(element: JawaFieldDeclaration) extends JawaStructureViewElement(element, false) {
  override def getPresentation: ItemPresentation = new JawaFieldItemPresentation(element)
  override def getChildren: Array[TreeElement] = msetEmpty.toArray
}