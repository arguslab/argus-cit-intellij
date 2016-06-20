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

public class JawaStaticFieldDeclarationImpl extends ASTWrapperPsiElement implements JawaStaticFieldDeclaration {

  public JawaStaticFieldDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitStaticFieldDeclaration(this);
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
  public JawaStaticFieldDefSymbol getStaticFieldDefSymbol() {
    return findNotNullChildByClass(JawaStaticFieldDefSymbol.class);
  }

  @Override
  @NotNull
  public JawaType getType() {
    return findNotNullChildByClass(JawaType.class);
  }

}
