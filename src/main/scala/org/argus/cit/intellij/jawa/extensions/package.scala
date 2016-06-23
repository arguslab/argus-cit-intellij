/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa

import com.intellij.openapi.application.{ApplicationManager, Result}
import com.intellij.openapi.command.{CommandProcessor, WriteCommandAction}
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Computable
import com.intellij.psi.{PsiClass, PsiMethod, PsiModifier, PsiNamedElement}
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaTypeDefinition
import org.argus.jawa.core.JawaClass
import org.jetbrains.annotations.NotNull

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
package object extensions {
  implicit class PsiClassExt(val clazz: PsiClass) extends AnyVal {
    /**
      * Second match branch is for Java only.
      */
    def qualifiedName: String = {
      clazz match {
        case t: JawaTypeDefinition => t.qualifiedName
        case _ => clazz.getQualifiedName
      }
    }

    def constructors: Array[PsiMethod] = {
      clazz match {
        case c: JawaClass => c.constructors
        case _ => clazz.getConstructors
      }
    }

    def isEffectivelyFinal: Boolean = clazz match {
      case jawaClass: JawaClass => jawaClass.hasFinalModifier
      case _ => clazz.hasModifierProperty(PsiModifier.FINAL)
    }


    def processPsiMethodsForNode(node: SignatureNodes.Node, isStatic: Boolean, isInterface: Boolean)
                                (processMethod: PsiMethod => Unit, processName: String => Unit = _ => ()): Unit = {

      def concreteClassFor(typedDef: ScTypedDefinition): Option[PsiClass] = {
        if (typedDef.isAbstractMember) return None
        clazz match {
          case wrapper: PsiClassWrapper if wrapper.definition.isInstanceOf[ScObject] =>
            return Some(wrapper) //this is static case, when containing class should be wrapper
          case _ =>
        }

        ScalaPsiUtil.nameContext(typedDef) match {
          case m: ScMember =>
            m.containingClass match {
              case t: ScTrait =>
                val linearization = MixinNodes.linearization(clazz)
                  .flatMap(_.extractClass(clazz.getProject)(clazz.typeSystem))
                var index = linearization.indexWhere(_ == t)
                while (index >= 0) {
                  val cl = linearization(index)
                  if (!cl.isInterface) return Some(cl)
                  index -= 1
                }
                Some(clazz)
              case _ => None
            }
          case _ => None
        }
      }

      node.info.namedElement match {
        case fun: ScFunction if !fun.isConstructor =>
          val wrappers = fun.getFunctionWrappers(isStatic, isInterface = fun.isAbstractMember, concreteClassFor(fun))
          wrappers.foreach(processMethod)
          wrappers.foreach(w => processName(w.name))
        case method: PsiMethod if !method.isConstructor =>
          if (isStatic) {
            if (method.containingClass != null && method.containingClass.qualifiedName != "java.lang.Object") {
              processMethod(StaticPsiMethodWrapper.getWrapper(method, clazz))
              processName(method.getName)
            }
          }
          else {
            processMethod(method)
            processName(method.getName)
          }
        case t: ScTypedDefinition if t.isVal || t.isVar ||
          (t.isInstanceOf[ScClassParameter] && t.asInstanceOf[ScClassParameter].isCaseClassVal) =>

          PsiTypedDefinitionWrapper.processWrappersFor(t, concreteClassFor(t), node.info.name, isStatic, isInterface, processMethod, processName)
        case _ =>
      }
    }

    def namedElements: Seq[PsiNamedElement] = {
      clazz match {
        case td: ScTemplateDefinition =>
          td.members.flatMap {
            case holder: ScDeclaredElementsHolder => holder.declaredElements
            case named: ScNamedElement => Seq(named)
            case _ => Seq.empty
          }
        case _ => clazz.getFields ++ clazz.getMethods
      }
    }
  }

  implicit class PsiNamedElementExt(val named: PsiNamedElement) extends AnyVal {
    /**
      * Second match branch is for Java only.
      */
    def name: String = {
      named match {
        case nd: ScNamedElement => nd.name
        case nd => nd.getName
      }
    }
  }




  def startCommand(project: Project, commandName: String)(body: => Unit): Unit = {
    CommandProcessor.getInstance.executeCommand(project, new Runnable {
      def run() {
        inWriteAction {
          body
        }
      }
    }, commandName, null)
  }

  def inWriteAction[T](body: => T): T = {
    val application = ApplicationManager.getApplication

    if (application.isWriteAccessAllowed) body
    else {
      application.runWriteAction(
        new Computable[T] {
          def compute: T = body
        }
      )
    }
  }

  def inWriteCommandAction[T](project: Project, commandName: String = "Undefined")(body: => T): T = {
    val computable = new Computable[T] {
      override def compute(): T = body
    }
    new WriteCommandAction[T](project, commandName) {
      protected def run(@NotNull result: Result[T]) {
        result.setResult(computable.compute())
      }
    }.execute.getResultObject
  }

  def inReadAction[T](body: => T): T = {
    val application = ApplicationManager.getApplication

    if (application.isReadAccessAllowed) body
    else {
      application.runReadAction(
        new Computable[T] {
          override def compute(): T = body
        }
      )
    }
  }
}
