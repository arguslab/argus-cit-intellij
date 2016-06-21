/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.annotator
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.psi.PsiElement
import org.argus.cit.intellij.core.highlighter.AnnotatorHighlighter
import org.argus.cit.intellij.core.lang.psi._

/**
  * @author <a href="mailto:fgwei521@gmail.com">FenggWei</a>
  */
class JawaHighlightingAnnotator extends JawaAnnotator {
  override def annotate(psiElement: PsiElement, annotationHolder: AnnotationHolder): Unit = psiElement.accept(new JawaVisitor{
    override def visitTypeDefSymbol(o: JawaTypeDefSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitTypeDefSymbol(o)
    }
    override def visitTypeSymbol(o:JawaTypeSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitTypeSymbol(o)
    }
    override def visitVarDefSymbol(o: JawaVarDefSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitVarDefSymbol(o)
    }
    override def visitVarSymbol(o:JawaVarSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitVarSymbol(o)
    }
    override def visitFieldDefSymbol(o: JawaFieldDefSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitFieldDefSymbol(o)
    }
    override def visitFieldNameSymbol(o:JawaFieldNameSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitFieldNameSymbol(o)
    }
    override def visitStaticFieldDefSymbol(o: JawaStaticFieldDefSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitStaticFieldDefSymbol(o)
    }
    override def visitStaticFieldNameSymbol(o:JawaStaticFieldNameSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitStaticFieldNameSymbol(o)
    }
    override def visitMethodDefSymbol(o: JawaMethodDefSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitMethodDefSymbol(o)
    }
    override def visitMethodNameSymbol(o:JawaMethodNameSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitMethodNameSymbol(o)
    }
    override def visitLocationDefSymbol(o: JawaLocationDefSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitLocationDefSymbol(o)
    }
    override def visitLocationSymbol(o:JawaLocationSymbol) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitLocationSymbol(o)
    }
    override def visitAnnotationKey(o: JawaAnnotationKey) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitAnnotationKey(o)
    }
    override def visitNumberLiteral(o: JawaNumberLiteral) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitNumberLiteral(o)
    }
    override def visitParam(o: JawaParam) = {
      AnnotatorHighlighter.highlightElement(o, annotationHolder)
      super.visitParam(o)
    }
  })
}