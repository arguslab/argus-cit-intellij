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

import org.argus.cit.intellij.jawa.lang.psi.{JawaAccessFlagAnnotation, JawaSignatureSymbol, JawaJwType, JawaTypeDefSymbol}
import org.argus.jawa.core.{AccessFlag, Signature, JawaType}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaPsiImplUtil {
  def getType(element: JawaJwType): JawaType = {
    val baseTyp = element.getTypeSymbol.getApostropheId.getText.replaceAll("`", "")
    val dimension = element.getTypeFragmentList.size()
    new JawaType(baseTyp, dimension)
  }
  def getType(element: JawaTypeDefSymbol): JawaType = {
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
}