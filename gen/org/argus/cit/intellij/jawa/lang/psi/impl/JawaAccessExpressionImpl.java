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

public class JawaAccessExpressionImpl extends ASTWrapperPsiElement implements JawaAccessExpression {

  public JawaAccessExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitAccessExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaFieldNameSymbol getFieldNameSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaFieldNameSymbol.class));
  }

  @Override
  @NotNull
  public JawaVarSymbol getVarSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaVarSymbol.class));
  }

}
