/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaClassOrInterfaceDeclarationImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaClassOrInterfaceStub;
import com.intellij.psi.stubs.IStubElementType;

public class JawaClassOrInterfaceDeclarationImpl extends JawaClassOrInterfaceDeclarationImplMixin implements JawaClassOrInterfaceDeclaration {

  public JawaClassOrInterfaceDeclarationImpl(JawaClassOrInterfaceStub stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public JawaClassOrInterfaceDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitClassOrInterfaceDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaAccessFlagAnnotation getAccessFlagAnnotation() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, JawaAccessFlagAnnotation.class));
  }

  @Override
  @Nullable
  public JawaExtendsAndImplementsClause getExtendsAndImplementsClause() {
    return PsiTreeUtil.getStubChildOfType(this, JawaExtendsAndImplementsClause.class);
  }

  @Override
  @NotNull
  public JawaInstanceFieldDeclarationBlock getInstanceFieldDeclarationBlock() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaInstanceFieldDeclarationBlock.class));
  }

  @Override
  @NotNull
  public JawaKindAnnotation getKindAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaKindAnnotation.class));
  }

  @Override
  @NotNull
  public List<JawaMethodDeclaration> getMethodDeclarationList() {
    return PsiTreeUtil.getStubChildrenOfTypeAsList(this, JawaMethodDeclaration.class);
  }

  @Override
  @NotNull
  public List<JawaStaticFieldDeclaration> getStaticFieldDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaStaticFieldDeclaration.class);
  }

  @Override
  @NotNull
  public JawaTypeDefSymbol getTypeDefSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaTypeDefSymbol.class));
  }

}
