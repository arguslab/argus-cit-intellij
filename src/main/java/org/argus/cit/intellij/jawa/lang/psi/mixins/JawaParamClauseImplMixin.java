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
import com.intellij.psi.PsiParameter;
import com.intellij.psi.stubs.IStubElementType;
import org.argus.cit.intellij.jawa.lang.psi.*;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaParamClauseStub;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaParamClauseImplMixin
        extends JawaStubBasedPsiElementBase<JawaParamClauseStub>
        implements JawaParamClause {

    public JawaParamClauseImplMixin(@NotNull JawaParamClauseStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JawaParamClauseImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiParameter[] getParameters() {
        List<JawaParam> list = getParamList();
        List<JawaParam> tmp = new ArrayList<>();
        for(JawaParam jawaParam: list) {
            for(JawaAnnotation anno: jawaParam.getAnnotationList()) {
                JawaKindAnnotation ka = anno.getKindAnnotation();
                if(ka != null && ka.getId().getText().equals("this")) {
                    tmp.add(jawaParam);
                }
            }
        }
        list.removeAll(tmp);
        return list.toArray(new JawaParam[list.size()]);
    }

    @Override
    public int getParameterIndex(@NotNull PsiParameter psiParameter) {
        return Arrays.asList(getParameters()).indexOf(psiParameter);
    }

    @Override
    public int getParametersCount() {
        return getParameters().length;
    }
}
