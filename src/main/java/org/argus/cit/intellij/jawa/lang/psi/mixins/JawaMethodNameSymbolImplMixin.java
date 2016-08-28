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
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.argus.cit.intellij.jawa.lang.psi.JawaMethodNameSymbol;
import org.argus.cit.intellij.jawa.lang.psi.JawaReferenceExpressionPsiElement;
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.synthetic.JavaIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaMethodNameSymbolImplMixin extends JawaReferenceExpressionPsiElement implements JawaMethodNameSymbol {
    public JawaMethodNameSymbolImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getReferenceNameElement() {
        return getApostropheId();
    }

    @Override
    public TextRange getRangeInElement() {
        return new TextRange(getApostropheId().getStartOffsetInParent(), getTextLength());
    }

    @Override
    public String toString() {
        return "JawaMethodNameSymbol:" + getText();
    }
}
