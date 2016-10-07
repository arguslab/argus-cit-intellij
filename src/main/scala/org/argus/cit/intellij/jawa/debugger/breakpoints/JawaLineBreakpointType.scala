/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.debugger.breakpoints

import com.intellij.debugger.ui.breakpoints.JavaLineBreakpointType
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi._
import com.intellij.util.Processor
import com.intellij.xdebugger.XDebuggerUtil
import org.argus.cit.intellij.jawa.JawaBundle
import org.argus.cit.intellij.jawa.lang.JawaLanguage
import org.argus.cit.intellij.jawa.lang.psi.{JawaClassOrInterfaceDeclaration, JawaLocation}
import org.jetbrains.annotations.NotNull


/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaLineBreakpointType extends JavaLineBreakpointType("jawa-line", JawaBundle.message("line.breakpoints.tab.title")) {

  override def getDisplayName: String = JawaBundle.message("line.breakpoints.tab.title")

  override def canPutAt(@NotNull file: VirtualFile, line: Int, @NotNull project: Project): Boolean = {
    val psiFile: PsiFile = PsiManager.getInstance(project).findFile(file)
    if (psiFile == null || !psiFile.getLanguage.is(JawaLanguage.Instance)) {
      return false
    }
    val document: Document = FileDocumentManager.getInstance.getDocument(file)
    if (document == null) return false

    var result: Boolean = false
    val processor: Processor[PsiElement] = new Processor[PsiElement] {
      override def process(e: PsiElement): Boolean = e match {
        case ws: PsiWhiteSpace => true
        case _ if PsiTreeUtil.getParentOfType(e, classOf[PsiComment]) != null => true
        case _ if PsiTreeUtil.getParentOfType(e, classOf[JawaLocation], classOf[JawaClassOrInterfaceDeclaration]) != null =>
          result = true
          false
        case _ => true
      }
    }
    XDebuggerUtil.getInstance.iterateLine(project, document, line, processor)
    result
  }
}