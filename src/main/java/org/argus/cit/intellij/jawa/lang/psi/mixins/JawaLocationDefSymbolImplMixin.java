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
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import org.argus.cit.intellij.jawa.lang.psi.JawaExpressionPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.JawaLocationDefSymbol;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaLocationDefSymbolImplMixin extends JawaExpressionPsiElement implements JawaLocationDefSymbol {
    public JawaLocationDefSymbolImplMixin(@NotNull ASTNode node) {
        super(node);
    }


    @Override
    public String name() {
        return getName();
    }

    @Override
    public String getName() {
        return getNameIdentifier().getText();
    }

    @Override
    public PsiElement nameId() {
        return getLocationId();
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) {
        PsiImplUtil.setName(getNameIdentifier(), name);
        return this;
    }

    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        return processor.execute(this, state);
    }

    public String toString() {
        return "JawaLocationDefSymbol:" + getName();
    }

    @Override
    @NotNull
    public SearchScope getUseScope() {
        return new LocalSearchScope(getParent().getParent());
    }

    @Override
    public int getTextOffset() {
        return getNameIdentifier().getTextOffset();
    }

    @Override
    protected boolean isVisibilitySupported() {
        return true;
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return new JavaIdentifier(nameId());
    }
}
