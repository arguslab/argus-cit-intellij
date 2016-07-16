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

import com.intellij.ide.hierarchy.HierarchyBrowser
import com.intellij.ide.hierarchy.`type`.JavaTypeHierarchyProvider
import com.intellij.psi.PsiElement
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaTypeDefinition

import scala.collection.immutable.HashSet

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaTypeHierarchyProvider extends JavaTypeHierarchyProvider {
  override def createHierarchyBrowser(target: PsiElement): HierarchyBrowser = {
    target match {
      case clazz: JawaTypeDefinition =>
        collectSupers(clazz, new HashSet[JawaTypeDefinition])
      case _ =>
    }
    super.createHierarchyBrowser(target)
  }

  def collectSupers(clazz: JawaTypeDefinition, visited: HashSet[JawaTypeDefinition]) {
    clazz.getSupers.foreach {
      case clazz: JawaTypeDefinition =>
        if (visited.contains(clazz)) {
          println("clazz.getText = " + clazz.getText)
        } else {
          collectSupers(clazz, visited + clazz)
        }
      case _ =>
    }
  }
}
