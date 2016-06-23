/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.structureview.presentations.impl

import org.argus.cit.intellij.jawa.lang.psi.JawaMethodDeclaration
import org.argus.cit.intellij.jawa.lang.structureview.presentations.JawaItemPresentation

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaMethodItemPresentation(private val element: JawaMethodDeclaration) extends JawaItemPresentation(element) {
  def getPresentableText: String = {
    val sig = element.getSignatureAnnotation.getSignatureSymbol.getSignature
    val methodName = sig.methodName
    val params = sig.getParameterTypes.map(_.simpleName)
    val ret = sig.getReturnType.simpleName
    s"$methodName(${params.mkString(", ")}): $ret"
  }
}