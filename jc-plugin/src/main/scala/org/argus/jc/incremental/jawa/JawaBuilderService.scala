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

import java.util

import org.jetbrains.annotations.NotNull
import org.jetbrains.jps.incremental.{BuilderCategory, BuilderService, ModuleLevelBuilder}
import org.jetbrains.jps.incremental.resources.{ResourcesBuilder, StandardResourceBuilderEnabler}
import org.jetbrains.jps.model.module.JpsModule

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaBuilderService extends BuilderService {
  ResourcesBuilder.registerEnabler(new StandardResourceBuilderEnabler {
    def isResourceProcessingEnabled(module: JpsModule): Boolean = true
  })

  @NotNull
  override def createModuleLevelBuilders: util.List[_ <: ModuleLevelBuilder] = {
    util.Arrays.asList[ModuleLevelBuilder](
      new IdeaIncrementalBuilder(BuilderCategory.SOURCE_PROCESSOR),
      new IdeaIncrementalBuilder(BuilderCategory.OVERWRITING_TRANSLATOR)
    )
  }
}
