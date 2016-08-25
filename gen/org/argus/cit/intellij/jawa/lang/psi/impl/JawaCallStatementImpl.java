// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaCallStatementImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaCallStatementImpl extends JawaCallStatementImplMixin implements JawaCallStatement {

  public JawaCallStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitCallStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaArgClause getArgClause() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaArgClause.class));
  }

  @Override
  @Nullable
  public JawaCallLhs getCallLhs() {
    return PsiTreeUtil.getChildOfType(this, JawaCallLhs.class);
  }

  @Override
  @NotNull
  public JawaKindAnnotation getKindAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaKindAnnotation.class));
  }

  @Override
  @NotNull
  public JawaMethodNameSymbol getMethodNameSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaMethodNameSymbol.class));
  }

  @Override
  @NotNull
  public JawaSignatureAnnotation getSignatureAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaSignatureAnnotation.class));
  }

  @Override
  @NotNull
  public JawaTypeAnnotation getTypeAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaTypeAnnotation.class));
  }

}
