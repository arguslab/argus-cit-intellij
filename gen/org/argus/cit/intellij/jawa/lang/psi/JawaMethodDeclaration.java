// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaPsiMethod;
import com.intellij.psi.StubBasedPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaMethodStub;

public interface JawaMethodDeclaration extends JawaPsiMethod, StubBasedPsiElement<JawaMethodStub> {

  @NotNull
  JawaAccessFlagAnnotation getAccessFlagAnnotation();

  @NotNull
  JawaJwBody getJwBody();

  @NotNull
  JawaJwType getJwType();

  @NotNull
  JawaMethodDefSymbol getMethodDefSymbol();

  @NotNull
  JawaParamClause getParamClause();

  @NotNull
  JawaSignatureAnnotation getSignatureAnnotation();

  @NotNull
  JawaTypeAnnotation getTypeAnnotation();

}
