// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaExtendAndImplementImpl extends ASTWrapperPsiElement implements JawaExtendAndImplement {

  public JawaExtendAndImplementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitExtendAndImplement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaKindAnnotation getKindAnnotation() {
    return findNotNullChildByClass(JawaKindAnnotation.class);
  }

  @Override
  @NotNull
  public JawaTypeSymbol getTypeSymbol() {
    return findNotNullChildByClass(JawaTypeSymbol.class);
  }

}
