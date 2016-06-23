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

public class JawaNewExpressionImpl extends ASTWrapperPsiElement implements JawaNewExpression {

  public JawaNewExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitNewExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JawaTypeFragmentWithInit> getTypeFragmentWithInitList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaTypeFragmentWithInit.class);
  }

  @Override
  @NotNull
  public JawaTypeSymbol getTypeSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaTypeSymbol.class));
  }

}
