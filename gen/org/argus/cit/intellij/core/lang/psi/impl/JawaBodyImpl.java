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

public class JawaBodyImpl extends ASTWrapperPsiElement implements JawaBody {

  public JawaBodyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JawaVisitor visitor) {
    visitor.visitBody(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JawaVisitor) accept((JawaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JawaCatchClause> getCatchClauseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaCatchClause.class);
  }

  @Override
  @NotNull
  public List<JawaLocalVarDeclaration> getLocalVarDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaLocalVarDeclaration.class);
  }

  @Override
  @NotNull
  public List<JawaLocation> getLocationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JawaLocation.class);
  }

}
