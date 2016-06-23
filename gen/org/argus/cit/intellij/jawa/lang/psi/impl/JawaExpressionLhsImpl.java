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
    return PsiTreeUtil.getChildOfType(this, JawaAccessExpression.class);
  }

  @Override
  @Nullable
  public JawaIndexingExpression getIndexingExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaIndexingExpression.class);
  }

  @Override
  @Nullable
  public JawaNameExpression getNameExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaNameExpression.class);
  }

}
