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
import com.intellij.openapi.util.Couple;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.*;
import com.intellij.psi.scope.ElementClassHint;
import com.intellij.psi.scope.NameHint;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.scope.util.PsiScopesUtil;
import gnu.trove.THashSet;
import org.argus.cit.intellij.jawa.lang.psi.JawaExpressionPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.JawaJwBody;
import org.argus.cit.intellij.jawa.lang.psi.JawaLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaJwBodyImplMixin extends JawaExpressionPsiElement implements JawaJwBody {
    public JawaJwBodyImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiStatement[] getStatements() {
        List<JawaLocation> locs = getLocationList();
        PsiStatement[] pss = new PsiStatement[locs.size()];
        return locs.toArray(pss);
    }

    @Nullable
    @Override
    public PsiElement getFirstBodyElement() {
        return getLBrace();
    }

    @Nullable
    @Override
    public PsiElement getLastBodyElement() {
        return getRBrace();
    }

    @Nullable
    @Override
    public PsiJavaToken getLBrace() {
        return null;
    }

    @Nullable
    @Override
    public PsiJavaToken getRBrace() {
        return null;
    }

    private volatile Set<String> myVariablesSet;
    private volatile Set<String> myLocationSet;
    private volatile boolean myConflict;

    @Nullable
    private Couple<Set<String>> buildMaps() {
        Set<String> varSet = myVariablesSet;
        Set<String> locSet = myLocationSet;
        boolean wasConflict = myConflict;
        if (varSet == null || locSet == null) {
            final Set<String> localsSet = new THashSet<>();
            final Set<String> locationsSet = new THashSet<>();
            final Ref<Boolean> conflict = new Ref<>(Boolean.FALSE);
            getLocalVarDeclarationList().forEach(lvd -> {
                final String name = lvd.getName();
                if (!localsSet.add(name)) {
                    conflict.set(Boolean.TRUE);
                    localsSet.clear();
                }
            });
            getLocationList().forEach(ld -> {
                String loc = ld.getLocationDefSymbol().getLocationId().getText();
                if(!loc.equals("#.")) {
                    loc = loc.substring(1, loc.length() - 1);
                    if (!locationsSet.add(loc)) {
                        conflict.set(Boolean.TRUE);
                        locationsSet.clear();
                    }
                }

            });
            myVariablesSet = varSet = localsSet.isEmpty() ? Collections.emptySet() : localsSet;
            myLocationSet = locSet = locationsSet.isEmpty() ? Collections.emptySet() : locationsSet;
            myConflict = wasConflict = conflict.get();
        }
        return wasConflict ? null : Couple.of(varSet, locSet);
    }

    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
        processor.handleEvent(PsiScopeProcessor.Event.SET_DECLARATION_HOLDER, this);
        if (lastParent == null) {
            // Parent element should not see our vars
            return true;
        }
        Couple<Set<String>> set = buildMaps();
        boolean conflict = set == null;
        final Set<String> variablesSet = conflict ? null : set.first;
        final Set<String> locationsSet = conflict ? null : set.second;
        final NameHint hint = processor.getHint(NameHint.KEY);
        if (hint != null && !conflict) {
            final ElementClassHint elementClassHint = processor.getHint(ElementClassHint.KEY);
            final String name = hint.getName(state);
            if ((elementClassHint == null || elementClassHint.shouldProcess(ElementClassHint.DeclarationKind.VARIABLE)) && variablesSet.contains(name)) {
                return PsiScopesUtil.walkChildrenScopes(this, processor, state, lastParent, place);
            }
            if ((elementClassHint == null || elementClassHint.shouldProcess(ElementClassHint.DeclarationKind.VARIABLE)) && locationsSet.contains(name)) {
                return PsiScopesUtil.walkChildrenScopes(this, processor, state, null, place);
            }
        }
        else {
            return PsiScopesUtil.walkChildrenScopes(this, processor, state, lastParent, place);
        }
        return true;
    }

    @Override
    public boolean shouldChangeModificationCount(PsiElement psiElement) {
        return false;
    }
}
