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
import org.jetbrains.jps.model.module.JpsModule
import org.jetbrains.jps.service.JpsServiceManager

import scala.collection.JavaConverters._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class SourceDependenciesProviderService {
  def getSourceDependenciesFor(chunk: ModuleChunk): Seq[JpsModule]
}

object SourceDependenciesProviderService {
  def getSourceDependenciesFor(chunk: ModuleChunk): Seq[JpsModule] = {
    val providers = JpsServiceManager.getInstance.getExtensions(classOf[SourceDependenciesProviderService]).asScala
    providers.flatMap(_.getSourceDependenciesFor(chunk)).toSeq
  }
}
