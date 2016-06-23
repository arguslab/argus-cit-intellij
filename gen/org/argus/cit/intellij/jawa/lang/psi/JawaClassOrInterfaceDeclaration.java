// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaClassOrInterfaceDeclaration extends PsiElement {

  @NotNull
  JawaAccessFlagAnnotation getAccessFlagAnnotation();

  @Nullable
  JawaExtendsAndImplementsClauses getExtendsAndImplementsClauses();

  @NotNull
  JawaInstanceFieldDeclarationBlock getInstanceFieldDeclarationBlock();

  @NotNull
  JawaKindAnnotation getKindAnnotation();

  @NotNull
  List<JawaMethodDeclaration> getMethodDeclarationList();

  @NotNull
  List<JawaStaticFieldDeclaration> getStaticFieldDeclarationList();

  @NotNull
  JawaTypeDefSymbol getTypeDefSymbol();

}
