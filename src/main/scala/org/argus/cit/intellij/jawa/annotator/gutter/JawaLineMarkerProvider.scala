/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.annotator.gutter

import java.util
import javax.swing.Icon

import com.intellij.codeHighlighting.Pass
import com.intellij.codeInsight.daemon.impl.{JavaLineMarkerProvider, MarkerType}
import com.intellij.codeInsight.daemon._
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.colors.{CodeInsightColors, EditorColorsManager, EditorColorsScheme}
import com.intellij.openapi.editor.markup.{GutterIconRenderer, SeparatorPlacement}
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.search.searches.{DirectClassInheritorsSearch, SuperMethodsSearch}
import com.intellij.psi.util.MethodSignatureBackedByPsiMethod
import com.intellij.psi.{PsiClass, _}
import com.intellij.util.FunctionUtil
import org.argus.cit.intellij.jawa.lang.psi._
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaTypeDefinition
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier

import collection.JavaConversions._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaLineMarkerProvider(daemonSettings: DaemonCodeAnalyzerSettings, colorsManager: EditorColorsManager)
  extends JavaLineMarkerProvider(daemonSettings, colorsManager) {

//  private final val myOverriddenOption = new GutterIconDescriptor.Option("jawa.overridden", "Overridden method", AllIcons.Gutter.OverridenMethod)
//  private final val myImplementedOption = new GutterIconDescriptor.Option("jawa.implemented", "Implemented method", AllIcons.Gutter.ImplementedMethod)
//  private final val myOverridingOption = new GutterIconDescriptor.Option("jawa.overriding", "Overriding method", AllIcons.Gutter.OverridingMethod)
//  private final val myImplementingOption = new GutterIconDescriptor.Option("jawa.implementing", "Implementing method", AllIcons.Gutter.ImplementingMethod)

  private def isIdentifier(element: PsiElement): Boolean = element match {
    case _: JawaTypeDefSymbol | _: JawaFieldDefSymbol | _: JawaStaticFieldDefSymbol | _: JawaMethodDefSymbol => true
    case _ => false
  }

  override def getLineMarkerInfo(element: PsiElement): LineMarkerInfo[_ <: PsiElement] = {
    val elementToProcess = if(isIdentifier(element)) new JavaIdentifier(element) else element
    super.getLineMarkerInfo(elementToProcess)
//    val parent = element.getParent
//    if(element.isInstanceOf[PsiIdentifier] && parent.isInstanceOf[PsiMethod]) {
//      if (!myOverridingOption.isEnabled && !myImplementingOption.isEnabled) return null
//      val method: PsiMethod = parent.asInstanceOf[PsiMethod]
//      val superSignature: MethodSignatureBackedByPsiMethod = SuperMethodsSearch.search(method, null, true, false).findFirst
//      if (superSignature != null) {
//        val overrides: Boolean = method.hasModifierProperty(PsiModifier.ABSTRACT) == superSignature.getMethod.hasModifierProperty(PsiModifier.ABSTRACT)
//        var icon: Icon = null
//        if (overrides) {
//          if (!myOverridingOption.isEnabled) return null
//          icon = AllIcons.Gutter.OverridingMethod
//        }
//        else {
//          if (!myImplementingOption.isEnabled) return null
//          icon = AllIcons.Gutter.ImplementingMethod
//        }
//        return createSuperMethodLineMarkerInfo(element, icon, Pass.UPDATE_ALL)
//      }
//    }
//    val firstChild = element.getFirstChild
//    if(daemonSettings.SHOW_METHOD_SEPARATORS && firstChild == null) {
//      var element1 = element
//      var isMember: Boolean = false
//      while(element1 != null && !element1.isInstanceOf[PsiFile] && element1.getPrevSibling == null) {
//        element1 = element1.getParent
//        if(element1.isInstanceOf[PsiMember]) isMember = true
//      }
//      if(isMember) {
//        val file: PsiFile = element1.getContainingFile
//        val document: Document = if (file == null) null
//        else PsiDocumentManager.getInstance(file.getProject).getLastCommittedDocument(file)
//        var drawSeparator: Boolean = false
//
//        if (document != null) {
//          val documentChars: CharSequence = document.getCharsSequence
//          val category: Int = getCategory(element1, documentChars)
//          var child: PsiElement = element1.getPrevSibling
//          var shouldbreak = false
//          while (child != null || !shouldbreak) {
//            val category1: Int = getCategory(child, documentChars)
//            if (category1 != 0) {
//              drawSeparator = category != 1 || category1 != 1
//              shouldbreak = true
//            } else {
//              child = child.getPrevSibling
//            }
//          }
//        }
//
//        if (drawSeparator) {
//          val info: LineMarkerInfo[_ <: PsiElement] = new LineMarkerInfo[PsiElement](element, element.getTextRange, null, Pass.UPDATE_ALL, FunctionUtil.nullConstant[Any, String], null, GutterIconRenderer.Alignment.RIGHT)
//          val scheme: EditorColorsScheme = colorsManager.getGlobalScheme
//          info.separatorColor = scheme.getColor(CodeInsightColors.METHOD_SEPARATORS_COLOR)
//          info.separatorPlacement = SeparatorPlacement.TOP
//          return info
//        }
//      }
//    }
//    null
  }

//  private def getCategory(element: PsiElement, documentChars: CharSequence): Int = {
//    element match {
//      case PsiField => 1
//      case PsiClass => 2
//      case method: PsiMethod =>
//        if(method.hasModifierProperty(PsiModifier.ABSTRACT)) 1
//        else {
//          val textRange: TextRange = element.getTextRange
//          val start: Int = textRange.getStartOffset
//          val end: Int = Math.min(documentChars.length, textRange.getEndOffset)
//          val crlf: Int = StringUtil.getLineBreakCount(documentChars.subSequence(start, end))
//          if (crlf == 0) 1 else 2
//        }
//    }
//  }
//
//  private def createSuperMethodLineMarkerInfo(name: PsiElement, icon: Icon, passId: Int): LineMarkerInfo[_ <: PsiElement] = {
//    val info: ArrowUpLineMarkerInfo = new ArrowUpLineMarkerInfo(name, icon, JawaMarkerType.OVERRIDING_METHOD, passId)
//    NavigateAction.setNavigateAction(info, "Go to super method", IdeActions.ACTION_GOTO_SUPER)
//  }

  override def collectSlowLineMarkers(elements: util.List[PsiElement], result: util.Collection[LineMarkerInfo[_ <: PsiElement]]): Unit = {
    val elementsToProcess = elements.filter(isIdentifier).map(new JavaIdentifier(_))
    super.collectSlowLineMarkers(elementsToProcess, result)
//    ApplicationManager.getApplication.assertReadAccessAllowed()
//
//    val members = new ArrayBuffer[PsiElement]
//    val iterator = elements.iterator()
//    while (iterator.hasNext) {
//      val element = iterator.next()
//      ProgressManager.checkCanceled()
//
//      element match {
//        case clazz: JawaTypeDefinition =>
//          collectInheritingClasses(clazz, result)
//        case method: JawaMethodDeclaration =>
//        case _ =>
//      }
//
//      element match {
//        case x: PsiMember with PsiNamedElement => members += x
//        case _ =>
//      }
//    }
//    if (members.nonEmpty) {
//      GutterUtil.collectOverridingMembers(members, result)
//    }
  }

//  def getName: String = {
//    "Jawa line markers"
//  }
//
//  override def getOptions: Array[GutterIconDescriptor.Option] = {
//    Array[GutterIconDescriptor.Option](myOverriddenOption, myImplementedOption, myOverridingOption, myImplementingOption)
//  }

//  private class ArrowUpLineMarkerInfo private(val element: PsiElement, val icon: Icon, val markerType: MarkerType, val passId: Int)
//    extends MergeableLineMarkerInfo[PsiElement](element, element.getTextRange, icon, passId, markerType.getTooltip, markerType.getNavigationHandler, GutterIconRenderer.Alignment.LEFT) {
//    def canMergeWith(info: MergeableLineMarkerInfo[_]): Boolean = {
//      if (!info.isInstanceOf[ArrowUpLineMarkerInfo]) return false
//      val otherElement = info.getElement
//      val myElement: PsiElement = getElement
//      otherElement != null && myElement != null
//    }
//
//    def getCommonIcon(infos: util.List[MergeableLineMarkerInfo[_ <: PsiElement]]): Icon = myIcon
//
//    def getCommonTooltip(infos: util.List[MergeableLineMarkerInfo[_ <: PsiElement]]): (PsiElement, Function[PsiElement, String]) = {
//      element -> "Multiple method overrides".asInstanceOf[Function[PsiElement, String]]
//    }
//  }
//
//  def collectInheritingClasses(aClass: PsiClass, result: util.Collection[LineMarkerInfo[_ <: PsiElement]]): Unit = {
//    if (aClass.hasModifierProperty(PsiModifier.FINAL)) {
//      return
//    }
//    if (CommonClassNames.JAVA_LANG_OBJECT == aClass.getQualifiedName) {
//      return
//    }
//    val subClass: PsiClass = DirectClassInheritorsSearch.search(aClass).findFirst
//    if (subClass != null) {
//      var icon: Icon = null
//      if (aClass.isInterface) {
//        if (!myImplementedOption.isEnabled) return
//        icon = AllIcons.Gutter.ImplementedMethod
//      }
//      else {
//        if (!myOverriddenOption.isEnabled) return
//        icon = AllIcons.Gutter.OverridenMethod
//      }
//      var range: PsiElement = aClass.getNameIdentifier
//      if (range == null) {
//        range = aClass
//      }
//      val `type`: MarkerType = JawaMarkerType.SUBCLASSED_CLASS
//      val info: LineMarkerInfo[_ <: PsiElement] = new LineMarkerInfo[PsiElement](range, range.getTextRange, icon, Pass.UPDATE_OVERRIDDEN_MARKERS, `type`.getTooltip, `type`.getNavigationHandler, GutterIconRenderer.Alignment.RIGHT)
//      NavigateAction.setNavigateAction(info, if (aClass.isInterface) "Go to implementation(s)"
//      else "Go to subclass(es)", IdeActions.ACTION_GOTO_IMPLEMENTATION)
//      result.add(info)
//    }
//  }
}
