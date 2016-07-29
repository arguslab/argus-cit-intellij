/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa;

import org.argus.jc.incremental.jawa.model.*;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsGlobal;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.library.JpsLibrary;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.Collection;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public class SettingsManager {
    public static final JpsElementChildRoleBase<GlobalSettings> GLOBAL_SETTINGS_ROLE = JpsElementChildRoleBase.create("jawa global settings");
    public static final JpsElementChildRoleBase<ProjectSettings> PROJECT_SETTINGS_ROLE = JpsElementChildRoleBase.create("jawa project settings");

    public static GlobalSettings getGlobalSettings(JpsGlobal global) {
        GlobalSettings settings = global.getContainer().getChild(GLOBAL_SETTINGS_ROLE);
        return settings == null ? GlobalSettingsImpl$.MODULE$.DEFAULT() : settings;
    }

    public static void setGlobalSettings(JpsGlobal global, GlobalSettings settings) {
        global.getContainer().setChild(GLOBAL_SETTINGS_ROLE, settings);
    }

    public static ProjectSettings getProjectSettings(JpsProject project) {
        ProjectSettings settings = project.getContainer().getChild(PROJECT_SETTINGS_ROLE);
        return settings == null ? ProjectSettingsImpl$.MODULE$.DEFAULT() : settings;
    }

    public static void setProjectSettings(JpsProject project, ProjectSettings settings) {
        project.getContainer().setChild(PROJECT_SETTINGS_ROLE, settings);
    }
}
