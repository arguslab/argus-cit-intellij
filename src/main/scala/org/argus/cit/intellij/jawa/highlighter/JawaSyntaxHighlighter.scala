/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.highlighter

import java.awt.Font
import java.util

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.argus.cit.intellij.jawa.lang.lexer.{JawaLexerAdapter, JawaTokenTypes}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaSyntaxHighlighter extends SyntaxHighlighterBase {

  final val BOLD = new TextAttributes(null, null, null, null, Font.BOLD)
  final val ITALIC = new TextAttributes(null, null, null, null, Font.ITALIC)
  final val BOLD_ITALIC = TextAttributes.merge(BOLD, ITALIC)

  private final val ATTRIBUTES: util.HashMap[IElementType, TextAttributesKey] = new util.HashMap
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.LINE_COMMENTS, DefaultHighlighter.LINE_COMMENT)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.BLOCK_COMMENTS, DefaultHighlighter.BLOCK_COMMENT)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.DOC_COMMENTS, DefaultHighlighter.DOC_COMMENT)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.KEYWORDS, DefaultHighlighter.KEYWORD)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.NUMBERS, DefaultHighlighter.NUMBER)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.STRINGS, DefaultHighlighter.STRING)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.BRACKETS, DefaultHighlighter.BRACKETS)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.BRACES, DefaultHighlighter.BRACES)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.PARENTESES, DefaultHighlighter.PARENTHESES)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.ASSIGN_OP, DefaultHighlighter.ASSIGN)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.SEMICOLON, DefaultHighlighter.SEMICOLON)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.DOT, DefaultHighlighter.DOT)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.COMMA, DefaultHighlighter.COMMA)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.BAD_CHARACTER, DefaultHighlighter.BAD_CHARACTER)
  SyntaxHighlighterBase.fillMap(ATTRIBUTES, JawaTokenTypes.ANNOTATION, DefaultHighlighter.ANNOTATION)

  override def getTokenHighlights(iElementType: IElementType): Array[TextAttributesKey] = {
    SyntaxHighlighterBase.pack(ATTRIBUTES.get(iElementType))
  }

  override def getHighlightingLexer: Lexer = new JawaLexerAdapter
}