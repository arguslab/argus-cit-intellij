/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.util

import com.intellij.psi.stubs.{StubInputStream, StubOutputStream}
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaFileStub
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaFileStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object JawaStubsUtil {
  def serializeFileStubElement(stub: JawaFileStub, dataStream: StubOutputStream) {
    dataStream.writeBoolean(stub.isCompiled)
    dataStream.writeName(stub.packageName)
    dataStream.writeName(stub.getFileName)
  }

  def deserializeFileStubElement(dataStream: StubInputStream, parentStub: Object) = {
    val compiled = dataStream.readBoolean
    val packName = dataStream.readName
    val fileName = dataStream.readName
    new JawaFileStubImpl(null, packName, fileName, compiled)
  }

//  private val LOG = Logger.getInstance("#org.argus.cit.intellij.jawa.lang.psi.stubs.util.JawaStubsUtil")
}
