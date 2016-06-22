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

public class JawaTypeImpl extends ASTWrapperPsiElement implements JawaType {

  public JawaTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitType(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JawaTypeFragment> getTypeFragmentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaTypeFragment.class);
  }

  @Override
  @NotNull
  public JawaTypeSymbol getTypeSymbol() {
    return findNotNullChildByClass(JawaTypeSymbol.class);
  }

  public org.argus.jawa.core.JawaType getType() {
    return JawaPsiImplUtil.getType(this);
  }

}
