/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.lang.lexer

import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet
import org.argus.cit.intellij.core.lang.psi.{JawaTokenType, JawaTypes}
import org.argus.cit.intellij.core.lang.psi.JawaTypes._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaTokenTypes {

  val EOF = JawaTokenType("EOF")
  val LINE_COMMENT = JawaTokenType("LINE_COMMENT")
  val BLOCK_COMMENT = JawaTokenType("BLOCK_COMMENT")
  val DOC_COMMENT = JawaTokenType("DOC_COMMENT")


  val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)

  val KEYWORDS = TokenSet.create(
    CLASS_OR_INTERFACE, METHOD, STATIC_FIELD, EXTENDS_AND_IMPLEMENTS, IF, THEN, NEW,
    RETURN, THROW, CALL, SWITCH, ELSE, GOTO, CATCH, INSTANCEOF, CONST_CLASS,
    NULL_LITERAL, MONITOR_ENTER, MONITOR_EXIT, CMP, LENGTH)

  val COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT, DOC_COMMENT)

  val IDS = TokenSet.create(ID, LOCATION_ID, STATIC_ID)

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

  val VARIABLES = TokenSet.create(JawaTypes.ID, JawaTypes.STATIC_ID, JawaTypes.LOCATION_ID)

  val LINE_COMMENTS = TokenSet.create(LINE_COMMENT)

  val BLOCK_COMMENTS = TokenSet.create(BLOCK_COMMENT)

  val DOC_COMMENTS = TokenSet.create(DOC_COMMENT)

  val TYPEPARAM = TokenSet.create(JawaTypes.APOSTROPHE_ID)

  val CLASS = TokenSet.create(JawaTypes.TYPE_DEF_SYMBOL)

  val BAD_CHARACTER = TokenSet.create(TokenType.BAD_CHARACTER)

  val LOCAL_VARIABLES = TokenSet.create(JawaTypes.VAR_DEF_SYMBOL)

  val METHOD_CALL = TokenSet.create(JawaTypes.METHOD_NAME_SYMBOL)

  val ANNOTATION = TokenSet.create(JawaTypes.OWNER_KEY, JawaTypes.TYPE_KEY, JawaTypes.CLASS_DESCRIPTOR_KEY, JawaTypes.SIGNATURE_KEY, JawaTypes.ANNOTATION_KEY)

  val ANNOTATION_ATTRIBUTE = TokenSet.create(JawaTypes.ANNOTATION_VALUE)
}
