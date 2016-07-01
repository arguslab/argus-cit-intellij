// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaParamClauseImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;
import com.intellij.psi.stubs.IStubElementType;

public class JawaParamClauseImpl extends JawaParamClauseImplMixin implements JawaParamClause {

  public JawaParamClauseImpl(ASTNode node) {
    super(node);
  }

  public JawaParamClauseImpl(org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamClauseStub stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitParamClause(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JawaParam> getParamList() {
    return PsiTreeUtil.getStubChildrenOfTypeAsList(this, JawaParam.class);
  }

}
