// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaReference;
import com.intellij.psi.StubBasedPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaTypeSymbolStub;
import org.argus.jawa.core.JawaType;

public interface JawaTypeSymbol extends JawaReference, StubBasedPsiElement<JawaTypeSymbolStub> {

  @NotNull
  PsiElement getApostropheId();

  JawaType getJawaType();

}
