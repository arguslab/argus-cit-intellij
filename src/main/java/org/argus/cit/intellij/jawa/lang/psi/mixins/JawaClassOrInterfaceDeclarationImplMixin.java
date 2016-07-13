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
import com.intellij.psi.impl.InheritanceImplUtil;
import com.intellij.psi.impl.PsiClassImplUtil;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.impl.PsiSuperMethodImplUtil;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
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
import java.util.Arrays;
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
        return getExtendsAndImplementsClause();
    }

    @Nullable
    @Override
    public PsiReferenceList getImplementsList() {
        return getExtendsAndImplementsClause();
    }

    @NotNull
    @Override
    public PsiClassType[] getExtendsListTypes() {
        JawaExtendsAndImplementsClause eic = getExtendsAndImplementsClause();
        if(eic != null) return eic.getReferencedTypes();
        else return PsiClassType.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public PsiClassType[] getImplementsListTypes() {
        JawaExtendsAndImplementsClause eic = getExtendsAndImplementsClause();
        if(eic != null) return eic.getReferencedTypes();
        else return PsiClassType.EMPTY_ARRAY;
    }

    @Nullable
    @Override
    public PsiClass getSuperClass() {
        return PsiClassImplUtil.getSuperClass(this);
    }

    @Override
    public PsiClass[] getInterfaces() {
        return PsiClassImplUtil.getInterfaces(this);
    }

    @NotNull
    @Override
    public PsiClass[] getSupers() {
        return PsiClassImplUtil.getSupers(this);
    }

    @NotNull
    @Override
    public PsiClassType[] getSuperTypes() {
        return PsiClassImplUtil.getSuperTypes(this);
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
        List<PsiMethod> consts = new ArrayList<>();
        Arrays.asList(getMethods()).forEach(m -> {
            if(m.isConstructor()) consts.add(m);
        });
        PsiMethod[] methods = new PsiMethod[consts.size()];
        return consts.toArray(methods);
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
        return getInnerClasses();
    }

    @Nullable
    @Override
    public PsiField findFieldByName(@NonNls String name, boolean checkBases) {
        return PsiClassImplUtil.findFieldByName(this, name, checkBases);
    }

    @Nullable
    @Override
    public PsiMethod findMethodBySignature(PsiMethod patternMethod, boolean checkBases) {
        return PsiClassImplUtil.findMethodBySignature(this, patternMethod, checkBases);
    }

    @NotNull
    @Override
    public PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases) {
        return PsiClassImplUtil.findMethodsBySignature(this, patternMethod, checkBases);
    }

    @NotNull
    @Override
    public PsiMethod[] findMethodsByName(@NonNls String name, boolean checkBases) {
        return PsiClassImplUtil.findMethodsByName(this, name, checkBases);
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
        final JawaClassOrInterfaceStub stub = getStub();
        if (stub != null) {
            return stub.getParentStub().getPsi();
        }

        ASTNode treeElement = getNode();
        ASTNode parent = treeElement.getTreeParent();

        while(parent != null) {
            if (parent.getElementType() instanceof IStubElementType){
                return parent.getPsi();
            }
            parent = parent.getTreeParent();
        }

        return getContainingFile();
    }

    @Override
    public boolean isInheritor(@NotNull PsiClass baseClass, boolean checkDeep) {
        return InheritanceImplUtil.isInheritor(this, baseClass, checkDeep);
    }

    @Override
    public boolean isInheritorDeep(PsiClass baseClass, @Nullable PsiClass classToByPass) {
        return InheritanceImplUtil.isInheritorDeep(this, baseClass, classToByPass);
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
    public PsiElement setName(@NonNls @NotNull String newName) {
        boolean isRenameFile = isRenameFileOnRenaming();

        PsiIdentifier id = getNameIdentifier();
        if(id != null)
            PsiImplUtil.setName(id, newName);

        if (isRenameFile) {
            PsiFile file = (PsiFile)getParent();
            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            file.setName(dotIndex >= 0 ? newName + "." + fileName.substring(dotIndex + 1) : newName);
        }

        return this;
    }

    private boolean isRenameFileOnRenaming() {
        final PsiElement parent = getParent();
        if (parent instanceof PsiFile) {
            PsiFile file = (PsiFile)parent;
            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            String name = dotIndex >= 0 ? fileName.substring(0, dotIndex) : fileName;
            String oldName = getName();
            return name.equals(oldName);
        }
        else {
            return false;
        }
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
    public String getName() {
        return name();
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