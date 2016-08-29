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
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaStatementImpl extends ASTWrapperPsiElement implements JawaStatement {

  public JawaStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JawaAnnotation> getAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaAnnotation.class);
  }

  @Override
  @Nullable
  public JawaAssignmentStatement getAssignmentStatement() {
    return PsiTreeUtil.getChildOfType(this, JawaAssignmentStatement.class);
  }

  @Override
  @Nullable
  public JawaCallStatement getCallStatement() {
    return PsiTreeUtil.getChildOfType(this, JawaCallStatement.class);
  }

  @Override
  @Nullable
  public JawaGotoStatement getGotoStatement() {
    return PsiTreeUtil.getChildOfType(this, JawaGotoStatement.class);
  }

  @Override
  @Nullable
  public JawaIfStatement getIfStatement() {
    return PsiTreeUtil.getChildOfType(this, JawaIfStatement.class);
  }

  @Override
  @Nullable
  public JawaMonitorStatement getMonitorStatement() {
    return PsiTreeUtil.getChildOfType(this, JawaMonitorStatement.class);
  }

  @Override
  @Nullable
  public JawaReturnStatement getReturnStatement() {
    return PsiTreeUtil.getChildOfType(this, JawaReturnStatement.class);
  }

  @Override
  @Nullable
  public JawaSwitchStatement getSwitchStatement() {
    return PsiTreeUtil.getChildOfType(this, JawaSwitchStatement.class);
  }

  @Override
  @Nullable
  public JawaThrowStatement getThrowStatement() {
    return PsiTreeUtil.getChildOfType(this, JawaThrowStatement.class);
  }

}
