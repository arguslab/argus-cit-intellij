/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa.model

import org.jetbrains.jps.ModuleChunk
import org.jetbrains.jps.model.ex.JpsElementBase

import collection.JavaConversions._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ProjectSettingsImpl(defaultSettings: CompilerSettingsImpl,
                          profileToSettings: java.util.Map[String, CompilerSettingsImpl],
                          moduleToProfile: java.util.Map[String, String]) extends JpsElementBase[ProjectSettingsImpl] with ProjectSettings {
  override def createCopy(): ProjectSettingsImpl = {
    val myDefaultSettings: CompilerSettingsImpl = defaultSettings.createCopy()
    val newProfileToSettings: java.util.Map[String, CompilerSettingsImpl] = profileToSettings.map{
      case (k, v) => k -> v.createCopy()
    }
    new ProjectSettingsImpl(myDefaultSettings, newProfileToSettings, moduleToProfile)
  }

  override def applyChanges(self: ProjectSettingsImpl): Unit = {}

  override def getCompilerSettings(chunk: ModuleChunk): CompilerSettings = {
    val module: String = chunk.representativeTarget.getModule.getName
    Option(moduleToProfile.get(module)) match {
      case Some(profile) => profileToSettings(profile)
      case None => defaultSettings
    }
  }
}

object ProjectSettingsImpl {
  final val DEFAULT = new ProjectSettingsImpl(CompilerSettingsImpl.DEFAULT, new java.util.HashMap[String, CompilerSettingsImpl](), new java.util.HashMap[String, String]())
}