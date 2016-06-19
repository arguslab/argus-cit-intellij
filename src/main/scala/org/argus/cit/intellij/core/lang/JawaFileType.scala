/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.lang

import javax.swing.Icon

import com.intellij.openapi.fileTypes.LanguageFileType
import org.argus.cit.intellij.core.icons.Icons

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
object JawaFileType extends LanguageFileType(JawaLanguage) {
    override def getDefaultExtension: String = "pilar"

    override def getName: String = "Jawa"

    override def getIcon: Icon = Icons.FILE

    override def getDescription: String = "Jawa files"
}
