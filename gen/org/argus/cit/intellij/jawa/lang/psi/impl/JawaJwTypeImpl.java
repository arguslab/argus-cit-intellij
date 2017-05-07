// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaJwTypeImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.jawa.core.JawaType;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaJwTypeStub;
import com.intellij.psi.stubs.IStubElementType;

public class JawaJwTypeImpl extends JawaJwTypeImplMixin implements JawaJwType {

  public JawaJwTypeImpl(JawaJwTypeStub stub, IStubElementType type) {
    super(stub, type);
  }

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
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, JawaTypeSymbol.class));
  }

  public JawaType getJawaType() {
    return JawaPsiImplUtil.getJawaType(this);
  }

}
