/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

// This is a generated file. Not intended for manual editing.
package org.argus.cit.intellij.jawa.lang.psi;

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
