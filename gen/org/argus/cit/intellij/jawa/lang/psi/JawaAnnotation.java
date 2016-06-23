// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaAnnotation extends PsiElement {

  @Nullable
  JawaAccessFlagAnnotation getAccessFlagAnnotation();

  @Nullable
  JawaAnnotationKey getAnnotationKey();

  @Nullable
  JawaKindAnnotation getKindAnnotation();

  @Nullable
  JawaSignatureAnnotation getSignatureAnnotation();

  @Nullable
  JawaTypeAnnotation getTypeAnnotation();

  @Nullable
  PsiElement getId();

}
