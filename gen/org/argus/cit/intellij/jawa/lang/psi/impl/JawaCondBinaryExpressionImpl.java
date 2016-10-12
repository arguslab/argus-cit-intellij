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

public class JawaCondBinaryExpressionImpl extends ASTWrapperPsiElement implements JawaCondBinaryExpression {

  public JawaCondBinaryExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitCondBinaryExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JawaNumberLiteral getNumberLiteral() {
    return PsiTreeUtil.getChildOfType(this, JawaNumberLiteral.class);
  }

  @Override
  @NotNull
  public List<JawaVarSymbol> getVarSymbolList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaVarSymbol.class);
  }

}
