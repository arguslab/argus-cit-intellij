/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa.model

import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.jps.model.ex.JpsElementBase

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class GlobalSettingsImpl(state: GlobalSettingsImpl.State) extends JpsElementBase[GlobalSettingsImpl] with GlobalSettings {
  override def createCopy(): GlobalSettingsImpl = new GlobalSettingsImpl(XmlSerializerUtil.createCopy(state))

  override def applyChanges(self: GlobalSettingsImpl): Unit = {}

  override def isCompileServerEnabled: Boolean = state.COMPILE_SERVER_ENABLED

  override def getCompileServerPort: Int = state.COMPILE_SERVER_PORT
}

object GlobalSettingsImpl {
  final val DEFAULT = new GlobalSettingsImpl(new State)
  class State {
    var COMPILE_SERVER_ENABLED = true
    var COMPILE_SERVER_PORT = 5210
  }
}

