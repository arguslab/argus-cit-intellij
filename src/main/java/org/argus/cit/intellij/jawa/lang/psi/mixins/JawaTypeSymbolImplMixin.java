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
import com.intellij.psi.filters.OrFilter;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.impl.source.tree.JavaSourceUtil;
import com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.JawaTypeSymbol;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaTypeSymbolStub;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaTypeSymbolImplMixin
        extends JawaStubBasedPsiElementBase<JawaTypeSymbolStub>
        implements JawaTypeSymbol {

    public JawaTypeSymbolImplMixin(@NotNull JawaTypeSymbolStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaTypeSymbolImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getReferenceNameElement() {
        return getApostropheId();
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
        return getQualifier() != null;
    }

    @Override
    public String getQualifiedName() {
        return getJawaType().jawaName();
    }

    @Override
    public void processVariants(@NotNull PsiScopeProcessor psiScopeProcessor) {
        final OrFilter filter = new OrFilter();

    }

    @NotNull
    @Override
    public JavaResolveResult advancedResolve(boolean incompleteCode) {
        final JavaResolveResult[] results = multiResolve(incompleteCode);
        return results.length == 1 ? results[0] : JavaResolveResult.EMPTY;
    }

    @NotNull
    @Override
    public JavaResolveResult[] multiResolve(boolean incompleteCode) {
        return PsiImplUtil.multiResolveImpl(this, incompleteCode, PsiReferenceExpressionImpl.OurGenericsResolver.INSTANCE);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return new JavaIdentifier(nameId());
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        throw new IncorrectOperationException("cannot set name");
    }

    @Nullable
    @Override
    public PsiElement getQualifier() {
        return getApostropheId();
    }

    @Nullable
    @Override
    public String getReferenceName() {
        return getJawaType().jawaName();
    }

    @Override
    public PsiElement getElement() {
        return getApostropheId();
    }

    @Override
    public TextRange getRangeInElement() {
        return getApostropheId().getTextRange();
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return advancedResolve(false).getElement();
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return getNormalizedText();
    }

    private String getNormalizedText() {
        return JavaSourceUtil.getReferenceText(this);
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        final PsiElement oldIdentifier = getReferenceNameElement();
        if (oldIdentifier == null) {
            throw new IncorrectOperationException();
        }
        final PsiElement identifier = JavaPsiFacade.getInstance(getProject()).getElementFactory().createIdentifier(newElementName);
        oldIdentifier.replace(identifier);
        return this;
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

    @Override
    public String name() {
        return getJawaType().jawaName();
    }

    @Override
    public PsiElement nameId() {
        return getApostropheId();
    }
}
