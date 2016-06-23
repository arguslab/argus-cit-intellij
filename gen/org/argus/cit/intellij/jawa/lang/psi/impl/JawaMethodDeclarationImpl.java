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
  public JawaAccessFlagAnnotation getAccessFlagAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaAccessFlagAnnotation.class));
  }

  @Override
  @NotNull
  public JawaBody getBody() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaBody.class));
  }

  @Override
  @NotNull
  public JawaMethodDefSymbol getMethodDefSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaMethodDefSymbol.class));
  }

  @Override
  @NotNull
  public JawaParamClause getParamClause() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaParamClause.class));
  }

  @Override
  @NotNull
  public JawaSignatureAnnotation getSignatureAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaSignatureAnnotation.class));
  }

  @Override
  @NotNull
  public JawaType getType() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaType.class));
  }

  @Override
  @NotNull
  public JawaTypeAnnotation getTypeAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaTypeAnnotation.class));
  }

}
