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
import com.intellij.psi.impl.source.HierarchicalMethodSignatureImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.MethodSignatureBackedByPsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.icons.Icons;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaTypeDefinition;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.argus.cit.intellij.jawa.lang.psi.fake.FakePsiReferenceList;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaMethodStub;
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem;
import org.argus.jawa.core.AccessFlag;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaMethodDeclarationImplMixin
        extends JawaStubBasedPsiElementBase<JawaMethodStub>
        implements JawaMethodDeclaration {

    public JawaMethodDeclarationImplMixin(@NotNull JawaMethodStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaMethodDeclarationImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiType getReturnType() {
        return JawaTypeSystem.toPsiType(getJwType().getJawaType(), getProject(), getResolveScope());
    }

    @Nullable
    @Override
    public PsiTypeElement getReturnTypeElement() {
        return getJwType();
    }

    @NotNull
    @Override
    public PsiParameterList getParameterList() {
        return getParamClause();
    }

    @NotNull
    @Override
    public PsiReferenceList getThrowsList() {
        return new FakePsiReferenceList(getManager(), getLanguage(), PsiReferenceList.Role.THROWS_LIST);
    }

    @Nullable
    @Override
    public PsiCodeBlock getBody() {
        return null;
    }

    @Override
    public boolean isConstructor() {
        return AccessFlag.isConstructor(getAccessFlagAnnotation().getModifiers());
    }

    @Override
    public boolean isVarArgs() {
        return false;
    }

    @NotNull
    @Override
    public MethodSignatureBackedByPsiMethod getSignature(@NotNull PsiSubstitutor psiSubstitutor) {
        return MethodSignatureBackedByPsiMethod.create(this, psiSubstitutor);
    }

    @Nullable
    @Override
    public PsiIdentifier getNameIdentifier() {
        return new JavaIdentifier(nameId());
    }

    @NotNull
    @Override
    public PsiMethod[] findSuperMethods() {
        return new PsiMethod[0];
    }

    @NotNull
    @Override
    public PsiMethod[] findSuperMethods(boolean b) {
        return new PsiMethod[0];
    }

    @NotNull
    @Override
    public PsiMethod[] findSuperMethods(PsiClass psiClass) {
        return new PsiMethod[0];
    }

    @NotNull
    @Override
    public List<MethodSignatureBackedByPsiMethod> findSuperMethodSignaturesIncludingStatic(boolean b) {
        return new ArrayList<>();
    }

    @Nullable
    @Override
    @Deprecated
    public PsiMethod findDeepestSuperMethod() {
        return null;
    }

    @NotNull
    @Override
    public PsiMethod[] findDeepestSuperMethods() {
        return new PsiMethod[0];
    }

    @NotNull
    @Override
    public PsiModifierList getModifierList() {
        return getAccessFlagAnnotation();
    }

    @Override
    public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @NotNull String s) {
        return getAccessFlagAnnotation().hasModifierProperty(s);
    }

    @NotNull
    @Override
    public HierarchicalMethodSignature getHierarchicalMethodSignature() {
        return new HierarchicalMethodSignatureImpl(getSignature(PsiSubstitutor.EMPTY));
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

    @Override
    public boolean hasTypeParameters() {
        return false;
    }

    @Nullable
    @Override
    public PsiTypeParameterList getTypeParameterList() {
        return null;
    }

    @NotNull
    @Override
    public PsiTypeParameter[] getTypeParameters() {
        return PsiTypeParameter.EMPTY_ARRAY;
    }

    @Nullable
    @Override
    public PsiClass getContainingClass() {
        JawaMethodStub stub = getStub();
        if(stub != null) {
            return stub.getParentStubOfType(JawaTypeDefinition.class);
        } else {
            return PsiTreeUtil.getContextOfType(this, true, JawaTypeDefinition.class);
        }
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) {
//        if (isConstructor()) return this;
//        else {
//            ASTNode id = nameId().getNode();
//            ASTNode parent = id.getTreeParent();
//            ASTNode newid = JawaPsiElementFactory.
//        }
        throw new IncorrectOperationException("cannot set name");
    }

    @Override
    public String name() {
        JawaMethodStub stub = getStub();
        if(stub != null) return stub.getName();
        else return getSignatureAnnotation().getSignatureSymbol().getSignature().methodName();
    }

    @Override
    public PsiElement nameId() {
        return getMethodDefSymbol().getApostropheId();
    }

    @Override
    public Icon getIcon(int flags) {
        int mods = getAccessFlagAnnotation().getModifiers();
        if(AccessFlag.isAbstract(mods)) return Icons.AbstractMethod();
        else return Icons.Method();
    }
}
