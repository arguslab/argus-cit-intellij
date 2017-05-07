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

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.java.stubs.PsiClassStub
import com.intellij.psi.impl.java.stubs.index.JavaStubIndexKeys
import com.intellij.psi.stubs.{IndexSink, StubElement, StubInputStream, StubOutputStream}
import com.intellij.util.io.StringRef
import org.argus.cit.intellij.jawa.lang.psi.JawaExtendsAndImplementsClause
import org.argus.cit.intellij.jawa.lang.psi.impl.JawaExtendsAndImplementsClauseImpl
import org.argus.cit.intellij.jawa.lang.psi.stubs.{JawaExtendsAndImplementsClauseStub, JawaStubElementTypes}
import org.argus.cit.intellij.jawa.lang.psi.stubs.impl.JawaExtendsAndImplementsClausesStubImpl
import org.argus.jawa.core.util._

import collection.JavaConverters._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaExtendsClauseElementType(debugName: String) extends JawaStubElementType[JawaExtendsAndImplementsClauseStub, JawaExtendsAndImplementsClause](debugName) {
  def serialize(stub: JawaExtendsAndImplementsClauseStub, dataStream: StubOutputStream) {
    val hasExtend = stub.getExtendType != null
    dataStream.writeBoolean(hasExtend)
    if(hasExtend) dataStream.writeName(stub.getExtendType.jawaName)
    val interfaceTypes = stub.getInterfaceTypes.map(_.jawaName)
    dataStream.writeInt(interfaceTypes.length)
    for (name <- interfaceTypes) dataStream.writeName(name)
  }

  def deserializeImpl(dataStream: StubInputStream, parentStub: Any): JawaExtendsAndImplementsClauseStub = {
    val hasExtend = dataStream.readBoolean
    val extType: StringRef = if(hasExtend) dataStream.readName else null
    val length: Int = dataStream.readInt
    val interfaceTypes = new Array[StringRef](length)
    for (i <- 0 until length) interfaceTypes(i) = dataStream.readName
    new JawaExtendsAndImplementsClausesStubImpl(parentStub.asInstanceOf[StubElement[PsiElement]], this, extType, interfaceTypes)
  }

  def createPsi(stub: JawaExtendsAndImplementsClauseStub): JawaExtendsAndImplementsClause = {
    new JawaExtendsAndImplementsClauseImpl(stub, JawaStubElementTypes.EXTENDS)
  }

  def createStubImpl[ParentPsi <: PsiElement](psi: JawaExtendsAndImplementsClause, parentStub: StubElement[ParentPsi]): JawaExtendsAndImplementsClauseStub = {
    var extTypeText: String = null
    val impTypeTexts: MSet[String] = msetEmpty
    psi.getExtendAndImplementList.asScala foreach { eil =>
      if(eil.isImplements) impTypeTexts += eil.getTypeSymbol.getJawaType.jawaName
      else extTypeText = eil.getTypeSymbol.getJawaType.jawaName
    }
    new JawaExtendsAndImplementsClausesStubImpl(parentStub, this, extTypeText, impTypeTexts.toArray)
  }

  override def indexStub(stub: JawaExtendsAndImplementsClauseStub, sink: IndexSink): Unit = {
    val names = stub.getInterfaceTypes.map(_.simpleName).toBuffer
    if(stub.getExtendType != null)
      names += stub.getExtendType.simpleName
    names.foreach { name =>
      sink.occurrence(JavaStubIndexKeys.SUPER_CLASSES, name)
    }

    stub.getParentStub match {
      case psiClassStub: PsiClassStub[_] =>
        if (psiClassStub.isEnum) {
          sink.occurrence(JavaStubIndexKeys.SUPER_CLASSES, "Enum")
        }
        if (psiClassStub.isAnnotationType) {
          sink.occurrence(JavaStubIndexKeys.SUPER_CLASSES, "Annotation")
        }
    }
  }
}
