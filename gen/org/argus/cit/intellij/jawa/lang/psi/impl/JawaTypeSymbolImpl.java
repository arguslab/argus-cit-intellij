// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaTypeSymbolImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.jawa.core.JawaType;
import com.intellij.psi.stubs.IStubElementType;

public class JawaTypeSymbolImpl extends JawaTypeSymbolImplMixin implements JawaTypeSymbol {

  public JawaTypeSymbolImpl(ASTNode node) {
    super(node);
  }

  public JawaTypeSymbolImpl(org.argus.cit.intellij.jawa.lang.psi.stubs.JawaTypeSymbolStub stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitTypeSymbol(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getApostropheId() {
    return notNullChild(findChildByType(APOSTROPHE_ID));
  }

  public JawaType getJawaType() {
    return JawaPsiImplUtil.getJawaType(this);
  }

}
