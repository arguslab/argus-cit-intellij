// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaTypeElement;
import com.intellij.psi.StubBasedPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaJwTypeStub;
import org.argus.jawa.core.JawaType;

public interface JawaJwType extends JawaTypeElement, StubBasedPsiElement<JawaJwTypeStub> {

  @NotNull
  List<JawaTypeFragment> getTypeFragmentList();

  @NotNull
  JawaTypeSymbol getTypeSymbol();

  JawaType getJawaType();

}
