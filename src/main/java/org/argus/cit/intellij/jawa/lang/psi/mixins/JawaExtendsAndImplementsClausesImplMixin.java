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
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiType;
import com.intellij.psi.stubs.IStubElementType;
import org.apache.commons.lang.ArrayUtils;
import org.argus.cit.intellij.jawa.lang.psi.JawaExtendAndImplement;
import org.argus.cit.intellij.jawa.lang.psi.JawaExtendsAndImplementsClauses;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaExtendsAndImplementsClausesStub;
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaExtendsAndImplementsClausesImplMixin
        extends JawaStubBasedPsiElementBase<JawaExtendsAndImplementsClausesStub>
        implements JawaExtendsAndImplementsClauses {

    public JawaExtendsAndImplementsClausesImplMixin(@NotNull JawaExtendsAndImplementsClausesStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaExtendsAndImplementsClausesImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiJavaCodeReferenceElement[] getReferenceElements() {
        return new PsiJavaCodeReferenceElement[0];
    }

    @NotNull
    @Override
    public PsiClassType[] getReferencedTypes() {
        List<JawaExtendAndImplement> eis = getExtendAndImplementList();
        PsiClassType[] typs = new PsiClassType[eis.size()];
        eis.forEach(ei -> {
            PsiType pt = JawaTypeSystem.toPsiType(ei.getTypeSymbol().getJawaType(), getProject(), getResolveScope());
            if(pt instanceof PsiClassType) ArrayUtils.add(typs, pt);
        });
        return typs;
    }

    @Override
    public Role getRole() {
        return Role.EXTENDS_LIST;
    }
}
