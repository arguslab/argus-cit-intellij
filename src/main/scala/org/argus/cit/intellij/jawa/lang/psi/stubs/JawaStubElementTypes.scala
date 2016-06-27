/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs

import org.argus.cit.intellij.jawa.lang.psi.stubs.elements.{JawaAccessFlagElementType, JawaClassDefinitionElementType}

/**
  * Created by fgwei on 6/24/16.
  */
object JawaStubElementTypes {
  val CLASS = new JawaClassDefinitionElementType("CLASS")
  val ACCESS_FLAG = new JawaAccessFlagElementType("ACCESS_FLAG")
}
