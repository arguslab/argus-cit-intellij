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

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;

import java.util.UUID;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
@State(
        name = "JawaSettings",
        storages = {@Storage("jawa.xml")}
)
public class JawaCompileServerSettings implements PersistentStateComponent<JawaCompileServerSettings> {
    public boolean SHOW_TYPE_TOOLTIP_ON_MOUSE_HOVER = false;
    public int SHOW_TYPE_TOOLTIP_DELAY = 500;

    public boolean COMPILE_SERVER_ENABLED = true;

    //is not accessible from UI, but is serialized and used in jps-plugin
    public int COMPILE_SERVER_PORT = 5210;
    public String COMPILE_SERVER_ID = UUID.randomUUID().toString();

    public String COMPILE_SERVER_SDK;
    public String COMPILE_SERVER_MAXIMUM_HEAP_SIZE = "1024";
    public String COMPILE_SERVER_JVM_PARAMETERS = "-server -Xss1m";

    //in minutes
    public int COMPILE_SERVER_SHUTDOWN_DELAY = 120;
    public boolean COMPILE_SERVER_SHUTDOWN_IDLE = true;

    public boolean USE_PROJECT_HOME_AS_WORKING_DIR = false;

    public JawaCompileServerSettings getState() {
        return this;
    }

    public void loadState(JawaCompileServerSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static JawaCompileServerSettings getInstance() {
        return ServiceManager.getService(JawaCompileServerSettings.class);
    }

}
