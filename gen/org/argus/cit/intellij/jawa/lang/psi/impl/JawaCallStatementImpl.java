// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaCallStatementImpl extends ASTWrapperPsiElement implements JawaCallStatement {

  public JawaCallStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitCallStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaArgClause getArgClause() {
    return findNotNullChildByClass(JawaArgClause.class);
  }

  @Override
  @Nullable
  public JawaCallLhs getCallLhs() {
    return findChildByClass(JawaCallLhs.class);
  }

  @Override
  @NotNull
  public JawaKindAnnotation getKindAnnotation() {
    return findNotNullChildByClass(JawaKindAnnotation.class);
  }

  @Override
  @NotNull
  public JawaMethodNameSymbol getMethodNameSymbol() {
    return findNotNullChildByClass(JawaMethodNameSymbol.class);
  }

  @Override
  @NotNull
  public JawaSignatureAnnotation getSignatureAnnotation() {
    return findNotNullChildByClass(JawaSignatureAnnotation.class);
  }

  @Override
  @NotNull
  public JawaTypeAnnotation getTypeAnnotation() {
    return findNotNullChildByClass(JawaTypeAnnotation.class);
  }

}
