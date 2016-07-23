/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa

import org.argus.jc.incremental.jawa.model.{GlobalSettings, GlobalSettingsImpl, ProjectSettings, ProjectSettingsImpl}
import org.jetbrains.jps.model.{JpsGlobal, JpsProject}
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase
import org.jetbrains.jps.model.java.JpsJavaExtensionService
import org.jetbrains.jps.model.module.JpsModule

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object SettingsManager {
  final val GLOBAL_SETTINGS_ROLE: JpsElementChildRoleBase[GlobalSettings] = JpsElementChildRoleBase.create("jawa global settings")
  final val PROJECT_SETTINGS_ROLE: JpsElementChildRoleBase[ProjectSettings] = JpsElementChildRoleBase.create("jawa project settings")

  def getGlobalSettings(global: JpsGlobal): GlobalSettings = Option(global.getContainer.getChild(GLOBAL_SETTINGS_ROLE)).getOrElse(GlobalSettingsImpl.DEFAULT)
  def setGlobalSettings(global: JpsGlobal, settings: GlobalSettings) = global.getContainer.setChild(GLOBAL_SETTINGS_ROLE, settings)
  def getProjectSettings(project: JpsProject): ProjectSettings = Option(project.getContainer.getChild(PROJECT_SETTINGS_ROLE)).getOrElse(ProjectSettingsImpl.DEFAULT)
  def setProjectSettings(project: JpsProject, settings: ProjectSettings) = project.getContainer.setChild(PROJECT_SETTINGS_ROLE, settings)
  def libraryDependenciesIn(module: JpsModule) = JpsJavaExtensionService.dependencies(module).recursivelyExportedOnly.getLibraries
}
