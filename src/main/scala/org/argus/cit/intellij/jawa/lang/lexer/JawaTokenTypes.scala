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
import org.argus.cit.intellij.jawa.lang.psi.{JawaClassOrInterfaceDeclaration, JawaTokenType, JawaElementTypes}
import JawaElementTypes._
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

  val SEMICOLON = TokenSet.create(JawaElementTypes.SEMI)

  val DOT = TokenSet.create(JawaElementTypes.DOT)

  val COMMA = TokenSet.create(JawaElementTypes.COMMA)

  val ASSIGN_OP = TokenSet.create(JawaElementTypes.ASSIGN_OP)

  val PARENTESES = TokenSet.create(JawaElementTypes.LPAREN, JawaElementTypes.RPAREN)

  val BRACKETS = TokenSet.create(JawaElementTypes.LBRACKET, JawaElementTypes.RBRACKET)

  val BRACES = TokenSet.create(JawaElementTypes.LBRACE, JawaElementTypes.RBRACE)

  val NUMBERS = TokenSet.create(JawaElementTypes.NUMBER_LITERAL)

  val LINE_COMMENTS = TokenSet.create(LINE_COMMENT)

  val BLOCK_COMMENTS = TokenSet.create(BLOCK_COMMENT)

  val DOC_COMMENTS = TokenSet.create(DOC_COMMENT)

  val BAD_CHARACTER = TokenSet.create(TokenType.BAD_CHARACTER)

  val ANNOTATION = TokenSet.create(JawaElementTypes.OWNER_KEY, JawaElementTypes.TYPE_KEY, JawaElementTypes.CLASS_DESCRIPTOR_KEY, JawaElementTypes.SIGNATURE_KEY, JawaElementTypes.KIND_KEY, JawaElementTypes.ACCESS_FLAG_KEY)
}
