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
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.ElementClassHint;
import com.intellij.psi.scope.PsiScopeProcessor;
import org.argus.cit.intellij.jawa.lang.psi.JawaExpressionPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.JawaLocation;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaLocationImplMixin extends JawaExpressionPsiElement implements JawaLocation {
    public JawaLocationImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        PsiElement decl = getLocationDefSymbol();
        if(decl != lastParent) {
            if(!processor.execute(decl, state)) {
                return false;
            }
        } else {
            ElementClassHint hint = processor.getHint(ElementClassHint.KEY);
            if(lastParent instanceof PsiClass && (hint == null || hint.shouldProcess(ElementClassHint.DeclarationKind.CLASS)) && !processor.execute(lastParent, state)) {
                return false;
            }
        }
        return true;
    }
}
