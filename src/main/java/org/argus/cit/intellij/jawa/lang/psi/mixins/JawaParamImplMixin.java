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
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProviders;
import com.intellij.psi.*;
import com.intellij.psi.impl.ElementPresentationUtil;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.RowIcon;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.PlatformIcons;
import org.argus.cit.intellij.jawa.lang.psi.JawaParam;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaPsiMethod;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamStub;
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaParamImplMixin
        extends JawaStubBasedPsiElementBase<JawaParamStub>
        implements JawaParam {

    public JawaParamImplMixin(@NotNull JawaParamStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaParamImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiElement getDeclarationScope() {
        return getParent().getParent();
    }

    @Override
    public boolean isVarArgs() {
        return false;
    }

    @NotNull
    @Override
    public PsiType getType() {
        return JawaTypeSystem.toPsiType(getJwType().getJawaType(), getProject(), getResolveScope());
    }

    @Nullable
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

    @NotNull
    public final String getName() {
        JawaParamStub stub = getStub();
        if(stub != null) return stub.getName();
        else return getVarDefSymbol().getId().getText();
    }

    public final PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        PsiImplUtil.setName(this.getNameIdentifier(), name);
        return this;
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
    public PsiElement nameId() {
        return getVarDefSymbol();
    }

    @Override
    public ItemPresentation getPresentation() {
        return ItemPresentationProviders.getItemPresentation(this);
    }

    @Override
    public int getTextOffset() {
        return getNameIdentifier().getTextOffset();
    }

    @Override
    public Icon getElementIcon(int flags) {
        RowIcon baseIcon = createLayeredIcon(this, PlatformIcons.PARAMETER_ICON, 0);
        return ElementPresentationUtil.addVisibilityIcon(this, flags, baseIcon);
    }

    @Override
    protected boolean isVisibilitySupported() {
        return true;
    }

    @Override
    @NotNull
    public SearchScope getUseScope() {
        PsiElement declarationScope = this.getDeclarationScope();
        return new LocalSearchScope(declarationScope);
    }

    @Override
    public String toString() {
        return "JawaParam:" + this.getName();
    }
}
