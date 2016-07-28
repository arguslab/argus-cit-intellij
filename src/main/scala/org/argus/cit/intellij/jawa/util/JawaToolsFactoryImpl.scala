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
import com.intellij.psi.PsiFile
import org.argus.cit.intellij.jawa.folding.JawaFoldingBuilder
import org.argus.cit.intellij.jawa.lang.parser.JawaParserDefinition

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaToolsFactoryImpl extends JawaToolsFactory {
  def createJawaParserDefinition: ParserDefinition = new JawaParserDefinition()

  def createJawaFoldingBuilder: JawaFoldingBuilder = new JawaFoldingBuilder()

//  def createSurroundDescriptors: SurroundDescriptors = new ScalaSurroundDescriptors()

//  def createScalaFormattingModelBuilder: FormattingModelBuilder = new JawaFormattingModelBuilder()

  def createStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder = null

//  def createFindUsagesProvider = new ScalaFindUsagesProvider

}