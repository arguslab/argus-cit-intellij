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
import org.argus.cit.intellij.core.lang.psi.JawaTokenType
import org.argus.cit.intellij.core.lang.psi.JawaTypes._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaTokenTypes {

  val EOF = JawaTokenType("EOF")
  val LINE_COMMENT = JawaTokenType("LINE_COMMENT")
  val MULTILINE_COMMENT = JawaTokenType("MULTILINE_COMMENT")
  val DOC_COMMENT = JawaTokenType("DOC_COMMENT")

  val WS = TokenType.WHITE_SPACE

  val UNKNOWN = JawaTokenType("UNKNOWN")

  val KEYWORDS = TokenSet.create(
    CLASS_OR_INTERFACE, METHOD, STATIC_FIELD, EXTENDS_AND_IMPLEMENTS, IF, THEN, NEW,
    RETURN, THROW, CALL, SWITCH, ELSE, GOTO, CATCH, INSTANCEOF, CONST_CLASS,
    NULL_LITERAL, MONITOR_ENTER, MONITOR_EXIT, CMP, LENGTH)

  val COMMENTS = TokenSet.create(LINE_COMMENT, MULTILINE_COMMENT, DOC_COMMENT)

  val IDS = TokenSet.create(ID, LOCATION_ID, STATIC_ID)

  val LITERALS = TokenSet.create(NUMBER_LITERAL, STRING_LITERAL, NULL_LITERAL)

  val WHITE_SPACES = TokenSet.create(WS)

  val STRINGS = TokenSet.create(STRING_LITERAL)
}
