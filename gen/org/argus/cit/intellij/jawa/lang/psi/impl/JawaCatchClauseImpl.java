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

public class JawaCatchClauseImpl extends ASTWrapperPsiElement implements JawaCatchClause {

  public JawaCatchClauseImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitCatchClause(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaCatchRange getCatchRange() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaCatchRange.class));
  }

  @Override
  @NotNull
  public JawaLocationSymbol getLocationSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaLocationSymbol.class));
  }

  @Override
  @NotNull
  public JawaType getType() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaType.class));
  }

}
