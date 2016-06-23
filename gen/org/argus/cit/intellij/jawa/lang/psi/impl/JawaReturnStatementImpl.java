// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaReturnStatementImpl extends ASTWrapperPsiElement implements JawaReturnStatement {

  public JawaReturnStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitReturnStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JawaKindAnnotation getKindAnnotation() {
    return findChildByClass(JawaKindAnnotation.class);
  }

  @Override
  @Nullable
  public JawaVarSymbol getVarSymbol() {
    return findChildByClass(JawaVarSymbol.class);
  }

}
