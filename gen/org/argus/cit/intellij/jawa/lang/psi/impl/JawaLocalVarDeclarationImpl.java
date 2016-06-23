// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaLocalVarDeclarationImpl extends ASTWrapperPsiElement implements JawaLocalVarDeclaration {

  public JawaLocalVarDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitLocalVarDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaType getType() {
    return findNotNullChildByClass(JawaType.class);
  }

  @Override
  @NotNull
  public JawaVarDefSymbol getVarDefSymbol() {
    return findNotNullChildByClass(JawaVarDefSymbol.class);
  }

}
