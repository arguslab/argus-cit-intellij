/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.impl

import java.util

import com.intellij.pom.java.LanguageLevel
import com.intellij.psi.javadoc.PsiDocComment
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.{PsiPrimitiveType, PsiTypeParameterListOwner, _}
import com.intellij.util.IncorrectOperationException
import org.argus.cit.intellij.jawa.lang.refactoring.util.JawaNamesUtil

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaPsiElementFactoryImpl (manager: PsiManager) extends JVMElementFactory {
  def createDocCommentFromText(text: String): PsiDocComment = ???

  def isValidClassName(name: String): Boolean = JawaNamesUtil.isIdentifier(name)

  def isValidMethodName(name: String): Boolean = JawaNamesUtil.isIdentifier(name)

  def isValidParameterName(name: String): Boolean = JawaNamesUtil.isIdentifier(name)

  def isValidFieldName(name: String): Boolean = JawaNamesUtil.isIdentifier(name)

  def isValidLocalVariableName(name: String): Boolean = JawaNamesUtil.isIdentifier(name)

  def createConstructor(name: String, context: PsiElement): PsiMethod = ???

  def createParameter(name: String, `type`: PsiType, context: PsiElement): PsiParameter = ???

  def createClass(name: String): PsiClass = throw new IncorrectOperationException

  def createInterface(name: String): PsiClass = throw new IncorrectOperationException

  def createEnum(name: String): PsiClass = throw new IncorrectOperationException

  def createField(name: String, `type`: PsiType): PsiField = throw new IncorrectOperationException

  def createMethod(name: String, returnType: PsiType): PsiMethod = throw new IncorrectOperationException

  def createMethod(name: String, returnType: PsiType, context: PsiElement): PsiMethod = throw new IncorrectOperationException

  def createConstructor(): PsiMethod = ???

  def createConstructor(name: String): PsiMethod = ???

  def createClassInitializer(): PsiClassInitializer = throw new IncorrectOperationException

  def createParameter(name: String, `type`: PsiType): PsiParameter = ???

  def createParameterList(names: Array[String], types: Array[PsiType]): PsiParameterList = throw new IncorrectOperationException

  def createMethodFromText(text: String, context: PsiElement): PsiMethod = throw new IncorrectOperationException

  def createAnnotationFromText(annotationText: String, context: PsiElement): PsiAnnotation = throw new IncorrectOperationException

  def createReferenceElementByType(`type`: PsiClassType): PsiElement = ???

  def createTypeParameterList(): PsiTypeParameterList = ???

  def createTypeParameter(name: String, superTypes: Array[PsiClassType]): PsiTypeParameter = ???

  def createType(aClass: PsiClass): PsiClassType = ???

  def createAnnotationType(name: String): PsiClass = ???

  def createType(resolve: PsiClass, substitutor: PsiSubstitutor): PsiClassType = ???

  def createType(resolve: PsiClass, substitutor: PsiSubstitutor, languageLevel: LanguageLevel): PsiClassType = ???

  def createType(resolve: PsiClass, substitutor: PsiSubstitutor, languageLevel: LanguageLevel, annotations: Array[PsiAnnotation]): PsiClassType = ???

  def createType(aClass: PsiClass, parameters: PsiType): PsiClassType = ???

  def createRawSubstitutor(owner: PsiTypeParameterListOwner): PsiSubstitutor = ???

  def createSubstitutor(map: util.Map[PsiTypeParameter, PsiType]): PsiSubstitutor = ???

  def createPrimitiveType(text: String): PsiPrimitiveType = ???

  def createTypeByFQClassName(qName: String): PsiClassType = ???

  def createTypeByFQClassName(qName: String, resolveScope: GlobalSearchScope): PsiClassType = ???

  def createType(aClass: PsiClass, parameters: PsiType*): PsiClassType = ???

  def createExpressionFromText(text: String, context: PsiElement): PsiElement = ???
}

object JawaPsiElementFactory {

}