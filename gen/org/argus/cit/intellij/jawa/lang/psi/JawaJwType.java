// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.jawa.core.JawaType;

public interface JawaJwType extends PsiElement {

  @NotNull
  List<JawaTypeFragment> getTypeFragmentList();

  @NotNull
  JawaTypeSymbol getTypeSymbol();

  JawaType getType();

}
