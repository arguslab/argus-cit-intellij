/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.refactoring.util

import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi._
import org.argus.cit.intellij.jawa.lang.lexer.{JawaLexerAdapter, JawaTokenTypes}
import org.argus.cit.intellij.jawa.lang.psi.{JawaPsiUtil, JawaElementTypes}
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaNamedElement

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaNamesUtil {
  val keywordNames = JawaTokenTypes.KEYWORDS.getTypes.map(_.toString).toSet

  private val lexerCache = new ThreadLocal[JawaLexerAdapter] {
    override def initialValue(): JawaLexerAdapter = new JawaLexerAdapter()
  }

  private def checkGeneric(text: String, predicate: JawaLexerAdapter => Boolean): Boolean = {
    if (text == null || text == "") return false

    val lexer = lexerCache.get()
    lexer.start(text, 0, text.length(), 0)
    if (!predicate(lexer)) return false
    lexer.advance()
    lexer.getTokenType == null
  }

  def isOpCharacter(c : Char) : Boolean = {
    c match {
      case '~' | '!' | '@' | '#' | '%' | '^' | '*' | '+' | '-' | '<' | '>' | '?' | ':' | '=' | '&' | '|' | '/' | '\\' =>
        true
      case ch =>
        Character.getType(ch) == Character.MATH_SYMBOL.toInt || Character.getType(ch) == Character.OTHER_SYMBOL.toInt
    }
  }

  def isIdentifier(text: String): Boolean = {
    checkGeneric(text, lexer => lexer.getTokenType == JawaElementTypes.ID)
  }

  def isQualifiedName(text: String): Boolean = {
    if (StringUtil.isEmpty(text)) return false
    text.split('.').forall(isIdentifier)
  }

  def isKeyword(text: String): Boolean = keywordNames.contains(text)

  def isOperatorName(text: String): Boolean = isIdentifier(text) && isOpCharacter(text(0))

//  def jawaName(element: PsiElement) = element match {
//    case jawaNamed: JawaNamedElement => jawaNamed.name
//    case psiNamed: PsiNamedElement => psiNamed.getName
//  }
//
//  def qualifiedName(named: PsiNamedElement): Option[String] = {
//    JawaPsiUtil.nameContext(named) match {
//      case clazz: PsiClass => Some(clazz.qualifiedName)
//      case memb: PsiMember =>
//        val containingClass = memb.containingClass
//        if (containingClass != null && containingClass.qualifiedName != null && memb.hasModifierProperty(PsiModifier.STATIC)) {
//          Some(Seq(containingClass.qualifiedName, named.name).filter(_ != "").mkString("."))
//        } else None
//      case _ => None
//    }
//  }

//  object isBackticked {
//    def unapply(named: JawaNamedElement): Option[String] = {
//      val name = named.name
//      isBacktickedName.unapply(name)
//    }
//  }

  object isBacktickedName {
    def unapply(name: String): Option[String] = {
      if (name == null || name.isEmpty) None
      else if (name != "`" && name.startsWith("`") && name.endsWith("`")) Some(name.substring(1, name.length - 1))
      else None
    }
  }

  def splitName(name: String): Seq[String] = {
    if (name == null || name.isEmpty) Seq.empty
    else if (name.contains(".")) name.split("\\.")
    else Seq(name)
  }

  def toJavaName(name: String) = {
    name match {
      case isBacktickedName(s) => s
      case _ => name
    }
  }

  def clean(name: String): String = {
    name match {
      case isBacktickedName(s) => s
      case _ => name
    }
  }

  def cleanFqn(fqn: String): String =
    splitName(fqn).map(clean).mkString(".")

  def equivalentFqn(l: String, r: String): Boolean =
    l == r || cleanFqn(l) == cleanFqn(r)

  def equivalent(l: String, r: String): Boolean =
    l == r || clean(l) == clean(r)

  def escapeKeywordsFqn(fqn: String): String =
    splitName(fqn).map(escapeKeyword).mkString(".")

  def escapeKeyword(s: String): String =
    if (isKeyword(s)) s"`$s`" else s
}
