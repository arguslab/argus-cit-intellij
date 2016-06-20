// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaBody extends PsiElement {

  @NotNull
  List<JawaCatchClause> getCatchClauseList();

  @NotNull
  List<JawaLocalVarDeclaration> getLocalVarDeclarationList();

  @NotNull
  List<JawaLocation> getLocationList();

}