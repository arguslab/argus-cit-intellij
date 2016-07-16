/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.{FoldingBuilderEx, FoldingDescriptor}
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.argus.cit.intellij.jawa.lang.psi.{JawaJwBody, JawaInstanceFieldDeclarationBlock}
import org.sireum.util._

import scala.collection.JavaConversions._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFoldingBuilder extends FoldingBuilderEx {
  override def isCollapsedByDefault(astNode: ASTNode): Boolean = false

  override def buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array[FoldingDescriptor] = {
    val descriptors: MSet[FoldingDescriptor] = msetEmpty
    val ifblocks = PsiTreeUtil.findChildrenOfType(root, classOf[JawaInstanceFieldDeclarationBlock])
    val bodys = PsiTreeUtil.findChildrenOfType(root, classOf[JawaJwBody])
    ifblocks.foreach { ifblock=>
      if(ifblock.getTextRange.getEndOffset - 1 > ifblock.getTextRange.getStartOffset + 1)
        descriptors.add(new FoldingDescriptor(
          ifblock.getNode, new TextRange(ifblock.getTextRange.getStartOffset + 1, ifblock.getTextRange.getEndOffset - 1)
        ))
    }
    bodys.foreach{ body =>
      if(body.getTextRange.getEndOffset - 1 > body.getTextRange.getStartOffset + 1)
        descriptors.add(new FoldingDescriptor(
          body.getNode, new TextRange(body.getTextRange.getStartOffset + 1, body.getTextRange.getEndOffset - 1)
        ))
    }
    descriptors.toArray
  }

  override def getPlaceholderText(astNode: ASTNode): String = "..."
}
