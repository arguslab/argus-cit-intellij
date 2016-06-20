// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaStatement extends PsiElement {

  @NotNull
  List<JawaAnnotation> getAnnotationList();

  @Nullable
  JawaAssignmentStatement getAssignmentStatement();

  @Nullable
  JawaCallStatement getCallStatement();

  @Nullable
  JawaGotoStatement getGotoStatement();

  @Nullable
  JawaIfStatement getIfStatement();

  @Nullable
  JawaMonitorStatement getMonitorStatement();

  @Nullable
  JawaReturnStatement getReturnStatement();

  @Nullable
  JawaSwitchStatement getSwitchStatement();

  @Nullable
  JawaThrowStatement getThrowStatement();

}
