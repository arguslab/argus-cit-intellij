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
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.lang.psi.JawaExpressionPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.JawaMethodNameSymbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaMethodNameSymbolImplMixin extends JawaExpressionPsiElement implements JawaMethodNameSymbol {
    public JawaMethodNameSymbolImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiExpression getQualifierExpression() {
        return null;
    }

    @Override
    public PsiElement bindToElementViaStaticImport(@NotNull PsiClass psiClass) throws IncorrectOperationException {
        return this;
    }

    @Override
    public void setQualifierExpression(@Nullable PsiExpression psiExpression) throws IncorrectOperationException {

    }

    @Nullable
    @Override
    public PsiType getType() {
        return null;
    }

    @Nullable
    @Override
    public PsiElement getReferenceNameElement() {
        return null;
    }

    @Nullable
    @Override
    public PsiReferenceParameterList getParameterList() {
        return null;
    }

    @NotNull
    @Override
    public PsiType[] getTypeParameters() {
        return new PsiType[0];
    }

    @Override
    public boolean isQualified() {
        return true;
    }

    @Override
    public String getQualifiedName() {
        return null;
    }

    @Override
    public void processVariants(@NotNull PsiScopeProcessor psiScopeProcessor) {

    }

    @NotNull
    @Override
    public JavaResolveResult advancedResolve(boolean b) {
        return null;
    }

    @NotNull
    @Override
    public JavaResolveResult[] multiResolve(boolean b) {
        return new JavaResolveResult[0];
    }

    @Nullable
    @Override
    public PsiElement getQualifier() {
        return null;
    }

    @Nullable
    @Override
    public String getReferenceName() {
        return null;
    }

    @Override
    public PsiElement getElement() {
        return null;
    }

    @Override
    public TextRange getRangeInElement() {
        return null;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return null;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return null;
    }

    @Override
    public PsiElement handleElementRename(String s) throws IncorrectOperationException {
        return null;
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        return null;
    }

    @Override
    public boolean isReferenceTo(PsiElement psiElement) {
        return false;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}
