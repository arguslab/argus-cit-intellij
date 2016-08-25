/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.mixins;

import com.intellij.lang.ASTNode;
import com.intellij.psi.*;
import org.argus.cit.intellij.jawa.lang.psi.JawaCallStatement;
import org.argus.cit.intellij.jawa.lang.psi.JawaExpressionPsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaCallStatementImplMixin extends JawaExpressionPsiElement implements JawaCallStatement {

    public JawaCallStatementImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiExpressionList getArgumentList() {
        return getArgClause();
    }

    @Nullable
    @Override
    public PsiMethod resolveMethod() {
        return (PsiMethod)getMethodExpression().resolve();
    }

    @NotNull
    @Override
    public JavaResolveResult resolveMethodGenerics() {
        return getMethodExpression().advancedResolve(false);
    }

    @NotNull
    @Override
    public PsiReferenceExpression getMethodExpression() {
        return null;
    }

    @NotNull
    @Override
    public PsiReferenceParameterList getTypeArgumentList() {
        return null;
    }

    @NotNull
    @Override
    public PsiType[] getTypeArguments() {
        return new PsiType[0];
    }

    @Nullable
    @Override
    public PsiType getType() {
        return null;
    }
}
