// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaFieldDeclarationImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaFieldStub;
import com.intellij.psi.stubs.IStubElementType;

public class JawaFieldDeclarationImpl extends JawaFieldDeclarationImplMixin implements JawaFieldDeclaration {

  public JawaFieldDeclarationImpl(JawaFieldStub stub, IStubElementType type) {
    super(stub, type);
  }

  public JawaFieldDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitFieldDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JawaAccessFlagAnnotation getAccessFlagAnnotation() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, JawaAccessFlagAnnotation.class));
  }

  @Override
  @Nullable
  public JawaFieldDefSymbol getFieldDefSymbol() {
    return PsiTreeUtil.getChildOfType(this, JawaFieldDefSymbol.class);
  }

  @Override
  @NotNull
  public JawaJwType getJwType() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, JawaJwType.class));
  }

  @Override
  @Nullable
  public JawaStaticFieldDefSymbol getStaticFieldDefSymbol() {
    return PsiTreeUtil.getChildOfType(this, JawaStaticFieldDefSymbol.class);
  }

  public String getFQN() {
    return JawaPsiImplUtil.getFQN(this);
  }

}
