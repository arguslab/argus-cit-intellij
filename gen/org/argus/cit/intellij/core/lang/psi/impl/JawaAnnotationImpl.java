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

public class JawaAnnotationImpl extends ASTWrapperPsiElement implements JawaAnnotation {

  public JawaAnnotationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitAnnotation(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JawaAccessFlagAnnotation getAccessFlagAnnotation() {
    return findChildByClass(JawaAccessFlagAnnotation.class);
  }

  @Override
  @Nullable
  public JawaAnnotationKey getAnnotationKey() {
    return findChildByClass(JawaAnnotationKey.class);
  }

  @Override
  @Nullable
  public JawaKindAnnotation getKindAnnotation() {
    return findChildByClass(JawaKindAnnotation.class);
  }

  @Override
  @Nullable
  public JawaSignatureAnnotation getSignatureAnnotation() {
    return findChildByClass(JawaSignatureAnnotation.class);
  }

  @Override
  @Nullable
  public JawaTypeAnnotation getTypeAnnotation() {
    return findChildByClass(JawaTypeAnnotation.class);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

}
