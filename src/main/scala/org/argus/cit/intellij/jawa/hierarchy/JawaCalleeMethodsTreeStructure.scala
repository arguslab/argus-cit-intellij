/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.hierarchy

import com.intellij.ide.hierarchy.call.CallHierarchyNodeDescriptor
import com.intellij.ide.hierarchy.{HierarchyNodeDescriptor, HierarchyTreeStructure}
import com.intellij.openapi.project.Project
import com.intellij.psi.search.searches.OverridingMethodsSearch
import com.intellij.psi.{PsiElement, PsiMethod, _}
import com.intellij.util.ArrayUtil
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaReference

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaCalleeMethodsTreeStructure(project: Project, method: PsiMethod, myScopeType: String)
  extends HierarchyTreeStructure(project, new CallHierarchyNodeDescriptor(project, null, method, true, false)) {

  protected final def buildChildren(descriptor: HierarchyNodeDescriptor): Array[AnyRef] = {
    val enclosingElement: PsiMember = descriptor.asInstanceOf[CallHierarchyNodeDescriptor].getEnclosingElement
    val method: PsiMethod = enclosingElement match {
      case method: PsiMethod => method
      case _ => return ArrayUtil.EMPTY_OBJECT_ARRAY
    }
    val methods: ArrayBuffer[PsiMethod] = new ArrayBuffer[PsiMethod]
    val body = method.getBody
    JawaCalleeMethodsTreeStructure.visitor(body, methods)
    val baseMethod: PsiMethod = getBaseDescriptor.asInstanceOf[CallHierarchyNodeDescriptor].getTargetElement.asInstanceOf[PsiMethod]
    val baseClass: PsiClass = baseMethod.getContainingClass
    val methodToDescriptorMap: mutable.HashMap[PsiMethod, CallHierarchyNodeDescriptor] =
      new mutable.HashMap[PsiMethod, CallHierarchyNodeDescriptor]
    val result: ArrayBuffer[CallHierarchyNodeDescriptor] = new ArrayBuffer[CallHierarchyNodeDescriptor]
    for (calledMethod <- methods if isInScope(baseClass, calledMethod, myScopeType)) {
      methodToDescriptorMap.get(calledMethod) match {
        case Some(d) => d.incrementUsageCount()
        case _ =>
          val d = new CallHierarchyNodeDescriptor(myProject, descriptor, calledMethod, false, false)
          methodToDescriptorMap.put(calledMethod, d)
          result += d
      }
    }
    val overridingMethods: Array[PsiMethod] =
      OverridingMethodsSearch.search(method, method.getUseScope, true).toArray(PsiMethod.EMPTY_ARRAY)
    for (overridingMethod <- overridingMethods if isInScope(baseClass, overridingMethod, myScopeType)) {
      val node: CallHierarchyNodeDescriptor = new CallHierarchyNodeDescriptor(myProject, descriptor, overridingMethod, false, false)
      if (!result.contains(node)) result += node
    }
    result.toArray
  }


}

object JawaCalleeMethodsTreeStructure {
  private[hierarchy] def visitor(element: PsiElement, methods: ArrayBuffer[PsiMethod]): Unit = {
    if (element == null) return
    element match {
      case ref: JawaReference =>
        val resolve = ref.resolve()
        resolve match {
          case meth: PsiMethod => methods += meth
          case _ =>
        }
      case callExpression: PsiMethodCallExpression =>
        val methodExpression: PsiReferenceExpression = callExpression.getMethodExpression
        val method: PsiMethod = methodExpression.resolve.asInstanceOf[PsiMethod]
        if (method != null) {
          methods += method
        }
      case newExpression: PsiNewExpression =>
        val method: PsiMethod = newExpression.resolveConstructor
        if (method != null) {
          methods += method
        }
      case _ =>
    }
    for (child <- element.getChildren) {
      visitor(child, methods)
    }
  }
}