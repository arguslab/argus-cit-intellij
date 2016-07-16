/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.impl

import com.intellij.psi.PsiClass
import com.intellij.psi.tree.{IStubFileElementType, TokenSet}
import com.intellij.util.io.StringRef
import org.argus.cit.intellij.jawa.lang.psi.api.JawaFile
import org.argus.cit.intellij.jawa.lang.psi.stubs.{JawaFileStub, JawaStubElementTypes}
import org.argus.cit.intellij.jawa.lang.psi.stubs.element.wrappers.PsiFileStubWrapperImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFileStubImpl(file: JawaFile) extends PsiFileStubWrapperImpl[JawaFile](file) with JawaFileStub {
  override def getType = JawaStubElementTypes.FILE.asInstanceOf[IStubFileElementType[Nothing]]

  var packName: StringRef = _
  var sourceFileName: StringRef = _
  var compiled: Boolean = false

  def this(file: JawaFile, pName : StringRef, name: StringRef, compiled: Boolean) = {
    this(file)
    this.sourceFileName = name
    packName = pName
    this.compiled = compiled
  }

  def getClasses = {
    getChildrenByType(TokenSet.create(JawaStubElementTypes.CLASS), PsiClass.ARRAY_FACTORY)
  }

  def getFileName = StringRef.toString(sourceFileName)

  def packageName = StringRef.toString(packName)

  def isCompiled: Boolean = compiled
}
