// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaParamImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamStub;
import com.intellij.psi.stubs.IStubElementType;

public class JawaParamImpl extends JawaParamImplMixin implements JawaParam {

  public JawaParamImpl(JawaParamStub stub, IStubElementType type) {
    super(stub, type);
  }

  public JawaParamImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitParam(this);
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
  public JawaJwType getJwType() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, JawaJwType.class));
  }

  @Override
  @NotNull
  public JawaVarDefSymbol getVarDefSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaVarDefSymbol.class));
  }

}
