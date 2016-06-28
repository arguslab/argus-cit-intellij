/*
 * Copyright (c) 2016. Fengguo Wei and others.
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
import org.argus.cit.intellij.jawa.lang.psi.api.JawaFile
import org.argus.cit.intellij.jawa.lang.structureview.elements.JawaStructureViewElement
import org.argus.cit.intellij.jawa.lang.structureview.presentations.impl.JawaFileItemPresentation

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFileStructureViewElement(file: JawaFile) extends JawaStructureViewElement(file, false) {
  override def getChildren: Array[TreeElement] = {
    file.typeDefinitionsArray.map{
      td =>
        new JawaClassStructureViewElement(td)
    }
  }

  override def getPresentation: ItemPresentation = new JawaFileItemPresentation(file)
}
