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
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.lang.psi.JawaFieldDeclaration;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaFieldStub;
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaFieldDeclarationImplMixin extends JawaStubBasedPsiElementBase<JawaFieldStub>
        implements JawaFieldDeclaration {
    public JawaFieldDeclarationImplMixin(@NotNull JawaFieldStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaFieldDeclarationImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public void setInitializer(@Nullable PsiExpression psiExpression) throws IncorrectOperationException {

    }

    @NotNull
    @Override
    public PsiType getType() {
        return JawaTypeSystem.toPsiType(getJwType().getJawaType(), getProject(), getResolveScope());
    }

    @Nullable
    @Override
    public PsiTypeElement getTypeElement() {
        return null;
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

    @NotNull
    @Override
    public PsiIdentifier getNameIdentifier() {
        return new JavaIdentifier(nameId());
    }

    @Nullable
    @Override
    public PsiDocComment getDocComment() {
        return null;
    }

    @Override
    public boolean isDeprecated() {
        return false;
    }

    @Nullable
    @Override
    public PsiClass getContainingClass() {
        return null;
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
        return null;
    }

    @Override
    public PsiElement nameId() {
        return null;
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String s) {
        return null;
    }
}
