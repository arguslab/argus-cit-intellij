/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.types.api

import com.intellij.openapi.project.Project
import com.intellij.psi.{JavaPsiFacade, PsiSubstitutor, _}
import com.intellij.psi.search.GlobalSearchScope
import org.argus.jawa.core.{JavaKnowledge, JawaType}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
trait JawaTypePsiTypeBridge extends TypeSystemOwner {

  def toJawaType(`type`: PsiType,
               project: Project,
               scope: GlobalSearchScope = null): JawaType = `type` match {
    case arrayType: PsiArrayType => new JawaType(JavaKnowledge.JAVA_TOPLEVEL_OBJECT, arrayType.getArrayDimensions)
    case PsiType.VOID => new JawaType("void")
    case PsiType.BOOLEAN => new JawaType("boolean")
    case PsiType.CHAR => new JawaType("char")
    case PsiType.BYTE => new JawaType("byte")
    case PsiType.SHORT => new JawaType("short")
    case PsiType.INT => new JawaType("int")
    case PsiType.LONG => new JawaType("long")
    case PsiType.FLOAT => new JawaType("float")
    case PsiType.DOUBLE => new JawaType("double")
//    case PsiType.NULL => new JawaType("boolean")
//    case null => Any
    case diamondType: PsiDiamondType =>
      val types = diamondType.resolveInferredTypes().getInferredTypes
      if (types.isEmpty) {
        JavaKnowledge.JAVA_TOPLEVEL_OBJECT_TYPE
      } else {
        toJawaType(types.get(0), project, scope)
      }
    case _ => throw new IllegalArgumentException(s"psi type ${`type`} should not be converted to ${typeSystem.name} type")
  }

  def toPsiType(`type`: JawaType,
                project: Project,
                scope: GlobalSearchScope): PsiType = {
    def javaObject = createJavaObject(project, scope)

    `type` match {
      case JavaKnowledge.JAVA_TOPLEVEL_OBJECT_TYPE => javaObject
      case t if t.jawaName == "void" => PsiType.VOID
      case t if t.jawaName == "boolean" => PsiType.BOOLEAN
      case t if t.jawaName == "char" => PsiType.CHAR
      case t if t.jawaName == "byte" => PsiType.BYTE
      case t if t.jawaName == "short" => PsiType.SHORT
      case t if t.jawaName == "int" => PsiType.INT
      case t if t.jawaName == "long" => PsiType.LONG
      case t if t.jawaName == "float" => PsiType.FLOAT
      case t if t.jawaName == "double" => PsiType.DOUBLE
//      case Null => javaObject
//      case Nothing => javaObject
      case t if t.isArray => new PsiArrayType(toPsiType(new JawaType(t.baseType), project, scope))
      case _ => javaObject
    }
  }

//  def extractClass(`type`: JawaType,
//                   project: Project = null): Option[PsiClass] =
//    extractClassType(`type`, project)
//
//  def extractClassType(`type`: JawaType,
//                       project: Project = null): Option[PsiClass] =
//    `type` match {
//      case jawaType: JawaType =>
//        jawaType.asClass(Option(project).getOrElse(DecompilerUtil.obtainProject))
//      case _ => None
//    }

  protected def createType(psiClass: PsiClass,
                           project: Project,
                           substitutor: PsiSubstitutor = PsiSubstitutor.EMPTY,
                           raw: Boolean = false): PsiType = {
    val psiType = factory(project).createType(psiClass, substitutor)
    if (raw) psiType.rawType
    else psiType
  }

  protected def createJavaObject(project: Project, scope: GlobalSearchScope) = {
    createTypeByFqn(project, scope, "java.lang.Object")
  }

  private def createTypeByFqn(project: Project, scope: GlobalSearchScope, fqn: String): PsiType = {
    factory(project).createTypeByFQClassName(fqn, scope)
  }

  private def factory(project: Project) =
    JavaPsiFacade.getInstance(project).getElementFactory
}
