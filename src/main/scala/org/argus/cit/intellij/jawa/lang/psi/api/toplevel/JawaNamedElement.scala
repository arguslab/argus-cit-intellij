/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.api.toplevel

import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.stubs.NamedStub
import com.intellij.psi._
import com.intellij.psi.search.SearchScope
import com.intellij.psi.util.PsiTreeUtil
import org.argus.cit.intellij.jawa.lang.psi.JawaPsiElement
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaNamedElement extends JawaPsiElement with PsiNameIdentifierOwner with NavigatablePsiElement {
  def name: String = {
    this match {
      case st: StubBasedPsiElement[_] =>  st.getStub match {
        case namedStub: NamedStub[_] => namedStub.getName
        case _ => nameInner
      }
      case _ => nameInner
    }
  }

  def name_=(it: String) {
    setName(it)
  }

  def nameInner: String = nameId.getText

  override def getTextOffset: Int = nameId.getTextRange.getStartOffset

  override def getName = ScalaNamesUtil.toJavaName(name)

  def nameId: PsiElement

  override def getNameIdentifier: PsiIdentifier = if (nameId != null) new JavaIdentifier(nameId) else null

  override def setName(name: String): PsiElement = {
    val id = nameId.getNode
    val parent = id.getTreeParent
    val newId = ScalaPsiElementFactory.createIdentifier(name, getManager)
    parent.replaceChild(id, newId)
    this
  }

  override def getPresentation: ItemPresentation = {
    val clazz: ScTemplateDefinition =
      getParent match {
        case _: ScTemplateBody | _: ScEarlyDefinitions =>
          PsiTreeUtil.getParentOfType(this, classOf[ScTemplateDefinition], true)
        case _ if this.isInstanceOf[ScClassParameter]  =>
          PsiTreeUtil.getParentOfType(this, classOf[ScTemplateDefinition], true)
        case _ => null
      }

    val parentMember: ScMember = PsiTreeUtil.getParentOfType(this, classOf[ScMember], false)
    new ItemPresentation {
      def getPresentableText: String = name
      def getTextAttributesKey: TextAttributesKey = null
      def getLocationString: String = clazz match {
        case _: ScTypeDefinition => "(" + clazz.qualifiedName + ")"
        case x: ScNewTemplateDefinition => "(<anonymous>)"
        case _ => ""
      }
      override def getIcon(open: Boolean) = parentMember match {case mem: ScMember => mem.getIcon(0) case _ => null}
    }
  }

  override def getIcon(flags: Int) =
    JawaPsiUtil.nameContext(this) match {
      case null => null
      case c: ScCaseClause => Icons.PATTERN_VAL
      case x => x.getIcon(flags)
    }

  abstract override def getUseScope: SearchScope = super.getUseScope
}
