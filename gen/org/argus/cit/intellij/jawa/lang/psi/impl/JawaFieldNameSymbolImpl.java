// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaFieldNameSymbolImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;

public class JawaFieldNameSymbolImpl extends JawaFieldNameSymbolImplMixin implements JawaFieldNameSymbol {

  public JawaFieldNameSymbolImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitFieldNameSymbol(this);
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

  public String getFQN() {
    return JawaPsiImplUtil.getFQN(this);
  }

}
