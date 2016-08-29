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
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.impl.PsiSuperMethodImplUtil;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.*;
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

    @NotNull
    @Override
    public String getName() {
        if(isConstructor() && getContainingClass() != null && getContainingClass().getName() != null) {
            return getContainingClass().getName();
        } else {
            return name();
        }
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
    public MethodSignature getSignature(@NotNull PsiSubstitutor substitutor) {
        JawaMethodDeclaration outthis = this;
        if (substitutor == PsiSubstitutor.EMPTY) {
            return CachedValuesManager.getCachedValue(this, () -> {
                MethodSignature signature = MethodSignatureBackedByPsiMethod.create(outthis, PsiSubstitutor.EMPTY);
                return CachedValueProvider.Result.create(signature, PsiModificationTracker.JAVA_STRUCTURE_MODIFICATION_COUNT);
            });
        }
        return MethodSignatureBackedByPsiMethod.create(this, substitutor);
    }

    @Nullable
    @Override
    public PsiIdentifier getNameIdentifier() {
        return new JavaIdentifier(nameId());
    }

    @NotNull
    @Override
    public PsiMethod[] findSuperMethods() {
        return PsiSuperMethodImplUtil.findSuperMethods(this);
    }

    @NotNull
    @Override
    public PsiMethod[] findSuperMethods(boolean checkAccess) {
        return PsiSuperMethodImplUtil.findSuperMethods(this, checkAccess);
    }

    @NotNull
    @Override
    public PsiMethod[] findSuperMethods(PsiClass parentClass) {
        return PsiSuperMethodImplUtil.findSuperMethods(this, parentClass);
    }

    @NotNull
    @Override
    public List<MethodSignatureBackedByPsiMethod> findSuperMethodSignaturesIncludingStatic(boolean checkAccess) {
        return PsiSuperMethodImplUtil.findSuperMethodSignaturesIncludingStatic(this, checkAccess);
    }

    @Nullable
    @Override
    @Deprecated
    public PsiMethod findDeepestSuperMethod() {
        return PsiSuperMethodImplUtil.findDeepestSuperMethod(this);
    }

    @NotNull
    @Override
    public PsiMethod[] findDeepestSuperMethods() {
        return PsiSuperMethodImplUtil.findDeepestSuperMethods(this);
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
        return PsiSuperMethodImplUtil.getHierarchicalMethodSignature(this);
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
        PsiElement parent = getParent();
        return parent instanceof PsiClass ? (PsiClass)parent : PsiTreeUtil.getParentOfType(this, JawaTypeDefinition.class);
    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        boolean result = PsiImplUtil.processDeclarationsInMethod(this, processor, state, lastParent, place);
        if(result) {
            boolean fromBody = lastParent instanceof PsiCodeBlock;
            if(fromBody) {
                List<JawaParam> list = getParamClause().getParamList();
                if(list.size() > 0) {
                    PsiParameter parameter = list.get(0);
                    if(!processor.execute(parameter, state)) {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) {
        final PsiIdentifier identifier = getNameIdentifier();
        if (identifier == null) throw new IncorrectOperationException("Empty name: " + this);
        PsiImplUtil.setName(identifier, name);
        return this;
    }

    @Override
    public String name() {
        JawaMethodStub stub = getStub();
        if(stub != null) return stub.getName();
        else return getSignatureAnnotation().getSignatureSymbol().getSignature().methodName();
    }

    @Override
    public PsiElement nameId() {
        return getMethodDefSymbol();
    }

    @Override
    public Icon getIcon(int flags) {
        int mods = getAccessFlagAnnotation().getModifiers();
        if(AccessFlag.isAbstract(mods)) return Icons.AbstractMethod();
        else return Icons.Method();
    }
}
