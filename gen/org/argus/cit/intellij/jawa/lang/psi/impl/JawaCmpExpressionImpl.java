// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaCmpExpressionImpl extends ASTWrapperPsiElement implements JawaCmpExpression {

  public JawaCmpExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitCmpExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaCmp getCmp() {
    return findNotNullChildByClass(JawaCmp.class);
  }

  @Override
  @NotNull
  public List<JawaVarSymbol> getVarSymbolList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaVarSymbol.class);
  }

}
