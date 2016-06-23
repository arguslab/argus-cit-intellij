// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaSwitchCaseImpl extends ASTWrapperPsiElement implements JawaSwitchCase {

  public JawaSwitchCaseImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitSwitchCase(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JawaLocationSymbol getLocationSymbol() {
    return findChildByClass(JawaLocationSymbol.class);
  }

  @Override
  @NotNull
  public JawaNumberLiteral getNumberLiteral() {
    return findNotNullChildByClass(JawaNumberLiteral.class);
  }

}
