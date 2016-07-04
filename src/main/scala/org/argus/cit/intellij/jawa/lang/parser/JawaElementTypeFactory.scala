/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.parser

import com.intellij.psi.tree.IElementType
import org.argus.cit.intellij.jawa.lang.psi.JawaElementType
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaStubElementTypes

/**
  * Created by fgwei on 6/24/16.
  */
object JawaElementTypeFactory {
  def getElementType(name: String): IElementType = name match {
    case "CLASS_OR_INTERFACE_DECLARATION" => JawaStubElementTypes.CLASS
    case "METHOD_DECLARATION" => JawaStubElementTypes.METHOD
    case "ACCESS_FLAG_ANNOTATION" => JawaStubElementTypes.ACCESS_FLAG
    case "JW_TYPE" => JawaStubElementTypes.TYPE
    case "PARAM_CLAUSE" => JawaStubElementTypes.PARAM_CLAUSE
    case "PARAM" => JawaStubElementTypes.PARAM
    case _ => JawaElementType(name)
  }
}
