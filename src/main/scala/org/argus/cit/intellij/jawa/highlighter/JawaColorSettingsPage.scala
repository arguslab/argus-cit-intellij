/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.highlighter

import java.util
import javax.swing.Icon

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.{AttributesDescriptor, ColorDescriptor, ColorSettingsPage}
import org.argus.cit.intellij.jawa.icons.Icons

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
      |<keyword>record</keyword> <class>`com.fgwei.Test`</class>  <annotation>@kind</annotation> class <annotation>@AccessFlag</annotation> PUBLIC {
      |  <class>`int`</class> <instance-field>`com.fgwei.Test.i1`</instance-field> <annotation>@AccessFlag</annotation> PRIVATE<semi>;</semi>
      |}
      |<keyword>global</keyword> <class>`java.lang.String`</class> <static-field>`@@com.fgwei.Test.str1`</static-field> <annotation>@AccessFlag</annotation> STATIC<semi>;</semi>
      |<keyword>procedure</keyword> <class>`int`</class> <method-declaration>`com.fgwei.Test.main`</method-declaration> <par>(</par><class>`int`</class> <para>v3</para><comma>,</comma> <class>`int`</class> <para>v4</para><par>)</par>
      |       <annotation>@owner</annotation> ^<class>`com.fgwei.Test`</class>
      |       <annotation>@signature</annotation> <sig>`Lcom/fgwei/Test;.main:()I`</sig>
      |       <annotation>@AccessFlag</annotation> PUBLIC_STATIC {
      |  <class>`int`</class> <local-variable>v0</local-variable><semi>;</semi>
      |  <class>`int`</class><bracket>[</bracket><bracket>]</bracket> <local-variable>v1</local-variable><semi>;</semi>
      |  <class>`com.fgwei.Test`</class> <local-variable>test</local-variable><semi>;</semi>
      |
      |  <location>#L027040.</location> <local-variable>test</local-variable><assign>:=</assign> new <class>`com.fgwei.Test`</class>; <line-comment>// This is a line comment</line-comment>
      |  <location>#L027041.</location> <keyword>call</keyword> <method-call>`com.fgwei.Test.<init>`</method-call><par>(</par><local-variable>test</local-variable><par>)</par>
      |         <annotation>@signature</annotation> <sig>`Lcom/fgwei/Test;.<init>:()V`</sig>
      |         <annotation>@classDescriptor</annotation> ^<class>`com.fgwei.Test`</class>
      |         <annotation>@kind</annotation> direct<semi>;</semi>
      |  <location>#L027044.</location> <local-variable>v0</local-variable><assign>:=</assign> <number>1I</number><semi>;</semi>
      |  <location>#L027046.</location> <local-variable>v0</local-variable><assign>:=</assign> <local-variable>test</local-variable><dot>.</dot><instance-field>`com.fgwei.Test.i1`</instance-field> <annotation>@kind</annotation> int <annotation>@type</annotation> ^<class>`int`</class><semi>;</semi>
      |  <location>#L027048.</location> <static-field>`@@com.fgwei.Test.str1`</static-field><assign>:=</assign> <string>"String"</string> <semi>;</semi>
      |  <location>#L02705a.</location> <keyword>return</keyword> <local-variable>v0</local-variable><semi>;</semi>
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
    new AttributesDescriptor(DefaultHighlighter.ANNOTATION_NAME, DefaultHighlighter.ANNOTATION),
    new AttributesDescriptor(DefaultHighlighter.PARAMETER_NAME, DefaultHighlighter.PARAMETER),
    new AttributesDescriptor(DefaultHighlighter.SIGNATURE_NAME, DefaultHighlighter.SIGNATURE)
  )

  final val ANNOTATOR_TAGS: util.HashMap[String, TextAttributesKey] = {
    val tags = new util.HashMap[String, TextAttributesKey]()
    tags.put("keyword", DefaultHighlighter.KEYWORD)
    tags.put("string", DefaultHighlighter.STRING)
    tags.put("assign", DefaultHighlighter.ASSIGN)
    tags.put("par", DefaultHighlighter.PARENTHESES)
    tags.put("brace", DefaultHighlighter.BRACES)
    tags.put("bracket", DefaultHighlighter.BRACKETS)
    tags.put("semi", DefaultHighlighter.SEMICOLON)
    tags.put("dot", DefaultHighlighter.DOT)
    tags.put("comma", DefaultHighlighter.COMMA)
    tags.put("line-comment", DefaultHighlighter.LINE_COMMENT)
    tags.put("class", DefaultHighlighter.CLASS)
    tags.put("local-variable", DefaultHighlighter.LOCAL_VARIABLES)
    tags.put("instance-field", DefaultHighlighter.INSTANCE_FIELD)
    tags.put("static-field", DefaultHighlighter.STATIC_FIELD)
    tags.put("location", DefaultHighlighter.LOCATION)
    tags.put("method-declaration", DefaultHighlighter.METHOD_DECLARATION)
    tags.put("method-call", DefaultHighlighter.METHOD_CALL)
    tags.put("number", DefaultHighlighter.NUMBER)
    tags.put("annotation", DefaultHighlighter.ANNOTATION)
    tags.put("para", DefaultHighlighter.PARAMETER)
    tags.put("sig", DefaultHighlighter.SIGNATURE)
    tags
  }
}