// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaAssignmentStatementImpl extends ASTWrapperPsiElement implements JawaAssignmentStatement {

  public JawaAssignmentStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitAssignmentStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaExpressionLhs getExpressionLhs() {
    return findNotNullChildByClass(JawaExpressionLhs.class);
  }

  @Override
  @NotNull
  public JawaExpressionRhs getExpressionRhs() {
    return findNotNullChildByClass(JawaExpressionRhs.class);
  }

  @Override
  @Nullable
  public JawaKindAnnotation getKindAnnotation() {
    return findChildByClass(JawaKindAnnotation.class);
  }

  @Override
  @Nullable
  public JawaTypeAnnotation getTypeAnnotation() {
    return findChildByClass(JawaTypeAnnotation.class);
  }

}
