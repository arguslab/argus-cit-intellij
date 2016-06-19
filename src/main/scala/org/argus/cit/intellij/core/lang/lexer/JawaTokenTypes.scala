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

import com.intellij.psi.tree.TokenSet

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaTokenTypes {
  val CLASS_OR_INTERFACE = JawaElementType("CLASS_OR_INTERFACE")
  val METHOD = JawaElementType("METHOD")
  val STATIC_FIELD = JawaElementType("STATIC_FIELD")
  val EXTENDS_AND_IMPLEMENTS = JawaElementType("EXTENDS_AND_IMPLEMENTS")

  val CONST_CLASS = JawaElementType("CONST_CLASS")
  val LENGTH = JawaElementType("LENGTH")

  val EQUALS = JawaElementType("EQUALS")

  val ID = JawaElementType("ID")
  val LOCATION_ID = JawaElementType("LOCATION_ID")
  val STATIC_ID = JawaElementType("STATIC_ID")
  val EXCEPTION = JawaElementType("EXCEPTION")
  val INSTANCEOF = JawaElementType("INSTANCEOF")

  val NEW = JawaElementType("NEW")
  val THROW = JawaElementType("THROW")
  val CATCH = JawaElementType("CATCH")
  val IF = JawaElementType("IF")
  val THEN = JawaElementType("THEN")
  val GOTO = JawaElementType("GOTO")
  val SWITCH = JawaElementType("SWITCH")
  val ELSE = JawaElementType("ELSE")
  val RETURN = JawaElementType("RETURN")
  val CALL = JawaElementType("CALL")
  val MONITOR_ENTER = JawaElementType("MONITOR_ENTER")
  val MONITOR_EXIT = JawaElementType("MONITOR_EXIT")



  val EOF = JawaElementType("EOF")

  val LBRACKET = JawaElementType("LBRACKET")
  val RBRACKET = JawaElementType("RBRACKET")
  val LPAREN = JawaElementType("LPAREN")
  val RPAREN = JawaElementType("RPAREN")
  val LBRACE = JawaElementType("LBRACE")
  val RBRACE = JawaElementType("RBRACE")

  val STRING_LITERAL = JawaElementType("STRING_LITERAL")
  val FLOATING_POINT_LITERAL = JawaElementType("FLOATING_POINT_LITERAL")
  val INTEGER_LITERAL = JawaElementType("INTEGER_LITERAL")
  val CHARACTER_LITERAL = JawaElementType("CHARACTER_LITERAL")
  val TRUE = JawaElementType("TRUE")
  val FALSE = JawaElementType("FALSE")
  val NULL = JawaElementType("NULL")

  val COMMA = JawaElementType("COMMA")
  val DOT = JawaElementType("DOT")
  val SEMI = JawaElementType("SEMI")
  val COLON = JawaElementType("COLON")
  val ARROW = JawaElementType("ARROW")
  val AT = JawaElementType("AT")
  val RANGE = JawaElementType("RANGE")
  val ASSIGN_OP = JawaElementType("ASSIGN_OP")
  val HAT = JawaElementType("HAT")

  val CMP = JawaElementType("CMP")
  //  val ANY = JawaElementType("ANY")

  val LINE_COMMENT = JawaElementType("LINE_COMMENT")
  val MULTILINE_COMMENT = JawaElementType("MULTILINE_COMMENT")
  val DOC_COMMENT = JawaElementType("DOC_COMMENT")

  val WS = JawaElementType("WS")

  val OP = JawaElementType("OP")

  val UNKNOWN = JawaElementType("UNKNOWN")

  val KEYWORDS = TokenSet.create(
    CLASS_OR_INTERFACE, METHOD, STATIC_FIELD, EXTENDS_AND_IMPLEMENTS, IF, THEN, NEW,
    RETURN, THROW, CALL, SWITCH, ELSE, GOTO, CATCH, INSTANCEOF, CONST_CLASS,
    TRUE, FALSE, NULL, MONITOR_ENTER, MONITOR_EXIT, CMP, LENGTH)

  val COMMENTS = TokenSet.create(LINE_COMMENT, MULTILINE_COMMENT, DOC_COMMENT)

  val IDS = TokenSet.create(ID, LOCATION_ID, STATIC_ID)

  val LITERALS = TokenSet.create(CHARACTER_LITERAL, INTEGER_LITERAL, FLOATING_POINT_LITERAL, STRING_LITERAL, TRUE, FALSE, NULL)

  val WHITE_SPACES = TokenSet.create(WS)

  val STRINGS = TokenSet.create(STRING_LITERAL)
}
