/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.argus.cit.intellij.core.icons.Icons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public class JawaFileType extends LanguageFileType {
    public static final JawaFileType INSTANCE = new JawaFileType();

    private JawaFileType() {
        super(JawaLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Jawa";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Jawa files";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "pilar";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return Icons.FILE();
    }
}
