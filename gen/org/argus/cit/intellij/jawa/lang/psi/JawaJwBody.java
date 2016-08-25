// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaCodeBlock;

public interface JawaJwBody extends JawaCodeBlock {

  @NotNull
  List<JawaCatchClause> getCatchClauseList();

  @NotNull
  List<JawaLocalVarDeclaration> getLocalVarDeclarationList();

  @NotNull
  List<JawaLocation> getLocationList();

}
