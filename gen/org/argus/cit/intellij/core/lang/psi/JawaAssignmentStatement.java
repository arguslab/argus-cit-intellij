// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaAssignmentStatement extends PsiElement {

  @NotNull
  List<JawaAnnotation> getAnnotationList();

  @NotNull
  JawaExpressionLhs getExpressionLhs();

  @NotNull
  JawaExpressionRhs getExpressionRhs();

  @NotNull
  List<JawaTypeAnnotation> getTypeAnnotationList();

}