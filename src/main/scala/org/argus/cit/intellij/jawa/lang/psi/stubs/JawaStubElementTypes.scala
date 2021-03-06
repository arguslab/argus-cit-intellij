/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs

import com.intellij.psi.PsiFile
import com.intellij.psi.stubs.PsiFileStub
import com.intellij.psi.tree.IStubFileElementType
import org.argus.cit.intellij.jawa.lang.JawaFileType
import org.argus.cit.intellij.jawa.lang.psi.stubs.elements._

/**
  * Created by fgwei on 6/24/16.
  */
object JawaStubElementTypes {
  val FILE: IStubFileElementType[_ <: PsiFileStub[_ <: PsiFile]] = new JawaStubFileElementType(JawaFileType.JAWA_LANGUAGE)
  val CLASS = new JawaClassDeclarationElementType("CLASS")
  val EXTENDS = new JawaExtendsClauseElementType("EXTENDS")
  val METHOD = new JawaMethodDeclarationElementType("METHOD")
  val FIELD = new JawaFieldDeclarationElementType("FIELD")
  val ACCESS_FLAG = new JawaAccessFlagElementType("ACCESS_FLAG")
  val TYPE = new JawaJwTypeElementType("TYPE")
  val TYPE_SYMBOL = new JawaTypeSymbolElementType("TYPE_SYMBOL")
  val PARAM_CLAUSE = new JawaParamClauseElementType("PARAM_CLAUSE")
  val PARAM = new JawaParamElementType("PARAM")
}
