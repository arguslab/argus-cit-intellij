/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.highlighter

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.psi.PsiElement
import org.argus.cit.intellij.core.lang.psi._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object AnnotatorHighlighter {
  def highlightElement(element: PsiElement, holder: AnnotationHolder) = element match {
    case _: JawaTypeDefSymbol | _: JawaTypeSymbol =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.CLASS)
    case _: JawaVarDefSymbol | _: JawaVarSymbol =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.LOCAL_VARIABLES)
    case _: JawaFieldDefSymbol | _: JawaFieldNameSymbol =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.INSTANCE_FIELD)
    case _: JawaStaticFieldDefSymbol | _: JawaStaticFieldNameSymbol =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.STATIC_FIELD)
    case _: JawaMethodDefSymbol =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.METHOD_DECLARATION)
    case _: JawaMethodNameSymbol =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.METHOD_CALL)
    case _: JawaLocationDefSymbol | _: JawaLocationSymbol =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.LOCATION)
    case _: JawaAnnotationKey =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.ANNOTATION)
    case _: JawaNumberLiteral =>
      val annotation = holder.createInfoAnnotation(element, null)
      annotation.setTextAttributes(DefaultHighlighter.NUMBER)
    case _ =>
  }
}
