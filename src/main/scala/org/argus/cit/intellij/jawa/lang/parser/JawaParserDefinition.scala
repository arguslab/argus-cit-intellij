/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.parser

import com.intellij.lang.ParserDefinition.SpaceRequirements
import com.intellij.lang.{ASTNode, ParserDefinition, PsiParser}
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.tree.{IFileElementType, TokenSet}
import com.intellij.psi.{FileViewProvider, PsiElement, PsiFile}
import org.argus.cit.intellij.jawa.lang.lexer.{JawaLexerAdapter, JawaTokenTypes}
import org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaFileImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaStubElementTypes

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaParserDefinition extends ParserDefinition {
  override def getWhitespaceTokens: TokenSet = JawaTokenTypes.WHITE_SPACES

  override def spaceExistanceTypeBetweenTokens(astNode: ASTNode, astNode1: ASTNode): SpaceRequirements = SpaceRequirements.MAY

  override def createFile(fileViewProvider: FileViewProvider): PsiFile = new JawaFileImpl(fileViewProvider)

  override def getCommentTokens: TokenSet = JawaTokenTypes.COMMENTS

  override def createElement(astNode: ASTNode): PsiElement = JawaElementTypes.Factory.createElement(astNode)

  override def getStringLiteralElements: TokenSet = JawaTokenTypes.STRINGS

  override def createLexer(project: Project): Lexer = new JawaLexerAdapter

  override def getFileNodeType: IFileElementType = JawaStubElementTypes.FILE

  override def createParser(project: Project): PsiParser = new JawaParser
}