/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.lang.psi

import com.intellij.psi.tree.IElementType
import org.argus.cit.intellij.core.lang.JawaFileType

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
case class JawaElementType(debugName: String) extends IElementType(debugName, JawaFileType.JAWA_LANGUAGE) {
  override def toString: String = debugName
}
