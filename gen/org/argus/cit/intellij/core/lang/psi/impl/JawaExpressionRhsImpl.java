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
    return findChildByClass(JawaAccessExpression.class);
  }

  @Override
  @Nullable
  public JawaBinaryExpression getBinaryExpression() {
    return findChildByClass(JawaBinaryExpression.class);
  }

  @Override
  @Nullable
  public JawaCastExpression getCastExpression() {
    return findChildByClass(JawaCastExpression.class);
  }

  @Override
  @Nullable
  public JawaCmpExpression getCmpExpression() {
    return findChildByClass(JawaCmpExpression.class);
  }

  @Override
  @Nullable
  public JawaConstClassExpression getConstClassExpression() {
    return findChildByClass(JawaConstClassExpression.class);
  }

  @Override
  @Nullable
  public JawaExceptionExpression getExceptionExpression() {
    return findChildByClass(JawaExceptionExpression.class);
  }

  @Override
  @Nullable
  public JawaIndexingExpression getIndexingExpression() {
    return findChildByClass(JawaIndexingExpression.class);
  }

  @Override
  @Nullable
  public JawaInstanceofExpression getInstanceofExpression() {
    return findChildByClass(JawaInstanceofExpression.class);
  }

  @Override
  @Nullable
  public JawaLengthExpression getLengthExpression() {
    return findChildByClass(JawaLengthExpression.class);
  }

  @Override
  @Nullable
  public JawaLiteralExpression getLiteralExpression() {
    return findChildByClass(JawaLiteralExpression.class);
  }

  @Override
  @Nullable
  public JawaNameExpression getNameExpression() {
    return findChildByClass(JawaNameExpression.class);
  }

  @Override
  @Nullable
  public JawaNewExpression getNewExpression() {
    return findChildByClass(JawaNewExpression.class);
  }

  @Override
  @Nullable
  public JawaNullExpression getNullExpression() {
    return findChildByClass(JawaNullExpression.class);
  }

  @Override
  @Nullable
  public JawaTupleExpression getTupleExpression() {
    return findChildByClass(JawaTupleExpression.class);
  }

  @Override
  @Nullable
  public JawaUnaryExpression getUnaryExpression() {
    return findChildByClass(JawaUnaryExpression.class);
  }

}
