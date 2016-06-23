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

public class JawaClassOrInterfaceDeclarationImpl extends ASTWrapperPsiElement implements JawaClassOrInterfaceDeclaration {

  public JawaClassOrInterfaceDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitClassOrInterfaceDeclaration(this);
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
  @Nullable
  public JawaExtendsAndImplementsClauses getExtendsAndImplementsClauses() {
    return PsiTreeUtil.getChildOfType(this, JawaExtendsAndImplementsClauses.class);
  }

  @Override
  @NotNull
  public JawaInstanceFieldDeclarationBlock getInstanceFieldDeclarationBlock() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaInstanceFieldDeclarationBlock.class));
  }

  @Override
  @NotNull
  public JawaKindAnnotation getKindAnnotation() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaKindAnnotation.class));
  }

  @Override
  @NotNull
  public List<JawaMethodDeclaration> getMethodDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaMethodDeclaration.class);
  }

  @Override
  @NotNull
  public List<JawaStaticFieldDeclaration> getStaticFieldDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaStaticFieldDeclaration.class);
  }

  @Override
  @NotNull
  public JawaTypeDefSymbol getTypeDefSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, JawaTypeDefSymbol.class));
  }

}
