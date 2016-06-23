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

public class JawaIfStatementImpl extends ASTWrapperPsiElement implements JawaIfStatement {

  public JawaIfStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitIfStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaBinaryExpression getBinaryExpression() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaBinaryExpression.class));
  }

  @Override
  @NotNull
  public JawaLocationSymbol getLocationSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaLocationSymbol.class));
  }

}
