/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.mixins

import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.IStubElementType
import org.argus.cit.intellij.jawa.lang.psi.{JawaAccessFlagAnnotation, JawaStubBasedElementImpl}
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaAccessFlagStub

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class JawaAccessFlagAnnotationImplMixin(stub: JawaAccessFlagStub, nodeType: IStubElementType, node: ASTNode)
  extends JawaStubBasedElementImpl[JawaAccessFlagAnnotation](stub, nodeType, node) with JawaAccessFlagAnnotation {

}
