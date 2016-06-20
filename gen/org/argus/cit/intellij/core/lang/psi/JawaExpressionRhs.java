// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.core.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JawaExpressionRhs extends PsiElement {

  @Nullable
  JawaAccessExpression getAccessExpression();

  @Nullable
  JawaBinaryExpression getBinaryExpression();

  @Nullable
  JawaCastExpression getCastExpression();

  @Nullable
  JawaCmpExpression getCmpExpression();

  @Nullable
  JawaConstClassExpression getConstClassExpression();

  @Nullable
  JawaExceptionExpression getExceptionExpression();

  @Nullable
  JawaIndexingExpression getIndexingExpression();

  @Nullable
  JawaInstanceofExpression getInstanceofExpression();

  @Nullable
  JawaLengthExpression getLengthExpression();

  @Nullable
  JawaLiteralExpression getLiteralExpression();

  @Nullable
  JawaNameExpression getNameExpression();

  @Nullable
  JawaNewExpression getNewExpression();

  @Nullable
  JawaNullExpression getNullExpression();

  @Nullable
  JawaTupleExpression getTupleExpression();

  @Nullable
  JawaUnaryExpression getUnaryExpression();

}
