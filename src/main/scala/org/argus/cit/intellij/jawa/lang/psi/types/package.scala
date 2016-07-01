/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import org.argus.jawa.core.JawaType
import org.argus.cit.intellij.jawa.project.ProjectExt

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
package object types {
  implicit class JawaTypeExt(val scType: JawaType) extends AnyVal {
    def toPsiType(project: Project,
                  scope: GlobalSearchScope,
                  noPrimitives: Boolean = false,
                  skolemToWildcard: Boolean = false) = {
      project.typeSystem.bridge.toPsiType(scType, project, scope)
    }
  }
}
