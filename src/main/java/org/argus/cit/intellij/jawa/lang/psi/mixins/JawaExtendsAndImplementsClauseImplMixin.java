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
import com.intellij.psi.impl.source.tree.JavaElementType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.IStubElementType;
import org.argus.cit.intellij.jawa.lang.psi.JawaExtendAndImplement;
import org.argus.cit.intellij.jawa.lang.psi.JawaExtendsAndImplementsClause;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedPsiElementBase;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaExtendsAndImplementsClauseStub;
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaExtendsAndImplementsClauseImplMixin
        extends JawaStubBasedPsiElementBase<JawaExtendsAndImplementsClauseStub>
        implements JawaExtendsAndImplementsClause {

    public JawaExtendsAndImplementsClauseImplMixin(@NotNull JawaExtendsAndImplementsClauseStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaExtendsAndImplementsClauseImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiJavaCodeReferenceElement[] getReferenceElements() {
        return calcTreeElement().getChildrenAsPsiElements(JavaElementType.JAVA_CODE_REFERENCE, PsiJavaCodeReferenceElement.ARRAY_FACTORY);
    }

    @NotNull
    @Override
    public PsiClassType[] getReferencedTypes() {
        List<JawaExtendAndImplement> eis = getExtendAndImplementList();
        List<PsiClassType> typs = new ArrayList<>();
        eis.forEach(ei -> {
            PsiType pt = JawaTypeSystem.toPsiType(ei.getTypeSymbol().getJawaType(), getProject(), GlobalSearchScope.allScope(getProject()));
            if(pt instanceof PsiClassType) typs.add((PsiClassType)pt);
        });
        PsiClassType[] res = new PsiClassType[typs.size()];
        return typs.toArray(res);
    }

    @Override
    public Role getRole() {
        return Role.EXTENDS_LIST;
    }
}
