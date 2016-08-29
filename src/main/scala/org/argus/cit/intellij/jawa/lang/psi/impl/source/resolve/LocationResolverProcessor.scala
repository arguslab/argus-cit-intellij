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
//package org.argus.cit.intellij.jawa.lang.psi.impl.source.resolve
//
//import com.intellij.openapi.util.Key
//import com.intellij.psi.{PsiElement, PsiFile, ResolveState}
//import com.intellij.psi.infos.CandidateInfo
//import com.intellij.psi.scope.ElementClassHint.DeclarationKind
//import com.intellij.psi.scope.{ElementClassFilter, ElementClassHint, PsiConflictResolver}
//import com.intellij.psi.scope.processor.ConflictFilterProcessor
//import com.intellij.psi.util.PsiUtil
//import com.intellij.util.SmartList
//import org.argus.cit.intellij.jawa.lang.psi.JawaReferenceExpressionPsiElement
//
///**
//  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
//  */
//class LocationResolverProcessor(place: JawaReferenceExpressionPsiElement, placeFile: PsiFile)
//  extends ConflictFilterProcessor(place.getReferenceName, ElementClassFilter.ENUM_CONST, new Array[PsiConflictResolver](0), new SmartList[CandidateInfo](), place, placeFile)
//  with ElementClassHint {
//
//  override def shouldProcess(kind: DeclarationKind): Boolean = kind == DeclarationKind.ENUM_CONST
//
//  override def execute(element: PsiElement, state: ResolveState): Boolean = {
//    if (myName == null || PsiUtil.checkName(element, myName, myPlace)) {
//      super.execute(element, state)
//      return myResults.isEmpty
//    }
//    super.execute(element, state)
//  }
//
//  override def getHint[T](hintKey: Key[T]): T = {
//    if (hintKey == ElementClassHint.KEY) {
//      //noinspection unchecked
//      return this.asInstanceOf[T]
//    }
//     super.getHint(hintKey)
//  }
//
//}
