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

public class JawaExpressionRhsImpl extends ASTWrapperPsiElement implements JawaExpressionRhs {

  public JawaExpressionRhsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitExpressionRhs(this);
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
  public JawaBinaryExpression getBinaryExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaBinaryExpression.class);
  }

  @Override
  @Nullable
  public JawaCastExpression getCastExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaCastExpression.class);
  }

  @Override
  @Nullable
  public JawaCmpExpression getCmpExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaCmpExpression.class);
  }

  @Override
  @Nullable
  public JawaConstClassExpression getConstClassExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaConstClassExpression.class);
  }

  @Override
  @Nullable
  public JawaExceptionExpression getExceptionExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaExceptionExpression.class);
  }

  @Override
  @Nullable
  public JawaIndexingExpression getIndexingExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaIndexingExpression.class);
  }

  @Override
  @Nullable
  public JawaInstanceofExpression getInstanceofExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaInstanceofExpression.class);
  }

  @Override
  @Nullable
  public JawaLengthExpression getLengthExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaLengthExpression.class);
  }

  @Override
  @Nullable
  public JawaLiteralExpression getLiteralExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaLiteralExpression.class);
  }

  @Override
  @Nullable
  public JawaNameExpression getNameExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaNameExpression.class);
  }

  @Override
  @Nullable
  public JawaNewExpression getNewExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaNewExpression.class);
  }

  @Override
  @Nullable
  public JawaNullExpression getNullExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaNullExpression.class);
  }

  @Override
  @Nullable
  public JawaTupleExpression getTupleExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaTupleExpression.class);
  }

  @Override
  @Nullable
  public JawaUnaryExpression getUnaryExpression() {
    return PsiTreeUtil.getChildOfType(this, JawaUnaryExpression.class);
  }

}
