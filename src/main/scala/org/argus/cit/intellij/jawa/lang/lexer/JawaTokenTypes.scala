/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.lexer

import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet
import org.argus.cit.intellij.jawa.lang.psi.{JawaClassOrInterfaceDeclaration, JawaTokenType, JawaTypes}
import org.argus.cit.intellij.jawa.lang.psi.JawaTypes._
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaClassOrInterfaceDeclarationImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaTokenTypes {

  val EOF = JawaTokenType("EOF")
  val LINE_COMMENT = JawaTokenType("LINE_COMMENT")
  val BLOCK_COMMENT = JawaTokenType("BLOCK_COMMENT")
  val DOC_COMMENT = JawaTokenType("DOC_COMMENT")


  val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)

  val TYPE_DECLARATION = TokenSet.create()

  val KEYWORDS = TokenSet.create(
    CLASS_OR_INTERFACE, METHOD, STATIC_FIELD, EXTENDS_AND_IMPLEMENTS, IF, THEN, NEW,
    RETURN, THROW, CALL, SWITCH, ELSE, GOTO, CATCH, INSTANCEOF, CONST_CLASS,
    NULL_LITERAL, MONITOR_ENTER, MONITOR_EXIT, CMP, LENGTH)

  val COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT, DOC_COMMENT)

  val IDS = TokenSet.create(ID, LOCATION_ID)

  val LITERALS = TokenSet.create(NUMBER_LITERAL, STRING_LITERAL, NULL_LITERAL)

  val STRINGS = TokenSet.create(STRING_LITERAL)

  val SEMICOLON = TokenSet.create(JawaTypes.SEMI)

  val DOT = TokenSet.create(JawaTypes.DOT)

  val COMMA = TokenSet.create(JawaTypes.COMMA)

  val ASSIGN_OP = TokenSet.create(JawaTypes.ASSIGN_OP)

  val PARENTESES = TokenSet.create(JawaTypes.LPAREN, JawaTypes.RPAREN)

  val BRACKETS = TokenSet.create(JawaTypes.LBRACKET, JawaTypes.RBRACKET)

  val BRACES = TokenSet.create(JawaTypes.LBRACE, JawaTypes.RBRACE)

  val NUMBERS = TokenSet.create(JawaTypes.NUMBER_LITERAL)

  val LINE_COMMENTS = TokenSet.create(LINE_COMMENT)

  val BLOCK_COMMENTS = TokenSet.create(BLOCK_COMMENT)

  val DOC_COMMENTS = TokenSet.create(DOC_COMMENT)

  val BAD_CHARACTER = TokenSet.create(TokenType.BAD_CHARACTER)

  val ANNOTATION = TokenSet.create(JawaTypes.OWNER_KEY, JawaTypes.TYPE_KEY, JawaTypes.CLASS_DESCRIPTOR_KEY, JawaTypes.SIGNATURE_KEY, JawaTypes.KIND_KEY, JawaTypes.ACCESS_FLAG_KEY)
}
