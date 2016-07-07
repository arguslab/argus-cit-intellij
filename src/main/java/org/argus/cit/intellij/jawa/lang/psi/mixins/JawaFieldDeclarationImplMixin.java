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
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.icons.Icons;
import org.argus.cit.intellij.jawa.lang.psi.JawaFieldDeclaration;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaTypeDefinition;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaFieldStub;
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem;
import org.argus.jawa.core.JavaKnowledge$;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

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
        JawaFieldStub stub = getStub();
        if(stub != null) {
            return stub.getParentStubOfType(JawaTypeDefinition.class);
        } else {
            return PsiTreeUtil.getContextOfType(this, true, JawaTypeDefinition.class);
        }
    }

    @Nullable
    @Override
    public PsiModifierList getModifierList() {
        return getAccessFlagAnnotation();
    }

    @Override
    public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @NotNull String s) {
        return getAccessFlagAnnotation().hasModifierProperty(s);
    }

    @Override
    public String name() {
        JawaFieldStub stub = getStub();
        if(stub != null) return stub.getName();
        else return JavaKnowledge$.MODULE$.getFieldNameFromFieldFQN(getFQN());
    }

    @Override
    public PsiElement nameId() {
        PsiElement defsymbol = getFieldDefSymbol();
        if(defsymbol != null) {
            return defsymbol;
        } else {
            return getStaticFieldDefSymbol();
        }
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String s) {
        throw new IncorrectOperationException("cannot set name");
    }

    @Override
    public Icon getIcon(int flags) {
        return Icons.Field();
    }
}
