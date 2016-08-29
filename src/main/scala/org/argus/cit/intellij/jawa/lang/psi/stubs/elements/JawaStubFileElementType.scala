/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.elements

import com.intellij.lang.Language
import com.intellij.psi.StubBuilder
import com.intellij.psi.stubs.{IndexSink, StubInputStream, StubOutputStream}
import org.argus.cit.intellij.jawa.decompiler.DecompilerUtil
import org.argus.cit.intellij.jawa.lang.psi.api.JawaFile
import org.argus.cit.intellij.jawa.lang.psi.stubs.{JawaFileStub, JawaFileStubBuilder}
import org.argus.cit.intellij.jawa.lang.psi.stubs.element.wrappers.IStubFileElementWrapper
import org.argus.cit.intellij.jawa.lang.psi.stubs.util.JawaStubsUtil

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaStubFileElementType(lang: Language) extends IStubFileElementWrapper[JawaFile, JawaFileStub]("jawa.FILE", lang) {

  override def getStubVersion: Int = StubVersion.STUB_VERSION

  override def getBuilder: StubBuilder = new JawaFileStubBuilder()

  override def getExternalId = "jawa.FILE"

  override def deserializeImpl(dataStream: StubInputStream, parentStub: Object): JawaFileStub = {
    JawaStubsUtil.deserializeFileStubElement(dataStream, parentStub)
  }

  override def serialize(stub: JawaFileStub, dataStream: StubOutputStream): Unit = {
    JawaStubsUtil.serializeFileStubElement(stub, dataStream)
  }

  def indexStub(stub: JawaFileStub, sink: IndexSink){
  }

}


object StubVersion {
  val STUB_VERSION: Int = DecompilerUtil.DECOMPILER_VERSION
}