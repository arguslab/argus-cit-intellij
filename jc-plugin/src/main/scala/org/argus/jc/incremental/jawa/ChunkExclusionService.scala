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

import org.jetbrains.jps.ModuleChunk
import org.jetbrains.jps.service.JpsServiceManager

import collection.JavaConverters._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class ChunkExclusionService {
  def isExcluded(chunk: ModuleChunk): Boolean
}

object ChunkExclusionService {
  def isExcluded(chunk: ModuleChunk): Boolean = {
    val providers = JpsServiceManager.getInstance.getExtensions(classOf[ChunkExclusionService]).asScala
    providers.exists(_.isExcluded(chunk))
  }
}