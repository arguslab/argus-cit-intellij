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
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiType;
import org.argus.cit.intellij.jawa.lang.psi.JawaArgClause;
import org.argus.cit.intellij.jawa.lang.psi.JawaExpressionPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.JawaVarSymbol;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaArgClauseImplMixin extends JawaExpressionPsiElement implements JawaArgClause {

    public JawaArgClauseImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiExpression[] getExpressions() {
        List<JawaVarSymbol> vsl = getVarSymbolList();
        JawaVarSymbol[] jvs = new JawaVarSymbol[vsl.size()];
        return vsl.toArray(jvs);
    }

    @NotNull
    @Override
    public PsiType[] getExpressionTypes() {
        PsiExpression[] expressions = this.getExpressions();
        PsiType[] types = PsiType.createArray(expressions.length);

        for(int i = 0; i < types.length; ++i) {
            types[i] = expressions[i].getType();
        }

        return types;
    }
}
