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

public class JawaMethodDeclarationImpl extends ASTWrapperPsiElement implements JawaMethodDeclaration {

  public JawaMethodDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitMethodDeclaration(this);
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
  public JawaBody getBody() {
    return findNotNullChildByClass(JawaBody.class);
  }

  @Override
  @NotNull
  public JawaMethodDefSymbol getMethodDefSymbol() {
    return findNotNullChildByClass(JawaMethodDefSymbol.class);
  }

  @Override
  @NotNull
  public JawaParamClause getParamClause() {
    return findNotNullChildByClass(JawaParamClause.class);
  }

  @Override
  @NotNull
  public JawaSignatureAnnotation getSignatureAnnotation() {
    return findNotNullChildByClass(JawaSignatureAnnotation.class);
  }

  @Override
  @NotNull
  public JawaType getType() {
    return findNotNullChildByClass(JawaType.class);
  }

  @Override
  @NotNull
  public JawaTypeAnnotation getTypeAnnotation() {
    return findNotNullChildByClass(JawaTypeAnnotation.class);
  }

}
