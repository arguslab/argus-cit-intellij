/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.mixins;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProviders;
import com.intellij.psi.*;
import com.intellij.psi.impl.ElementPresentationUtil;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.ui.RowIcon;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.PlatformIcons;
import org.argus.cit.intellij.jawa.lang.psi.JawaExpressionPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.JawaLocalVarDeclaration;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaLocalVarDeclarationImplMixin extends JawaExpressionPsiElement implements JawaLocalVarDeclaration {
    public JawaLocalVarDeclarationImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public void setInitializer(@Nullable PsiExpression psiExpression) throws IncorrectOperationException {
    }

    @NotNull
    @Override
    public PsiType getType() {
        return getTypeElement().getType();
    }

    @NotNull
    @Override
    public PsiTypeElement getTypeElement() {
        return getJwType();
    }

    @Nullable
    @Override
    public PsiExpression getInitializer() {
        return null;
    }

    @Override
    public boolean hasInitializer() {
        return false;
    }

    @Override
    public void normalizeDeclaration() throws IncorrectOperationException {

    }

    @Nullable
    @Override
    public Object computeConstantValue() {
        return null;
    }

    @Nullable
    @Override
    public PsiIdentifier getNameIdentifier() {
        return new JavaIdentifier(nameId());
    }

    @Nullable
    @Override
    public PsiModifierList getModifierList() {
        return null;
    }

    @Override
    public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @NotNull String s) {
        return false;
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
        return getVarDefSymbol();
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) {
        PsiImplUtil.setName(getNameIdentifier(), name);
        return this;
    }

    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
//        if (lastParent == null) return true;
//        if (lastParent.getParent() != this) return true;
        return processor.execute(this, state);
    }

    @Override
    public ItemPresentation getPresentation() {
        return ItemPresentationProviders.getItemPresentation(this);
    }

    public String toString() {
        return "JawaLocalVarDeclaration:" + getName();
    }

    @Override
    @NotNull
    public SearchScope getUseScope() {
        return new LocalSearchScope(getParent());
    }

    @Override
    public Icon getElementIcon(final int flags) {
        final RowIcon baseIcon = ElementPresentationUtil.createLayeredIcon(PlatformIcons.VARIABLE_ICON, this, false);
        return ElementPresentationUtil.addVisibilityIcon(this, flags, baseIcon);
    }

    @Override
    public int getTextOffset() {
        return getNameIdentifier().getTextOffset();
    }

    @Override
    protected boolean isVisibilitySupported() {
        return true;
    }
}
