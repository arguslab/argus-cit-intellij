/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.elements

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{IndexSink, StubElement, StubInputStream, StubOutputStream}
import com.intellij.util.ArrayUtil
import org.argus.cit.intellij.jawa.lang.psi.JawaAccessFlagAnnotation
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaAccessFlagAnnotationImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaAccessFlagStub
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaAccessFlagStubImpl

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaAccessFlagElementType(debugName: String) extends JawaStubElementType[JawaAccessFlagStub, JawaAccessFlagAnnotation](debugName) {
  def createPsi(stub: JawaAccessFlagStub): JawaAccessFlagAnnotation = {
    new JawaAccessFlagAnnotationImpl(stub, this)
  }

  def createStubImpl[ParentPsi <: PsiElement](psi: JawaAccessFlagAnnotation, parentStub: StubElement[ParentPsi]): JawaAccessFlagStub = {
    val modifiers: Array[String] = psi.getModifiersStrings
    new JawaAccessFlagStubImpl(parentStub, this, if (modifiers.isEmpty) ArrayUtil.EMPTY_STRING_ARRAY else modifiers, psi.hasExplicitModifiers)
  }

  def serialize(stub: JawaAccessFlagStub, dataStream: StubOutputStream) {
    dataStream.writeBoolean(stub.hasExplicitModifiers)
    dataStream.writeInt(stub.getModifiers.length)
    for (modifier <- stub.getModifiers) dataStream.writeName(modifier)
  }

  def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaAccessFlagStub = {
    val explicitModifiers = dataStream.readBoolean()
    val num = dataStream.readInt
    val modifiers =
      if (num == 0) ArrayUtil.EMPTY_STRING_ARRAY
      else {
        val mods = new Array[String](num)
        for (i <- 0 until num) mods(i) = dataStream.readName.toString
        mods
      }
    new JawaAccessFlagStubImpl(parentStub.asInstanceOf[StubElement[PsiElement]], this, modifiers, explicitModifiers)
  }

  def indexStub(stub: JawaAccessFlagStub, sink: IndexSink) {}
}
