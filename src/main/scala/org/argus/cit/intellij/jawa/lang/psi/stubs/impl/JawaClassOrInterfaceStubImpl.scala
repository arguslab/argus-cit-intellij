/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.impl

import com.intellij.pom.java.LanguageLevel
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{IStubElementType, StubElement}
import com.intellij.util.io.StringRef
import org.argus.cit.intellij.jawa.lang.psi.JawaClassOrInterfaceDeclaration
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaClassOrInterfaceStub

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaClassOrInterfaceStubImpl[ParentPsi <: PsiElement](parent: StubElement[ParentPsi],
                                                            elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement])
  extends StubBaseWrapper[JawaClassOrInterfaceDeclaration](parent, elemType) with JawaClassOrInterfaceStub {

  var myName: String = _
  var myJavaQualName: String = _
  var mySourceFileName: String = _
  var myMethodNames: Array[String] = Array[String]()
  var myJavaName: String = _

  def this(parent: StubElement[ParentPsi],
           elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
           name: String,
           javaQualName: String,
           sourceFileName: String,
           methodNames: Array[String],
           javaName: String) {
    this(parent, elemType.asInstanceOf[IStubElementType[StubElement[PsiElement], PsiElement]])
    mySourceFileName = sourceFileName
    myName = name
    myJavaQualName = javaQualName
    myMethodNames = methodNames
    myJavaName = javaName
  }

  def this(parent: StubElement[ParentPsi],
           elemType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
           name: StringRef,
           javaQualName: StringRef,
           sourceFileName: StringRef,
           methodNames: Array[StringRef],
           javaName: StringRef) {
    this(parent, elemType.asInstanceOf[IStubElementType[StubElement[PsiElement], PsiElement]])
    mySourceFileName = StringRef.toString(sourceFileName)
    myName = StringRef.toString(name)
    myJavaQualName = StringRef.toString(javaQualName)
    myMethodNames = methodNames.map(StringRef.toString)
    myJavaName = StringRef.toString(javaName)
  }


  def sourceFileName = mySourceFileName

  def javaQualName = myJavaQualName

  def getName = myName

  def methodNames: Array[String] = myMethodNames

  //todo PsiClassStub methods
  def getLanguageLevel: LanguageLevel = LanguageLevel.JDK_1_8
  def isEnum: Boolean = false
  def isInterface: Boolean = false
  def isAnonymous: Boolean = false
  def isAnonymousInQualifiedNew: Boolean = false
  def isAnnotationType: Boolean = false
  def hasDeprecatedAnnotation: Boolean = false
  def isEnumConstantInitializer: Boolean = false
  def getBaseClassReferenceText: String = null
  def javaName: String = myJavaName

  override def getQualifiedName: String = javaQualName

  override def getSourceFileName: String = sourceFileName

  override def isDeprecated: Boolean = false
}
