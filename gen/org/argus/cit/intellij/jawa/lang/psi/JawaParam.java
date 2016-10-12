// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaParameter;
import com.intellij.psi.StubBasedPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamStub;

public interface JawaParam extends JawaParameter, StubBasedPsiElement<JawaParamStub> {

  @NotNull
  JawaJwType getJwType();

  @Nullable
  JawaKindAnnotation getKindAnnotation();

  @NotNull
  JawaVarDefSymbol getVarDefSymbol();

}
