// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaCallStatement extends PsiElement {

  @NotNull
  List<JawaAnnotation> getAnnotationList();

  @NotNull
  JawaArgClause getArgClause();

  @Nullable
  JawaCallLhs getCallLhs();

  @NotNull
  JawaMethodNameSymbol getMethodNameSymbol();

  @NotNull
  JawaSignatureAnnotation getSignatureAnnotation();

  @NotNull
  JawaTypeAnnotation getTypeAnnotation();

}
