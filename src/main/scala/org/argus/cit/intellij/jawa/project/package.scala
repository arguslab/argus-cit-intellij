/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa

import com.intellij.openapi.project.Project
import org.argus.cit.intellij.jawa.lang.psi.types.JawaTypeSystem
import org.argus.cit.intellij.jawa.lang.psi.types.api.TypeSystem

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
package object project {
  implicit class ProjectExt(val project: Project) extends AnyVal {
    def typeSystem: TypeSystem = JawaTypeSystem
  }
}
