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
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.*;
import com.intellij.psi.impl.*;
import com.intellij.psi.impl.source.ClassInnerStuffCache;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.util.PsiUtil;
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
        addTrace(null);
    }

    public JawaClassOrInterfaceDeclarationImplMixin(@NotNull ASTNode node) {
        super(node);
        addTrace(null);
    }

    private final ClassInnerStuffCache myInnersCache = new ClassInnerStuffCache(this);
    private volatile String myCachedName;

    private void addTrace(@Nullable JawaClassOrInterfaceStub stub) {
        if (ourTraceStubAstBinding) {
            String creationTrace = "Creation thread: " + Thread.currentThread() + "\n" + DebugUtil.currentStackTrace();
            if (stub != null) {
                creationTrace += "\nfrom stub " + stub + "@" + System.identityHashCode(stub) + "\n";
                if (stub instanceof UserDataHolder) {
                    String stubTrace = ((UserDataHolder)stub).getUserData(CREATION_TRACE);
                    if (stubTrace != null) {
                        creationTrace += stubTrace;
                    }
                }
            }
            putUserData(CREATION_TRACE, creationTrace);
        }
    }

    @Override
    public void subtreeChanged() {
        dropCaches();
        super.subtreeChanged();
    }

    private void dropCaches() {
        myInnersCache.dropCaches();
        myCachedName = null;
    }

    @Override
    protected Object clone() {
        JawaClassOrInterfaceDeclarationImplMixin clone = (JawaClassOrInterfaceDeclarationImplMixin)super.clone();
        clone.dropCaches();
        return clone;
    }

    @Override
    public PsiElement getOriginalElement() {
        return CachedValuesManager.getCachedValue(this, () -> {
            final JavaPsiImplementationHelper helper = JavaPsiImplementationHelper.getInstance(getProject());
            final PsiClass result = helper != null ? helper.getOriginalClass(JawaClassOrInterfaceDeclarationImplMixin.this) : JawaClassOrInterfaceDeclarationImplMixin.this;
            return CachedValueProvider.Result.create(result, PsiModificationTracker.JAVA_STRUCTURE_MODIFICATION_COUNT);
        });
    }

    @Nullable
    @Override
    public String getQualifiedName() {
        JawaClassOrInterfaceStub stub = getStub();
        if(stub != null) return stub.javaQualName();
        else return javaQualName();
    }

    private String javaQualName() {
        return getTypeDefSymbol().getJawaType().jawaName();
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
        return myInnersCache.getFields();
    }

    @NotNull
    @Override
    public PsiMethod[] getMethods() {
        return myInnersCache.getMethods();
    }

    @NotNull
    @Override
    public PsiMethod[] getConstructors() {
        return myInnersCache.getConstructors();
    }

    @NotNull
    @Override
    public PsiClass[] getInnerClasses() {
        return myInnersCache.getInnerClasses();
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
        return myInnersCache.findFieldByName(name, checkBases);
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
        return myInnersCache.findMethodsByName(name, checkBases);
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
    public PsiClass findInnerClassByName(@NonNls String name, boolean checkBases) {
        return myInnersCache.findInnerClassByName(name, checkBases);
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
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        LanguageLevel level = PsiUtil.getLanguageLevel(place);
        return PsiClassImplUtil.processDeclarationsInClass(this, processor, state, null, lastParent, place, level, false);
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
        String name = myCachedName;
        if(name != null) return name;
        JawaClassOrInterfaceStub stub = getStub();
        if(stub != null) name = stub.getName();
        else name = getTypeDefSymbol().getJawaType().simpleName();
        myCachedName = name;
        return name;
    }

    @Override
    public PsiElement nameId() {
        return getTypeDefSymbol();
    }

//    @Override
//    public Icon getIcon(int flags) {
//        int mods = getAccessFlagAnnotation().getModifiers();
//        if(isInterface()) return Icons.Interface();
//        else if(AccessFlag.isAbstract(mods)) return Icons.AbstractClass();
//        else return Icons.Class();
//    }
    @Override
    public Icon getElementIcon(int flags) {
        return PsiClassImplUtil.getClassIcon(flags, this);
    }

    @Override
    public ItemPresentation getPresentation() {
        return ItemPresentationProviders.getItemPresentation(this);
    }

    @Override
    public int getTextOffset() {
        return nameId().getTextOffset();
    }

    @Override
    protected boolean isVisibilitySupported() {
        return true;
    }

    @NotNull
    @Override
    public List<PsiField> getOwnFields() {
        List<PsiField> jfds = new ArrayList<>();
        List<JawaInstanceFieldDeclaration> ifl = getInstanceFieldDeclarationBlock().getInstanceFieldDeclarationList();
        List<JawaStaticFieldDeclaration> sfl = getStaticFieldDeclarationList();
        ifl.forEach(i -> jfds.add(i.getFieldDeclaration()));
        sfl.forEach(i -> jfds.add(i.getFieldDeclaration()));
        return jfds;
    }

    @NotNull
    @Override
    public List<PsiMethod> getOwnMethods() {
        List<PsiMethod> jmds = new ArrayList<>();
        List<JawaMethodDeclaration> mds = getMethodDeclarationList();
        mds.forEach(jmds::add);
        return jmds;
    }

    @NotNull
    @Override
    public List<PsiClass> getOwnInnerClasses() {
        return new ArrayList<>();
    }
}