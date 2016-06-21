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
  import JawaColorSettingsPage._

  override def getIcon: Icon = Icons.FILE

  override def getDemoText: String =
    """/* Block comment */
      |
      |/**
      | * This is a doc comment.
      | * @author fgwei
      | */
      |record <class>`com.fgwei.Test`</class>  @kind class @AccessFlag PUBLIC {
      |  <class>`int`</class> <instance-field>`com.fgwei.Test.i1`</instance-field> @AccessFlag PRIVATE;
      |}
      |global <class>`java.lang.String`</class> <static-field>`@@com.fgwei.Test.str1`</static-field> @AccessFlag STATIC;
      |procedure <class>`int`</class> <method-declaration>`com.fgwei.Test.main`</method-declaration> () @owner ^<class>`com.fgwei.Test`</class> @signature `Lcom/fgwei/Test;.main:()I` @AccessFlag PUBLIC_STATIC {
      |  <class>`int`</class> <local-variable>int_temp</local-variable>;
      |  <class>`int`</class> <local-variable>int_v0</local-variable>;
      |  <class>`com.fgwei.Test`</class> <local-variable>Test_v1</local-variable>;
      |  <class>`int`</class> <local-variable>int_v2</local-variable>;
      |
      |  <location>#L027040.</location> <local-variable>Test_v1</local-variable>:= new <class>`com.fgwei.Test`</class>; // This is a line comment
      |  <location>#L027041.</location> call <method-call>`com.fgwei.Test.<init>`</method-call>(<local-variable>Test_v1</local-variable>) @signature `Lcom/fgwei/Test;.<init>:()V` @classDescriptor ^<class>`com.fgwei.Test`</class> @kind direct;
      |  <location>#L027044.</location> <local-variable>int_v0</local-variable>:= <number>1I</number>;
      |  <location>#L027048.</location> call <method-call>`com.fgwei.Test.setTaint`</method-call>(<local-variable>Test_v1</local-variable>, <local-variable>int_v0</local-variable>) @signature `Lcom/fgwei/Test;.setTaint:(I)V` @classDescriptor ^<class>`com.fgwei.Test`</class> @kind direct;
      |  <location>#L027054.</location> call <local-variable>int_temp</local-variable>:=  <method-call>`com.fgwei.Test.getTaint`</method-call>(<local-variable>Test_v1</local-variable>) @signature `Lcom/fgwei/Test;.getTaint:()I` @classDescriptor ^<class>`com.fgwei.Test`</class> @kind direct;
      |  <location>#L027055.</location> <local-variable>int_v2</local-variable>:= <local-variable>int_temp</local-variable>;
      |  <location>#L02705a.</location> return <local-variable>int_v2</local-variable>;
      |
      |}
    """.stripMargin

  override def getAdditionalHighlightingTagToDescriptorMap: util.Map[String, TextAttributesKey] = ANNOTATOR_TAGS

  override def getHighlighter: SyntaxHighlighter = JawaSyntaxHighlighter

  override def getDisplayName: String = "Jawa"

  override def getColorDescriptors: Array[ColorDescriptor] = ColorDescriptor.EMPTY_ARRAY

  override def getAttributeDescriptors: Array[AttributesDescriptor] = ATTRS.toArray
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
    new AttributesDescriptor(DefaultHighlighter.LOCAL_VARIABLES_NAME, DefaultHighlighter.LOCAL_VARIABLES),
    new AttributesDescriptor(DefaultHighlighter.INSTANCE_FIELD_NAME, DefaultHighlighter.INSTANCE_FIELD),
    new AttributesDescriptor(DefaultHighlighter.STATIC_FIELD_NAME, DefaultHighlighter.STATIC_FIELD),
    new AttributesDescriptor(DefaultHighlighter.LOCATION_NAME, DefaultHighlighter.LOCATION),
    new AttributesDescriptor(DefaultHighlighter.METHOD_DECLARATION_NAME, DefaultHighlighter.METHOD_DECLARATION),
    new AttributesDescriptor(DefaultHighlighter.METHOD_CALL_NAME, DefaultHighlighter.METHOD_CALL),
    new AttributesDescriptor(DefaultHighlighter.BAD_CHARACTER_NAME, DefaultHighlighter.BAD_CHARACTER),
    new AttributesDescriptor(DefaultHighlighter.ANNOTATION_NAME, DefaultHighlighter.ANNOTATION)
  )

  final val ANNOTATOR_TAGS: util.HashMap[String, TextAttributesKey] = {
    val tags = new util.HashMap[String, TextAttributesKey]()
    tags.put("class", DefaultHighlighter.CLASS)
    tags.put("local-variable", DefaultHighlighter.LOCAL_VARIABLES)
    tags.put("instance-field", DefaultHighlighter.INSTANCE_FIELD)
    tags.put("static-field", DefaultHighlighter.STATIC_FIELD)
    tags.put("location", DefaultHighlighter.LOCATION)
    tags.put("method-declaration", DefaultHighlighter.METHOD_DECLARATION)
    tags.put("method-call", DefaultHighlighter.METHOD_CALL)
    tags.put("number", DefaultHighlighter.NUMBER)
    tags
  }
}