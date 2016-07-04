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
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.lang.psi.JawaParam;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.api.base.JawaPsiMethod;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamStub;
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        return PsiTreeUtil.getParentOfType(this, JawaPsiMethod.class);
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

    @Override
    public PsiElement setName(@NonNls @NotNull String s) {
        throw new IncorrectOperationException("cannot set name");
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
        JawaParamStub stub = getStub();
        if(stub != null) return stub.getName();
        else return getVarDefSymbol().getId().getText();
    }

    @Override
    public PsiElement nameId() {
        return getVarDefSymbol().getId();
    }
}
