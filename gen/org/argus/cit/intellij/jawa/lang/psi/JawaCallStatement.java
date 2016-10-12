// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.expr.JawaMethodCallExpression;

public interface JawaCallStatement extends JawaMethodCallExpression {

  @NotNull
  JawaArgClause getArgClause();

  @Nullable
  JawaCallLhs getCallLhs();

  @NotNull
  JawaKindAnnotation getKindAnnotation();

  @NotNull
  JawaMethodNameSymbol getMethodNameSymbol();

  @NotNull
  JawaSignatureAnnotation getSignatureAnnotation();

  @NotNull
  JawaTypeAnnotation getTypeAnnotation();

}
