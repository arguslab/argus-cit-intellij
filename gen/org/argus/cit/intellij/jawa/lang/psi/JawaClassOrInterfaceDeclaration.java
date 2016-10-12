// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaTypeDefinition;
import com.intellij.psi.StubBasedPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaClassOrInterfaceStub;

public interface JawaClassOrInterfaceDeclaration extends JawaTypeDefinition, StubBasedPsiElement<JawaClassOrInterfaceStub> {

  @NotNull
  JawaAccessFlagAnnotation getAccessFlagAnnotation();

  @Nullable
  JawaExtendsAndImplementsClause getExtendsAndImplementsClause();

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
