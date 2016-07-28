/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.util

import com.intellij.formatting.FormattingModelBuilder
import com.intellij.ide.structureView.StructureViewBuilder
import com.intellij.lang.ParserDefinition
import com.intellij.lang.folding.FoldingBuilder
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.psi.PsiFile

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class JawaToolsFactory extends ApplicationComponent {
  def getInstance: JawaToolsFactory = ApplicationManager.getApplication.getComponent(classOf[JawaToolsFactory])
  def getComponentName: String = "jawa.JawaToolsFactory"
  def initComponent() = {}
  def disposeComponent() = {}

  def createJawaParserDefinition: ParserDefinition

  def createJawaFoldingBuilder: FoldingBuilder

//  def createSurroundDescriptors: SurroundDescriptors

//  def createJawaFormattingModelBuilder: FormattingModelBuilder

  def createStructureViewBuilder (psiFile: PsiFile): StructureViewBuilder

//  def createFindUsagesProvider: FindUsagesProvider
}