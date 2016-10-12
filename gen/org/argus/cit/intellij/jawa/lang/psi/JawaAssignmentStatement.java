// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaAssignmentStatement extends PsiElement {

  @NotNull
  JawaExpressionLhs getExpressionLhs();

  @NotNull
  JawaExpressionRhs getExpressionRhs();

  @Nullable
  JawaKindAnnotation getKindAnnotation();

  @Nullable
  JawaTypeAnnotation getTypeAnnotation();

}
