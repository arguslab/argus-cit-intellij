/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.structureview.presentations.impl

import org.argus.cit.intellij.jawa.lang.psi.JawaFieldDeclaration
import org.argus.cit.intellij.jawa.lang.structureview.presentations.JawaItemPresentation
import org.argus.jawa.core.JavaKnowledge

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFieldItemPresentation(element: JawaFieldDeclaration) extends JawaItemPresentation(element) {
  def getPresentableText: String = {
    val fieldName = JavaKnowledge.getFieldNameFromFieldFQN(element.getFQN)
    val fieldType = element.getJwType.getJawaType.simpleName
    s"$fieldName: $fieldType"
  }
}