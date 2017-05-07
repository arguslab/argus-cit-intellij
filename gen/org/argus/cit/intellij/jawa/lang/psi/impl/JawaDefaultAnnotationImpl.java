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

public class JawaDefaultAnnotationImpl extends ASTWrapperPsiElement implements JawaDefaultAnnotation {

  public JawaDefaultAnnotationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitDefaultAnnotation(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getAnnotationKey() {
    return notNullChild(findChildByType(ANNOTATION_KEY));
  }

  @Override
  @Nullable
  public PsiElement getApostropheId() {
    return findChildByType(APOSTROPHE_ID);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

  public String getKey() {
    return JawaPsiImplUtil.getKey(this);
  }

  public String getValue() {
    return JawaPsiImplUtil.getValue(this);
  }

}
