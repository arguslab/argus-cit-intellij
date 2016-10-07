/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.debugger.breakpoints

import com.intellij.debugger.engine.{DebugProcessImpl, JavaBreakpointHandler, JavaBreakpointHandlerFactory}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaBreakpointHandlerFactory extends JavaBreakpointHandlerFactory {
  override def createHandler(process: DebugProcessImpl): JavaBreakpointHandler = new JawaBreakpointHandler(process)
}

class JawaBreakpointHandler(process: DebugProcessImpl) extends JavaBreakpointHandler(classOf[JawaLineBreakpointType], process)