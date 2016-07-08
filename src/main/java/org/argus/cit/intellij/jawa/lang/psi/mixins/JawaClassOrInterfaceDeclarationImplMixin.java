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
import com.intellij.openapi.util.Pair;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiClassImplUtil;
import com.intellij.psi.impl.PsiSuperMethodImplUtil;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.icons.Icons;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaClassOrInterfaceStub;
import org.argus.jawa.core.AccessFlag;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
public abstract class JawaClassOrInterfaceDeclarationImplMixin
        extends JawaStubBasedPsiElementBase<JawaClassOrInterfaceStub>
        implements JawaClassOrInterfaceDeclaration {

    public JawaClassOrInterfaceDeclarationImplMixin(@NotNull JawaClassOrInterfaceStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaClassOrInterfaceDeclarationImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public String getQualifiedName() {
        JawaClassOrInterfaceStub stub = getStub();
        if(stub != null) return stub.javaQualName();
        else return javaQualName();
    }

    private String javaQualName() {
        return getTypeDefSymbol().getJawaType().name();
    }

    @Override
    public boolean isInterface() {
        boolean isinterface = getKindAnnotation().getId().getText().equals("interface");
        return isinterface || hasModifierProperty("interface");
    }

    @Override
    public boolean isAnnotationType() {
        return false;
    }

    @Override
    public boolean isEnum() {
        return false;
    }

    @Nullable
    @Override
    public PsiReferenceList getExtendsList() {
        return getExtendsAndImplementsClauses();
    }

    @Nullable
    @Override
    public PsiReferenceList getImplementsList() {
        return getExtendsAndImplementsClauses();
    }

    @NotNull
    @Override
    public PsiClassType[] getExtendsListTypes() {
        JawaExtendsAndImplementsClauses eic = getExtendsAndImplementsClauses();
        if(eic != null) return eic.getReferencedTypes();
        else return PsiClassType.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public PsiClassType[] getImplementsListTypes() {
        JawaExtendsAndImplementsClauses eic = getExtendsAndImplementsClauses();
        if(eic != null) return eic.getReferencedTypes();
        else return PsiClassType.EMPTY_ARRAY;
    }

    @Nullable
    @Override
    public PsiClass getSuperClass() {
        return null;
    }

    @Override
    public PsiClass[] getInterfaces() {
        return new PsiClass[0];
    }

    @NotNull
    @Override
    public PsiClass[] getSupers() {
        return new PsiClass[0];
    }

    @NotNull
    @Override
    public PsiClassType[] getSuperTypes() {
        return new PsiClassType[0];
    }

    @NotNull
    @Override
    public PsiField[] getFields() {
        List<JawaFieldDeclaration> jfds = new ArrayList<>();
        List<JawaInstanceFieldDeclaration> ifl = getInstanceFieldDeclarationBlock().getInstanceFieldDeclarationList();
        List<JawaStaticFieldDeclaration> sfl = getStaticFieldDeclarationList();
        ifl.forEach(i -> jfds.add(i.getFieldDeclaration()));
        sfl.forEach(i -> jfds.add(i.getFieldDeclaration()));
        PsiField[] fields = new PsiField[jfds.size()];
        return jfds.toArray(fields);
    }

    @NotNull
    @Override
    public PsiMethod[] getMethods() {
        List<JawaMethodDeclaration> mds = getMethodDeclarationList();
        PsiMethod[] methods = new PsiMethod[mds.size()];
        return mds.toArray(methods);
    }

    @NotNull
    @Override
    public PsiMethod[] getConstructors() {
        return new PsiMethod[0];
    }

    @NotNull
    @Override
    public PsiClass[] getInnerClasses() {
        return new PsiClass[0];
    }

    @NotNull
    @Override
    public PsiClassInitializer[] getInitializers() {
        return new PsiClassInitializer[0];
    }

    @NotNull
    @Override
    public PsiField[] getAllFields() {
        return getFields();
    }

    @NotNull
    @Override
    public PsiMethod[] getAllMethods() {
        return getMethods();
    }

    @NotNull
    @Override
    public PsiClass[] getAllInnerClasses() {
        return new PsiClass[0];
    }

    @Nullable
    @Override
    public PsiField findFieldByName(@NonNls String s, boolean b) {
        return null;
    }

    @Nullable
    @Override
    public PsiMethod findMethodBySignature(PsiMethod psiMethod, boolean b) {
        return null;
    }

    @NotNull
    @Override
    public PsiMethod[] findMethodsBySignature(PsiMethod psiMethod, boolean b) {
        return new PsiMethod[0];
    }

    @NotNull
    @Override
    public PsiMethod[] findMethodsByName(@NonNls String s, boolean b) {
        return new PsiMethod[0];
    }

    @NotNull
    @Override
    public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(@NonNls String name, boolean checkBases) {
        return PsiClassImplUtil.findMethodsAndTheirSubstitutorsByName(this, name, checkBases);
    }

    @NotNull
    @Override
    public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors() {
        return PsiClassImplUtil.getAllWithSubstitutorsByMap(this, PsiClassImplUtil.MemberType.METHOD);
    }

    @Nullable
    @Override
    public PsiClass findInnerClassByName(@NonNls String s, boolean b) {
        return null;
    }

    @Nullable
    @Override
    public PsiElement getLBrace() {
        return null;
    }

    @Nullable
    @Override
    public PsiElement getRBrace() {
        return null;
    }

    @Nullable
    @Override
    public PsiIdentifier getNameIdentifier() {
        return new JavaIdentifier(nameId());
    }

    @Override
    public PsiElement getScope() {
        return null;
    }

    @Override
    public boolean isInheritor(@NotNull PsiClass psiClass, boolean b) {
        return false;
    }

    @Override
    public boolean isInheritorDeep(PsiClass psiClass, @Nullable PsiClass psiClass1) {
        return false;
    }

    @Nullable
    @Override
    public PsiClass getContainingClass() {
        return null;
    }

    @NotNull
    @Override
    public Collection<HierarchicalMethodSignature> getVisibleSignatures() {
        return PsiSuperMethodImplUtil.getVisibleSignatures(this);
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String s) {
        throw new IncorrectOperationException("cannot set name");
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
        return new PsiTypeParameter[0];
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
        JawaClassOrInterfaceStub stub = getStub();
        if(stub != null) return stub.getName();
        else return getTypeDefSymbol().getJawaType().simpleName();
    }

    @Override
    public PsiElement nameId() {
        return getTypeDefSymbol().getApostropheId();
    }

    @Override
    public Icon getIcon(int flags) {
        int mods = getAccessFlagAnnotation().getModifiers();
        if(AccessFlag.isInterface(mods)) return Icons.Interface();
        else if(AccessFlag.isAbstract(mods)) return Icons.AbstractClass();
        else return Icons.Class();
    }
}