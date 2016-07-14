///*
// * Copyright (c) 2016. Fengguo Wei and others.
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Detailed contributors are listed in the CONTRIBUTOR.md
// */
//
//package org.argus.cit.intellij.jawa.annotator.gutter
//
//import java.awt.event.MouseEvent
//import java.util
//import java.util.{Collections, Comparator}
//import javax.swing.JComponent
//
//import com.intellij.codeInsight.CodeInsightBundle
//import com.intellij.codeInsight.daemon.{DaemonBundle, GutterIconNavigationHandler}
//import com.intellij.codeInsight.daemon.impl.{GutterIconTooltipHelper, LineMarkerNavigator, MarkerType, PsiElementListNavigator}
//import com.intellij.codeInsight.navigation.ListBackgroundUpdaterTask
//import com.intellij.ide.util.{MethodCellRenderer, PsiClassListCellRenderer, PsiClassOrFunctionalExpressionListCellRenderer}
//import com.intellij.openapi.actionSystem.{ActionManager, IdeActions, Shortcut}
//import com.intellij.openapi.application.ApplicationManager
//import com.intellij.openapi.keymap.KeymapUtil
//import com.intellij.openapi.progress.{ProgressIndicator, ProgressManager}
//import com.intellij.openapi.project.DumbService
//import com.intellij.openapi.util.Computable
//import com.intellij.psi.impl.FindSuperElementsHelper
//import com.intellij.psi.util.PsiUtil
//import com.intellij.psi._
//import com.intellij.psi.search.{PsiElementProcessor, PsiElementProcessorAdapter, SearchScope}
//import com.intellij.psi.search.searches.ClassInheritorsSearch
//import com.intellij.util.{ArrayUtil, CommonProcessors, NullableFunction}
//import gnu.trove.THashSet
//import org.sireum.util._
//
//import collection.JavaConversions._
//
///**
//  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
//  */
//object JawaMarkerType {
//  private[impl] val OVERRIDING_METHOD: MarkerType = new MarkerType("OVERRIDING_METHOD", new NullableFunction[PsiElement, String] {
//    def fun(element: PsiElement): String = getParentMethod(element) match {
//      case method: PsiMethod =>
//        calculateOverridingMethodTooltip(method, method != element.getParent)
//      case _ => null
//    }
//  }, new LineMarkerNavigator() {
//    def browse(e: MouseEvent, element: PsiElement) {
//      val parent: PsiElement = getParentMethod(element)
//      if (!parent.isInstanceOf[PsiMethod]) return
//      val method: PsiMethod = parent.asInstanceOf[PsiMethod]
//      navigateToOverridingMethod(e, method, method ne element.getParent)
//    }
//  })
//  private[impl] val SUBCLASSED_CLASS: MarkerType = new MarkerType("SUBCLASSED_CLASS", new NullableFunction[PsiElement, String] {
//    def fun(element: PsiElement): String = element.getParent match {
//      case cls: PsiClass =>
//        getSubclassedClassTooltip(cls)
//      case _ => null
//    }
//  }.asInstanceOf[NullableFunction[PsiElement, String]], new LineMarkerNavigator() {
//    def browse(e: MouseEvent, element: PsiElement) = element.getParent match {
//      case cls: PsiClass =>
//        navigateToSubclassedClass(e, cls)
//      case _ =>
//    }
//  })
//
//  private def getParentMethod(element: PsiElement): PsiElement = element.getParent
//
//  private def calculateOverridingMethodTooltip(method: PsiMethod, acceptSelf: Boolean): String = {
//    val superMethods: Array[PsiMethod] = composeSuperMethods(method, acceptSelf)
//    if (superMethods.length == 0) return null
//    val superMethod: PsiMethod = superMethods(0)
//    val isAbstract: Boolean = method.hasModifierProperty(PsiModifier.ABSTRACT)
//    val isSuperAbstract: Boolean = superMethod.hasModifierProperty(PsiModifier.ABSTRACT)
//    val sameSignature: Boolean = superMethod.getSignature(PsiSubstitutor.EMPTY) == method.getSignature(PsiSubstitutor.EMPTY)
//    var key: String = null
//    if (isSuperAbstract && !isAbstract) {
//      key = if (sameSignature) "method.implements"
//      else "method.implements.in"
//    }
//    else {
//      key = if (sameSignature) "method.overrides"
//      else "method.overrides.in"
//    }
//    composeText(superMethods.map(_.asInstanceOf[PsiElement]), "", DaemonBundle.message(key), IdeActions.ACTION_GOTO_SUPER)
//  }
//
//  def getSubclassedClassTooltip(aClass: PsiClass): String = {
//    val processor: PsiElementProcessor.CollectElementsWithLimit[PsiClass] = new PsiElementProcessor.CollectElementsWithLimit[PsiClass](5, new THashSet[PsiClass])
//    ClassInheritorsSearch.search(aClass).forEach(new PsiElementProcessorAdapter[PsiClass](processor))
//    if (processor.isOverflow) {
//      return if (aClass.isInterface) DaemonBundle.message("interface.is.implemented.too.many")
//      else DaemonBundle.message("class.is.subclassed.too.many")
//    }
//    val subclasses: Array[PsiClass] = processor.toArray(PsiClass.EMPTY_ARRAY)
//    val comparator: Comparator[PsiClass] = PsiClassListCellRenderer.INSTANCE.getComparator
//    util.Arrays.sort(subclasses, comparator)
//    val start: String = if (aClass.isInterface) DaemonBundle.message("interface.is.implemented.by.header")
//    else DaemonBundle.message("class.is.subclassed.by.header")
//    val pattern: String = "&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#javaClass/{0}\">{0}</a>"
//    composeText(subclasses.map(_.asInstanceOf[PsiElement]), start, pattern, IdeActions.ACTION_GOTO_IMPLEMENTATION)
//  }
//
//  private val SEARCHING_FOR_OVERRIDDEN_METHODS: String = "Searching for Overridden Methods"
//
//  def navigateToSubclassedClass(e: MouseEvent, aClass: PsiClass): Unit = {
//    if (DumbService.isDumb(aClass.getProject)) {
//      DumbService.getInstance(aClass.getProject).showDumbModeNotification("Navigation to overriding methods is not possible during index update")
//      return
//    }
//    val collectProcessor: PsiElementProcessor.CollectElementsWithLimit[PsiClass] = new PsiElementProcessor.CollectElementsWithLimit[PsiClass](2, new THashSet[PsiClass])
//    val collectExprProcessor: PsiElementProcessor.CollectElementsWithLimit[PsiFunctionalExpression] = new PsiElementProcessor.CollectElementsWithLimit[PsiFunctionalExpression](2, new THashSet[PsiFunctionalExpression])
//    if (!ProgressManager.getInstance.runProcessWithProgressSynchronously(new Runnable() {
//      def run() = ClassInheritorsSearch.search(aClass).forEach(new PsiElementProcessorAdapter[PsiClass](collectProcessor))
//    }, SEARCHING_FOR_OVERRIDDEN_METHODS, true, aClass.getProject, e.getComponent.asInstanceOf[JComponent])) {
//      return
//    }
//    val inheritors: MList[NavigatablePsiElement] = mlistEmpty
//    inheritors ++= collectProcessor.getCollection
//    inheritors ++= collectExprProcessor.getCollection
//    if (inheritors.isEmpty) return
//    val renderer: PsiClassOrFunctionalExpressionListCellRenderer = new PsiClassOrFunctionalExpressionListCellRenderer
//    val subclassUpdater: SubclassUpdater = new SubclassUpdater(aClass, renderer)
//    Collections.sort(inheritors, renderer.getComparator)
//    PsiElementListNavigator.openTargets(e, inheritors.toArray(new Array[NavigatablePsiElement](inheritors.size)), subclassUpdater.getCaption(inheritors.size), CodeInsightBundle.message("goto.implementation.findUsages.title", aClass.getName), renderer, subclassUpdater)
//  }
//
//  private def composeSuperMethods(method: PsiMethod, acceptSelf: Boolean): Array[PsiMethod] = {
//    val superElements: Array[PsiElement] = FindSuperElementsHelper.findSuperElements(method)
//    var superMethods: Array[PsiMethod] = superElements.map(_.asInstanceOf[PsiMethod])
//    if (acceptSelf) {
//      superMethods = ArrayUtil.prepend(method, superMethods)
//    }
//    superMethods
//  }
//
//  private def composeText(methods: Array[PsiElement], start: String, pattern: String, actionId: String): String = {
//    val shortcuts: Array[Shortcut] = ActionManager.getInstance.getAction(actionId).getShortcutSet.getShortcuts
//    val shortcut: Shortcut = ArrayUtil.getFirstElement(shortcuts)
//    var postfix: String = "<br><div style='margin-top: 5px'><font size='2'>Click"
//    if (shortcut != null) postfix += " or press " + KeymapUtil.getShortcutText(shortcut)
//    postfix += " to navigate</font></div>"
//    GutterIconTooltipHelper.composeText(methods.toList, start, pattern, postfix)
//  }
//
//  private def navigateToOverridingMethod(e: MouseEvent, method: PsiMethod, acceptSelf: Boolean) = {
//    val superMethods: Array[PsiMethod] = composeSuperMethods(method, acceptSelf)
//    if (superMethods.length != 0) {
//      val showMethodNames: Boolean = !PsiUtil.allMethodsHaveSameSignature(superMethods)
//      PsiElementListNavigator.openTargets(e, superMethods.map(_.asInstanceOf[NavigatablePsiElement]), DaemonBundle.message("navigation.title.super.method", method.getName), DaemonBundle.message("navigation.findUsages.title.super.method", method.getName), new MethodCellRenderer(showMethodNames))
//    }
//  }
//
//  private class SubclassUpdater private(val myClass: PsiClass, val myRenderer: PsiClassOrFunctionalExpressionListCellRenderer) extends ListBackgroundUpdaterTask(myClass.getProject, SEARCHING_FOR_OVERRIDDEN_METHODS) {
//    def getCaption(size: Int): String = {
//      val suffix: String = if (isFinished) ""
//      else " so far"
//      if (myClass.isInterface) CodeInsightBundle.message("goto.implementation.chooserTitle", myClass.getName, size, suffix)
//      else DaemonBundle.message("navigation.title.subclass", myClass.getName, size, suffix)
//    }
//
//    override def run(indicator: ProgressIndicator) {
//      super.run(indicator)
//      ClassInheritorsSearch.search(myClass, ApplicationManager.getApplication.runReadAction(new Computable[SearchScope]() {
//        def compute: SearchScope = {
//          myClass.getUseScope
//        }
//      }), true).forEach(new CommonProcessors.CollectProcessor[PsiClass]() {
//        override def process(o: PsiClass): Boolean = {
//          if (!updateComponent(o, myRenderer.getComparator)) {
//            indicator.cancel()
//          }
//          indicator.checkCanceled()
//          super.process(o)
//        }
//      })
//    }
//  }
//}
//case class JawaMarkerType(debugName: String, tooltip: com.intellij.util.Function[PsiElement,String], navigator: LineMarkerNavigator) {
//  val handler = new GutterIconNavigationHandler[PsiElement]() {
//    def navigate(e: MouseEvent, elt: PsiElement) {
//      DumbService.getInstance(elt.getProject).withAlternativeResolveEnabled(new Runnable{
//        override def run(): Unit = navigator.browse(e, elt)
//      })
//    }
//  }
//}