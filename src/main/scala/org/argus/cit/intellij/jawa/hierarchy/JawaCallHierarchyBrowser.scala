/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.hierarchy

import java.util
import java.util.Comparator
import javax.swing.{JComponent, JTree}

import com.intellij.ide.hierarchy.CallHierarchyBrowserBase._
import com.intellij.ide.hierarchy.call.CallHierarchyNodeDescriptor
import com.intellij.ide.hierarchy.{CallHierarchyBrowserBase, HierarchyNodeDescriptor, HierarchyTreeStructure, JavaHierarchyUtil}
import com.intellij.ide.util.treeView.NodeDescriptor
import com.intellij.openapi.actionSystem._
import com.intellij.openapi.project.Project
import com.intellij.psi.{PsiElement, PsiMethod}
import com.intellij.ui.PopupHandler

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
final class JawaCallHierarchyBrowser(project: Project, method: PsiMethod)
  extends CallHierarchyBrowserBase(project, method) {
  protected def createTrees(type2TreeMap: util.Map[String, JTree]): Unit = {
    val group: ActionGroup = ActionManager.getInstance.getAction(IdeActions.GROUP_CALL_HIERARCHY_POPUP).asInstanceOf[ActionGroup]
    val tree1: JTree = createTree(false)
    PopupHandler.installPopupHandler(tree1, group, ActionPlaces.CALL_HIERARCHY_VIEW_POPUP, ActionManager.getInstance)
    val forName: Class[_] = Class.forName("com.intellij.ide.hierarchy.CallHierarchyBrowserBase")
    val classes = forName.getDeclaredClasses
    var baseClass: Class[_] = null
    for (clazz <- classes if clazz.getName endsWith "BaseOnThisMethodAction") baseClass = clazz
    val constructor = baseClass.getConstructor()
    val inst: Any = constructor.newInstance()
    val method = baseClass.getMethod("registerCustomShortcutSet", classOf[ShortcutSet], classOf[JComponent])
    method.invoke(inst, ActionManager.getInstance.getAction(IdeActions.ACTION_CALL_HIERARCHY).getShortcutSet, tree1)
    type2TreeMap.put(CALLEE_TYPE, tree1)
    val tree2: JTree = createTree(false)
    PopupHandler.installPopupHandler(tree2, group, ActionPlaces.CALL_HIERARCHY_VIEW_POPUP, ActionManager.getInstance)
    method.invoke(inst, ActionManager.getInstance.getAction(IdeActions.ACTION_CALL_HIERARCHY).getShortcutSet, tree2)
    type2TreeMap.put(CALLER_TYPE, tree2)
  }

  protected def getElementFromDescriptor(descriptor: HierarchyNodeDescriptor): PsiElement = {
    descriptor match {
      case nodeDescriptor: CallHierarchyNodeDescriptor => nodeDescriptor.getEnclosingElement
      case _ => null
    }
  }

  protected override def getOpenFileElementFromDescriptor(descriptor: HierarchyNodeDescriptor): PsiElement = {
    descriptor match {
      case nodeDescriptor: CallHierarchyNodeDescriptor => nodeDescriptor.getTargetElement
      case _ => null
    }
  }

  protected def isApplicableElement(element: PsiElement): Boolean = {
    element.isInstanceOf[PsiMethod]
  }

  protected def createHierarchyTreeStructure(typeName: String, psiElement: PsiElement): HierarchyTreeStructure = {
    if (CALLER_TYPE.equals(typeName))
      new JawaCallerMethodsTreeStructure(myProject, psiElement.asInstanceOf[PsiMethod], getCurrentScopeType)
    else if (CALLEE_TYPE.equals(typeName))
      new JawaCalleeMethodsTreeStructure(myProject, psiElement.asInstanceOf[PsiMethod], getCurrentScopeType)
    else null
  }

  protected def getComparator: Comparator[NodeDescriptor[_]] = {
    JavaHierarchyUtil.getComparator(myProject)
  }
}