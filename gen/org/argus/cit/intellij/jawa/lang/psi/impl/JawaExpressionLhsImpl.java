// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaExpressionLhsImpl extends ASTWrapperPsiElement implements JawaExpressionLhs {

  public JawaExpressionLhsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitExpressionLhs(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JawaAccessExpression getAccessExpression() {
    return findChildByClass(JawaAccessExpression.class);
  }

  @Override
  @Nullable
  public JawaIndexingExpression getIndexingExpression() {
    return findChildByClass(JawaIndexingExpression.class);
  }

  @Override
  @Nullable
  public JawaNameExpression getNameExpression() {
    return findChildByClass(JawaNameExpression.class);
  }

}
