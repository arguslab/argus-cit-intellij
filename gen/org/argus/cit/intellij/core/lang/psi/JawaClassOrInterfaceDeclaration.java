// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaClassOrInterfaceDeclaration extends PsiElement {

  @NotNull
  List<JawaAnnotation> getAnnotationList();

  @Nullable
  JawaExtendsAndImplementsClauses getExtendsAndImplementsClauses();

  @NotNull
  JawaInstanceFieldDeclarationBlock getInstanceFieldDeclarationBlock();

  @NotNull
  List<JawaMethodDeclaration> getMethodDeclarationList();

  @NotNull
  List<JawaStaticFieldDeclaration> getStaticFieldDeclarationList();

  @NotNull
  JawaTypeDefSymbol getTypeDefSymbol();

}