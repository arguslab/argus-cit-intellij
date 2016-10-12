// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaPsiField;
import com.intellij.psi.StubBasedPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaFieldStub;

public interface JawaFieldDeclaration extends JawaPsiField, StubBasedPsiElement<JawaFieldStub> {

  @NotNull
  JawaAccessFlagAnnotation getAccessFlagAnnotation();

  @Nullable
  JawaFieldDefSymbol getFieldDefSymbol();

  @NotNull
  JawaJwType getJwType();

  @Nullable
  JawaStaticFieldDefSymbol getStaticFieldDefSymbol();

  String getFQN();

}
