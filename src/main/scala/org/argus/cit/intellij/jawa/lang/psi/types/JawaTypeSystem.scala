/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.types

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import org.argus.jawa.core.JawaType

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaTypeSystem extends api.TypeSystem {
  override val name = "Jawa"
  override lazy val bridge = JawaTypePsiTypeBridge
  protected override lazy val presentation = JawaTypePresentation

  def toPsiType(`type`: JawaType, project: Project,
                scope: GlobalSearchScope) = `type`.toPsiType(project, scope)
}
