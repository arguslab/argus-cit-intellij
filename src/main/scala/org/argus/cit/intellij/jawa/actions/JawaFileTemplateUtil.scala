/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.actions

import java.util.Properties

import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.psi.{PsiClass, PsiMethod}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaFileTemplateUtil {
  val JAWA_INTERFACE = "Jawa Interface"
  val JAWA_CLASS = "Jawa Class"

  def setClassAndMethodNameProperties(properties: Properties, aClass: PsiClass, method: PsiMethod) {
    var className: String = aClass.getQualifiedName
    if (className == null) className = ""
    properties.setProperty(FileTemplate.ATTRIBUTE_CLASS_NAME, className)
    var classSimpleName: String = aClass.getName
    if (classSimpleName == null) classSimpleName = ""
    properties.setProperty(FileTemplate.ATTRIBUTE_SIMPLE_CLASS_NAME, classSimpleName)
    val methodName: String = method.getName
    properties.setProperty(FileTemplate.ATTRIBUTE_METHOD_NAME, methodName)
  }
}
