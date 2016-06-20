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

public class JawaStatementImpl extends ASTWrapperPsiElement implements JawaStatement {

  public JawaStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitStatement(this);
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
  @Nullable
  public JawaAssignmentStatement getAssignmentStatement() {
    return findChildByClass(JawaAssignmentStatement.class);
  }

  @Override
  @Nullable
  public JawaCallStatement getCallStatement() {
    return findChildByClass(JawaCallStatement.class);
  }

  @Override
  @Nullable
  public JawaGotoStatement getGotoStatement() {
    return findChildByClass(JawaGotoStatement.class);
  }

  @Override
  @Nullable
  public JawaIfStatement getIfStatement() {
    return findChildByClass(JawaIfStatement.class);
  }

  @Override
  @Nullable
  public JawaMonitorStatement getMonitorStatement() {
    return findChildByClass(JawaMonitorStatement.class);
  }

  @Override
  @Nullable
  public JawaReturnStatement getReturnStatement() {
    return findChildByClass(JawaReturnStatement.class);
  }

  @Override
  @Nullable
  public JawaSwitchStatement getSwitchStatement() {
    return findChildByClass(JawaSwitchStatement.class);
  }

  @Override
  @Nullable
  public JawaThrowStatement getThrowStatement() {
    return findChildByClass(JawaThrowStatement.class);
  }

}
