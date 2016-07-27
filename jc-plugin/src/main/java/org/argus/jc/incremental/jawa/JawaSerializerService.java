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

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.xmlb.XmlSerializer;
import org.argus.jc.incremental.jawa.model.CompilerSettingsImpl;
import org.argus.jc.incremental.jawa.model.GlobalSettingsImpl;
import org.argus.jc.incremental.jawa.model.ProjectSettings;
import org.argus.jc.incremental.jawa.model.ProjectSettingsImpl;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsGlobal;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.serialization.JpsGlobalExtensionSerializer;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;

import java.util.*;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public class JawaSerializerService extends JpsModelSerializerExtension {
  @NotNull
  @Override
  public List<? extends JpsGlobalExtensionSerializer> getGlobalExtensionSerializers() {
    return Collections.singletonList(new GlobalSettingsSerializer());
  }

  @NotNull
  @Override
  public List<? extends JpsProjectExtensionSerializer> getProjectExtensionSerializers() {
    return Collections.singletonList(new CompilerConfigurationSerializer());
  }

  private static class GlobalSettingsSerializer extends JpsGlobalExtensionSerializer {
    private GlobalSettingsSerializer() {
      super("scala.xml", "ScalaSettings");
    }

    @Override
    public void loadExtension(@NotNull JpsGlobal jpsGlobal, @NotNull Element componentTag) {
      GlobalSettingsImpl.State state = XmlSerializer.deserialize(componentTag, GlobalSettingsImpl.State.class);
      GlobalSettingsImpl settings = new GlobalSettingsImpl(state == null ? new GlobalSettingsImpl.State() : state);
      SettingsManager.setGlobalSettings(jpsGlobal, settings);
    }

    @Override
    public void saveExtension(@NotNull JpsGlobal jpsGlobal, @NotNull Element componentTag) {
      // do nothing
    }
  }

  private static class CompilerConfigurationSerializer extends JpsProjectExtensionSerializer {
    private CompilerConfigurationSerializer() {
      super("scala_compiler.xml", "ScalaCompilerConfiguration");
    }

    @Override
    public void loadExtension(@NotNull JpsProject jpsProject, @NotNull Element componentTag) {
      CompilerSettingsImpl defaultSetting = loadSettings(componentTag);

      Map<String, String> moduleToProfile = new HashMap<>();
      Map<String, CompilerSettingsImpl> profileToSettings = new HashMap<>();

      for (Element profileElement : componentTag.getChildren("profile")) {
        String profile = profileElement.getAttributeValue("name");
        CompilerSettingsImpl settings = loadSettings(profileElement);
        profileToSettings.put(profile, settings);

        List<String> modules = StringUtil.split(profileElement.getAttributeValue("modules"), ",");
        for (String module : modules) {
          moduleToProfile.put(module, profile);
        }
      }

      ProjectSettings configuration = new ProjectSettingsImpl(defaultSetting, profileToSettings, moduleToProfile);

      SettingsManager.setProjectSettings(jpsProject, configuration);
    }

    private static CompilerSettingsImpl loadSettings(Element componentTag) {
      CompilerSettingsImpl.State state = XmlSerializer.deserialize(componentTag, CompilerSettingsImpl.State.class);
      return new CompilerSettingsImpl(state == null ? new CompilerSettingsImpl.State() : state);
    }

    @Override
    public void saveExtension(@NotNull JpsProject jpsProject, @NotNull Element componentTag) {
      // do nothing
    }
  }
}
