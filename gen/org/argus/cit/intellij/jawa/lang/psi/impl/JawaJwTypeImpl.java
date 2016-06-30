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
import org.argus.jawa.core.JawaType;

public class JawaJwTypeImpl extends ASTWrapperPsiElement implements JawaJwType {

  public JawaJwTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitJwType(this);
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
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaTypeSymbol.class));
  }

  public JawaType getType() {
    return JawaPsiImplUtil.getType(this);
  }

}
