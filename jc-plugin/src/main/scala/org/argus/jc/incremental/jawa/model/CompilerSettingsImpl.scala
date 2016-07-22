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

import scala.collection.mutable

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class CompilerSettingsImpl(state: CompilerSettingsImpl.State) extends JpsElementBase[CompilerSettingsImpl] with CompilerSettings {
  override def createCopy(): CompilerSettingsImpl = new CompilerSettingsImpl(XmlSerializerUtil.createCopy(state))

  override def applyChanges(self: CompilerSettingsImpl): Unit = {}

  override def getCompileOrder: _root_.org.argus.jc.incremental.jawa.model.CompileOrder.Value = state.compileOrder

  override def getCompilerOptions: Set[String] = {
    val options: mutable.Set[String] = new mutable.HashSet[String]()
    if(!state.warnings) options += "-nowarn"
    options.toSet
  }
}

object CompilerSettingsImpl {
  final val DEFAULT = new CompilerSettingsImpl(new State)
  class State {
    var compileOrder = CompileOrder.MIXED
    var warnings = true
  }
}