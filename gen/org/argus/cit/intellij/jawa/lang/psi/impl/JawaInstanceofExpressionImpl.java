// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static org.argus.cit.intellij.jawa.lang.psi.JawaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaInstanceofExpressionImpl extends ASTWrapperPsiElement implements JawaInstanceofExpression {

  public JawaInstanceofExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitInstanceofExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaTypeAnnotation getTypeAnnotation() {
    return findNotNullChildByClass(JawaTypeAnnotation.class);
  }

  @Override
  @NotNull
  public JawaVarSymbol getVarSymbol() {
    return findNotNullChildByClass(JawaVarSymbol.class);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
  }

}
