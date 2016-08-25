// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.argus.cit.intellij.jawa.lang.psi.JawaElementTypes.*;
import org.argus.cit.intellij.jawa.lang.psi.mixins.JawaMethodDeclarationImplMixin;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaMethodStub;
import com.intellij.psi.stubs.IStubElementType;

public class JawaMethodDeclarationImpl extends JawaMethodDeclarationImplMixin implements JawaMethodDeclaration {

  public JawaMethodDeclarationImpl(JawaMethodStub stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public JawaMethodDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitMethodDeclaration(this);
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
  @NotNull
  public JawaJwBody getJwBody() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaJwBody.class));
  }

  @Override
  @NotNull
  public JawaJwType getJwType() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, JawaJwType.class));
  }

  @Override
  @NotNull
  public JawaMethodDefSymbol getMethodDefSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaMethodDefSymbol.class));
  }

  @Override
  @NotNull
  public JawaParamClause getParamClause() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, JawaParamClause.class));
  }

  @Override
  @NotNull
  public JawaSignatureAnnotation getSignatureAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaSignatureAnnotation.class));
  }

  @Override
  @NotNull
  public JawaTypeAnnotation getTypeAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaTypeAnnotation.class));
  }

}
