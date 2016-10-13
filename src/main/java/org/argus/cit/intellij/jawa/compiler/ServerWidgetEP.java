/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.compiler;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.project.Project;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class ServerWidgetEP {
    public static ExtensionPointName<ServerWidgetEP> EP_NAME = ExtensionPointName.create("org.argus.cit.intellij.serverWidgetEP");

    public static ServerWidgetEP[] getAllWidgetEps() {
        return EP_NAME.getExtensions();
    }

    public abstract AnAction[] getAdditionalActions(Project project);
}