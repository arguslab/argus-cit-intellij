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

public class JawaInstanceFieldDeclarationImpl extends ASTWrapperPsiElement implements JawaInstanceFieldDeclaration {

  public JawaInstanceFieldDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitInstanceFieldDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaAccessFlagAnnotation getAccessFlagAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaAccessFlagAnnotation.class));
  }

  @Override
  @NotNull
  public JawaFieldDefSymbol getFieldDefSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaFieldDefSymbol.class));
  }

  @Override
  @NotNull
  public JawaJwType getJwType() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaJwType.class));
  }

}
