/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.annotator.gutter

import java.util

import com.intellij.codeInsight.daemon._
import com.intellij.codeInsight.daemon.impl.JavaLineMarkerProvider
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.psi._
import org.argus.cit.intellij.jawa.lang.psi._
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier

import scala.collection.JavaConverters._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaLineMarkerProvider(daemonSettings: DaemonCodeAnalyzerSettings, colorsManager: EditorColorsManager)
  extends JavaLineMarkerProvider(daemonSettings, colorsManager) {

  private def isIdentifier(element: PsiElement): Boolean = element match {
    case _: JawaTypeDefSymbol | _: JawaFieldDefSymbol | _: JawaStaticFieldDefSymbol | _: JawaMethodDefSymbol => true
    case _ => false
  }

  override def getLineMarkerInfo(element: PsiElement): LineMarkerInfo[_ <: PsiElement] = {
    val elementToProcess = if(isIdentifier(element)) new JavaIdentifier(element) else element
    super.getLineMarkerInfo(elementToProcess)
  }


  override def collectSlowLineMarkers(elements: util.List[PsiElement], result: util.Collection[LineMarkerInfo[_ <: PsiElement]]): Unit = {
    val elementsToProcess = elements.asScala.map{ e =>
      if(isIdentifier(e)) new JavaIdentifier(e) else e
    }
    super.collectSlowLineMarkers(elementsToProcess.asJava, result)
  }

}
