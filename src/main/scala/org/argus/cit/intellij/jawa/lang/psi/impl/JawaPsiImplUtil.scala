/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.impl

import org.argus.cit.intellij.jawa.lang.psi._
import org.argus.jawa.core.{AccessFlag, JawaType, Signature}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaPsiImplUtil {
  def getJawaType(element: JawaJwType): JawaType = {
    val baseTyp = element.getTypeSymbol.getApostropheId.getText.replaceAll("`", "")
    val dimension = element.getTypeFragmentList.size()
    new JawaType(baseTyp, dimension)
  }
  def getJawaType(element: JawaTypeDefSymbol): JawaType = {
    val baseTyp = element.getApostropheId.getText.replaceAll("`", "")
    new JawaType(baseTyp)
  }
  def getJawaType(element: JawaTypeSymbol): JawaType = {
    val baseTyp = element.getApostropheId.getText.replaceAll("`", "")
    new JawaType(baseTyp)
  }
  def getSignature(element: JawaSignatureSymbol): Signature = {
    new Signature(element.getApostropheId.getText.replaceAll("`", ""))
  }
  def getModifiers(element: JawaAccessFlagAnnotation): Int = {
    val mod = element.getId match {
      case null => ""
      case a => a.getText
    }
    AccessFlag.getAccessFlags(mod)
  }
  def getFQN(element: JawaFieldDeclaration): String = {
    if(element.getFieldDefSymbol != null) {
      element.getFieldDefSymbol.getApostropheId.getText.replaceAll("`", "")
    } else if(element.getStaticFieldDefSymbol != null) {
      element.getStaticFieldDefSymbol.getStaticId.getText.replaceAll("`", "").replaceAll("@@", "")
    } else null
  }
  def getFQN(element: JawaFieldNameSymbol): String = {
    element.getApostropheId.getText.replaceAll("`", "")
  }
  def getFQN(element: JawaStaticFieldNameSymbol): String = {
    element.getStaticId.getText.replaceAll("`", "").replaceAll("@@", "")
  }
  def isImplements(element: JawaExtendAndImplement): Boolean = {
    element.getKindAnnotation.getId.getText == "interface"
  }
}
