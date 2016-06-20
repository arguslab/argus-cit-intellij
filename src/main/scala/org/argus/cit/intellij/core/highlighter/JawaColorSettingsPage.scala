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

import java.util
import javax.swing.Icon

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.{AttributesDescriptor, ColorDescriptor, ColorSettingsPage}
import org.argus.cit.intellij.core.icons.Icons

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaColorSettingsPage extends ColorSettingsPage {



  override def getIcon: Icon = Icons.FILE

  override def getDemoText: String =
    """/* Block comment */
      |
      |/**
      | * This is a doc comment.
      | * @author fgwei
      | */
      |record `com.fgwei.Test`  @kind class @AccessFlag PUBLIC {
      |      `int` `com.fgwei.Test.i1`    @AccessFlag PRIVATE;
      |   }
      |    procedure `int` `com.fgwei.Test.main` () @owner ^`com.fgwei.Test` @signature `Lcom/fgwei/Test;.main:()I` @AccessFlag PUBLIC_STATIC {
      |      `int` int_temp;
      |      `int` int_v0;
      |      `com.fgwei.Test` Test_v1;
      |      `int` int_v2;
      |
      |#L027040.   Test_v1:= new `com.fgwei.Test`; // This is a line comment
      |#L027041.   call `com.fgwei.Test.<init>`(Test_v1) @signature `Lcom/fgwei/Test;.<init>:()V` @classDescriptor ^`com.fgwei.Test` @kind direct;
      |#L027044.   int_v0:= 1I;
      |#L027048.   call `com.fgwei.Test.setTaint`(Test_v1, int_v0) @signature `Lcom/fgwei/Test;.setTaint:(I)V` @classDescriptor ^`com.fgwei.Test` @kind direct;
      |#L027054.   call int_temp:=  `com.fgwei.Test.getTaint`(Test_v1) @signature `Lcom/fgwei/Test;.getTaint:()I` @classDescriptor ^`com.fgwei.Test` @kind direct;
      |#L027055.   int_v2:= int_temp;
      |#L02705a.   return int_v2;
      |
      |   }
    """.stripMargin

  override def getAdditionalHighlightingTagToDescriptorMap: util.Map[String, TextAttributesKey] = {
    new util.HashMap[String, TextAttributesKey]() {
      "keyword" -> DefaultHighlighter.KEYWORD
      "par" -> DefaultHighlighter.PARENTHESES
      "brace" -> DefaultHighlighter.BRACES
    }
  }

  override def getHighlighter: SyntaxHighlighter = JawaSyntaxHighlighter

  override def getDisplayName: String = "Jawa"

  override def getColorDescriptors: Array[ColorDescriptor] = new Array[ColorDescriptor](0)

  override def getAttributeDescriptors: Array[AttributesDescriptor] = JawaColorSettingsPage.ATTRS.toArray
}

object JawaColorSettingsPage {
  final val ATTRS: Set[AttributesDescriptor] = Set(
    new AttributesDescriptor(DefaultHighlighter.KEYWORD_NAME, DefaultHighlighter.KEYWORD),
    new AttributesDescriptor(DefaultHighlighter.NUMBER_NAME, DefaultHighlighter.NUMBER),
    new AttributesDescriptor(DefaultHighlighter.STRING_NAME, DefaultHighlighter.STRING),
    new AttributesDescriptor(DefaultHighlighter.ASSIGN_NAME, DefaultHighlighter.ASSIGN),
    new AttributesDescriptor(DefaultHighlighter.PARENTHESES_NAME, DefaultHighlighter.PARENTHESES),
    new AttributesDescriptor(DefaultHighlighter.BRACES_NAME, DefaultHighlighter.BRACES),
    new AttributesDescriptor(DefaultHighlighter.BRACKETS_NAME, DefaultHighlighter.BRACKETS),
    new AttributesDescriptor(DefaultHighlighter.SEMICOLON_NAME, DefaultHighlighter.SEMICOLON),
    new AttributesDescriptor(DefaultHighlighter.DOT_NAME, DefaultHighlighter.DOT),
    new AttributesDescriptor(DefaultHighlighter.COMMA_NAME, DefaultHighlighter.COMMA),
    new AttributesDescriptor(DefaultHighlighter.LINE_COMMENT_NAME, DefaultHighlighter.LINE_COMMENT),
    new AttributesDescriptor(DefaultHighlighter.BLOCK_COMMENT_NAME, DefaultHighlighter.BLOCK_COMMENT),
    new AttributesDescriptor(DefaultHighlighter.DOC_COMMENT_NAME, DefaultHighlighter.DOC_COMMENT),
    new AttributesDescriptor(DefaultHighlighter.CLASS_NAME, DefaultHighlighter.CLASS),
    new AttributesDescriptor(DefaultHighlighter.TYPEPARAM_NAME, DefaultHighlighter.TYPEPARAM),
    new AttributesDescriptor(DefaultHighlighter.LOCAL_VARIABLES_NAME, DefaultHighlighter.LOCAL_VARIABLES),
    new AttributesDescriptor(DefaultHighlighter.METHOD_CALL_NAME, DefaultHighlighter.METHOD_CALL),
    new AttributesDescriptor(DefaultHighlighter.BAD_CHARACTER_NAME, DefaultHighlighter.BAD_CHARACTER),
    new AttributesDescriptor(DefaultHighlighter.ANNOTATION_NAME, DefaultHighlighter.ANNOTATION),
    new AttributesDescriptor(DefaultHighlighter.ANNOTATION_ATTRIBUTE_NAME, DefaultHighlighter.ANNOTATION_ATTRIBUTE)
  )
}