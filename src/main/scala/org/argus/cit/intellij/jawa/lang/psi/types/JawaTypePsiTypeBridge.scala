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

/**
  * Created by fgwei on 6/30/16.
  */
object JawaTypePsiTypeBridge extends api.JawaTypePsiTypeBridge {
  override implicit lazy val typeSystem = JawaTypeSystem
}
