// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.core.lang.psi.JawaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.argus.cit.intellij.core.lang.psi.*;

public class JawaAssignmentStatementImpl extends ASTWrapperPsiElement implements JawaAssignmentStatement {

  public JawaAssignmentStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitAssignmentStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JawaAnnotation> getAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaAnnotation.class);
  }

  @Override
  @NotNull
  public JawaExpressionLhs getExpressionLhs() {
    return findNotNullChildByClass(JawaExpressionLhs.class);
  }

  @Override
  @NotNull
  public JawaExpressionRhs getExpressionRhs() {
    return findNotNullChildByClass(JawaExpressionRhs.class);
  }

  @Override
  @NotNull
  public List<JawaTypeAnnotation> getTypeAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaTypeAnnotation.class);
  }

}
