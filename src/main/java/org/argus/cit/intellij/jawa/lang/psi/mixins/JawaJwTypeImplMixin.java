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
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiType;
import com.intellij.psi.stubs.IStubElementType;
import org.argus.cit.intellij.jawa.lang.psi.JawaJwType;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaJwTypeStub;
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaJwTypeImplMixin
        extends JawaStubBasedPsiElementBase<JawaJwTypeStub>
        implements JawaJwType {

    public JawaJwTypeImplMixin(@NotNull JawaJwTypeStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaJwTypeImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiType getType() {
        return JawaTypeSystem.toPsiType(getJawaType(), getProject(), getResolveScope());
    }

    @Nullable
    @Override
    public PsiJavaCodeReferenceElement getInnermostComponentReferenceElement() {
        return null;
    }

    @NotNull
    @Override
    public PsiAnnotation[] getAnnotations() {
        return PsiAnnotation.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public PsiAnnotation[] getApplicableAnnotations() {
        return PsiAnnotation.EMPTY_ARRAY;
    }

    @Nullable
    @Override
    public PsiAnnotation findAnnotation(@NotNull @NonNls String s) {
        return null;
    }

    @NotNull
    @Override
    public PsiAnnotation addAnnotation(@NotNull @NonNls String qualifiedName) {
        return (PsiAnnotation)this.addAfter(JavaPsiFacade.getInstance(this.getProject()).getElementFactory().createAnnotationFromText("@" + qualifiedName, this), null);
    }
}
