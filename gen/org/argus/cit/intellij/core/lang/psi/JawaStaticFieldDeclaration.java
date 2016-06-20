// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaStaticFieldDeclaration extends PsiElement {

  @NotNull
  List<JawaAnnotation> getAnnotationList();

  @NotNull
  JawaStaticFieldDefSymbol getStaticFieldDefSymbol();

  @NotNull
  JawaType getType();

}
