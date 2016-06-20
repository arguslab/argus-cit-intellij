/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.highlighter

import com.intellij.codeInsight.daemon.impl.JavaHighlightInfoTypes
import com.intellij.openapi.editor.{DefaultLanguageHighlighterColors, HighlighterColors}
import com.intellij.openapi.editor.colors.TextAttributesKey

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object DefaultHighlighter {
  final val LINE_COMMENT_NAME = "Line comment"
  final val BLOCK_COMMENT_NAME = "Block comment"
  final val DOC_COMMENT_NAME = "Doc comment"
  final val KEYWORD_NAME = "Keyword"
  final val NUMBER_NAME = "Number"
  final val STRING_NAME = "String"
  final val BRACKETS_NAME = "Brackets"
  final val BRACES_NAME = "Braces"
  final val PARENTHESES_NAME = "Parentheses"
  final val ASSIGN_NAME = "Assign"
  final val SEMICOLON_NAME = "Semicolon"
  final val DOT_NAME = "Dot"
  final val COMMA_NAME = "Comma"
  final val TYPEPARAM_NAME = "Type parameter"
  final val CLASS_NAME = "Class"
  final val BAD_CHARACTER_NAME = "Bad character"
  final val LOCAL_VARIABLES_NAME = "Local variable"
  final val METHOD_CALL_NAME = "Class method call"
  final val ANNOTATION_NAME = "Annotation name"
  final val ANNOTATION_ATTRIBUTE_NAME = "Annotation attribute name"

  final val LINE_COMMENT_ID = "Jawa Line comment"
  final val BLOCK_COMMENT_ID = "Jawa Block comment"
  final val DOC_COMMENT_ID = "Jawa Doc comment"
  final val KEYWORD_ID = "Jawa Keyword"
  final val NUMBER_ID = "Jawa Number"
  final val STRING_ID = "Jawa String"
  final val BRACKETS_ID = "Jawa Brackets"
  final val BRACES_ID = "Jawa Braces"
  final val PARENTHESES_ID = "Jawa Parentheses"
  final val ASSIGN_ID = "Jawa Assign"
  final val SEMICOLON_ID = "Jawa Semicolon"
  final val DOT_ID = "Jawa Dot"
  final val COMMA_ID = "Jawa Comma"
  final val TYPEPARAM_ID = "Jawa Type parameter"
  final val CLASS_ID = "Jawa Class"
  final val BAD_CHARACTER_ID = "Jawa Bad character"
  final val LOCAL_VARIABLES_ID = "Jawa Local variable"
  final val METHOD_CALL_ID = "Jawa Class method call"
  final val ANNOTATION_ID = "Jawa Annotation name"
  final val ANNOTATION_ATTRIBUTE_ID = "Jawa Annotation attribute name"

  val LINE_COMMENT = createKey(LINE_COMMENT_ID, DefaultLanguageHighlighterColors.LINE_COMMENT)
  val BLOCK_COMMENT = createKey(BLOCK_COMMENT_ID, DefaultLanguageHighlighterColors.BLOCK_COMMENT)
  val DOC_COMMENT = createKey(DOC_COMMENT_ID, DefaultLanguageHighlighterColors.DOC_COMMENT)
  val KEYWORD = createKey(KEYWORD_ID, DefaultLanguageHighlighterColors.KEYWORD)
  val NUMBER = createKey(NUMBER_ID, DefaultLanguageHighlighterColors.NUMBER)
  val STRING = createKey(STRING_ID, DefaultLanguageHighlighterColors.STRING)
  val BRACKETS = createKey(BRACKETS_ID, DefaultLanguageHighlighterColors.BRACKETS)
  val BRACES = createKey(BRACES_ID, DefaultLanguageHighlighterColors.BRACES)
  val PARENTHESES = createKey(PARENTHESES_ID, DefaultLanguageHighlighterColors.PARENTHESES)
  val ASSIGN = createKey(ASSIGN_ID, DefaultLanguageHighlighterColors.OPERATION_SIGN)
  val SEMICOLON = createKey(SEMICOLON_ID, DefaultLanguageHighlighterColors.SEMICOLON)
  val DOT = createKey(DOT_ID, DefaultLanguageHighlighterColors.DOT)
  val COMMA = createKey(COMMA_ID, DefaultLanguageHighlighterColors.COMMA)
  val TYPEPARAM = createKey(TYPEPARAM_ID, JavaHighlightInfoTypes.TYPE_PARAMETER_NAME.getAttributesKey)
  val CLASS = createKey(CLASS_ID, JavaHighlightInfoTypes.CLASS_NAME.getAttributesKey)
  val BAD_CHARACTER = createKey(BAD_CHARACTER_ID, HighlighterColors.BAD_CHARACTER)
  val LOCAL_VARIABLES = createKey(LOCAL_VARIABLES_ID, JavaHighlightInfoTypes.LOCAL_VARIABLE.getAttributesKey)
  val METHOD_CALL = createKey(METHOD_CALL_ID, JavaHighlightInfoTypes.METHOD_CALL.getAttributesKey)
  val ANNOTATION = createKey(ANNOTATION_ID, JavaHighlightInfoTypes.ANNOTATION_NAME.getAttributesKey)
  val ANNOTATION_ATTRIBUTE = createKey(ANNOTATION_ATTRIBUTE_ID, JavaHighlightInfoTypes.ANNOTATION_ATTRIBUTE_NAME.getAttributesKey)

  private def createKey(externalName: String, prototype: TextAttributesKey): TextAttributesKey = {
    TextAttributesKey.createTextAttributesKey(externalName, prototype)
  }
}
