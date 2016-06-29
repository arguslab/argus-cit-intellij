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
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.Stub;
import com.intellij.util.IncorrectOperationException;
import org.argus.cit.intellij.jawa.lang.psi.JawaAccessFlagAnnotation;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaAccessFlagStub;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaStubElementTypes;
import org.argus.jawa.core.AccessFlag;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaAccessFlagAnnotationImplMixin
        extends JawaStubBasedPsiElementBase<JawaAccessFlagStub>
        implements JawaAccessFlagAnnotation {

    public JawaAccessFlagAnnotationImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    public JawaAccessFlagAnnotationImplMixin(@NotNull JawaAccessFlagStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }


    @NotNull
    public PsiAnnotation[] getAnnotations() {
        return PsiAnnotation.EMPTY_ARRAY;
    }
    public PsiAnnotation findAnnotation(@NotNull String name) {
        return null;
    }
    @NotNull
    public PsiAnnotation addAnnotation(@NotNull String qualifiedName) {
        return (PsiAnnotation)this.addAfter(JavaPsiFacade.getInstance(this.getProject()).getElementFactory().createAnnotationFromText("@" + qualifiedName, this), null);
    }
    @NotNull
    public PsiAnnotation[] getApplicableAnnotations() {
        return PsiAnnotation.EMPTY_ARRAY;
    }

    public boolean hasModifierProperty(@NotNull String name) {
    Stub stub = getStub();
    if (stub != null) {
        return Arrays.asList(((JawaAccessFlagStub) stub).getModifiers()).contains(name);
    }
        return has(name);
    }

    public boolean hasExplicitModifier(@NotNull String s) {
        return false;
    }

    @Override
    public void setModifierProperty(@PsiModifier.ModifierConstant @NotNull @NonNls String s, boolean b) throws IncorrectOperationException {

    }

    @Override
    public void checkSetModifierProperty(@PsiModifier.ModifierConstant @NotNull @NonNls String s, boolean b) throws IncorrectOperationException {

    }

    public boolean has(String name) {
        return Arrays.asList(getModifiersStrings()).contains(name);
    }

//    @Nullable
//    public JawaAccessFlagAnnotation accessModifier() {
//        Stub stub = getStub();
//        if (stub != null) {
//            val am = stub.findChildStubByType(JawaStubElementTypes.ACCESS_FLAG());
//            if (am != null) {
//                return am.getPsi();
//            } else return null;
//        }
//        return findChild(classOf[JawaAccessFlagAnnotation]);
//    }

    public String[] getModifiersStrings() {
        JawaAccessFlagAnnotation modifier = getStubOrPsiChild(JawaStubElementTypes.ACCESS_FLAG());
        return modifier != null ? AccessFlag.toString(modifier.getModifiers()).split(" ") : new String[0];
    }

    public boolean hasExplicitModifiers() {
        return getModifiersStrings().length > 0;
    }
}