/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.lang;

import javax.swing.Icon;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.ex.FileTypeIdentifiableByVirtualFile;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.argus.cit.intellij.core.icons.Icons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public class JawaFileType extends LanguageFileType implements FileTypeIdentifiableByVirtualFile {

    public static final JawaFileType INSTANCE = new JawaFileType();
    public static final Language JAWA_LANGUAGE = JawaLanguage.Instance;
    @NonNls
    public static final String DEFAULT_EXTENSION = "pilar";

    private JawaFileType() {
        super(JawaLanguage.Instance);
    }

    @NotNull
    @NonNls
    public String getName() {
        return "Jawa";
    }

    @NotNull
    public String getDescription() {
        return "Jawa files";
    }

    @NotNull
    @NonNls
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    public Icon getIcon() {
        return Icons.FILE();
    }

    @Override
    public boolean isMyFileType(@NotNull VirtualFile virtualFile) {
        return StringUtil.endsWith(virtualFile.getNameSequence(), ".pilar");
    }
}