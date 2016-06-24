/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.mixins;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import org.argus.cit.intellij.jawa.lang.psi.JawaAccessFlagAnnotation;
import org.argus.cit.intellij.jawa.lang.psi.JawaStubBasedElementImpl;
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaAccessFlagStub;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class JawaAccessFlagAnnotationImplMixin
        extends JawaStubBasedElementImpl<JawaAccessFlagStub>
        implements JawaAccessFlagAnnotation {

    public JawaAccessFlagAnnotationImplMixin(@NotNull ASTNode node) {
        super(null, null, node);
    }

    public JawaAccessFlagAnnotationImplMixin(@NotNull JawaAccessFlagStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType, null);
    }

//        def this(node: ASTNode) = this(null, null, node)
//        def this(stub: JawaAccessFlagStub, nodeType: IStubElementType) = this(stub, nodeType, null)
//
//        def getAnnotations: Array[PsiAnnotation] = PsiAnnotation.EMPTY_ARRAY
//        def findAnnotation(name: String): PsiAnnotation = null
//        override def addAnnotation(s: String): PsiAnnotation = null
//        override def getApplicableAnnotations: Array[PsiAnnotation] = PsiAnnotation.EMPTY_ARRAY
//
//        override def checkSetModifierProperty(s: String, b: Boolean): Unit = {}
//
//        override def setModifierProperty(s: String, b: Boolean): Unit = {}
//
//        def hasModifierProperty(name: String): Boolean = {
//        val stub = getStub
//        if (stub != null) {
//        return stub.asInstanceOf[JawaAccessFlagStub].getModifiers.contains(name)
//        }
//        has(name)
//        }
//
//        override def hasExplicitModifier(s: String): Boolean = false
//
//        def has(name: String) : Boolean = {
//        getModifiersStrings.contains(name)
//        }
//
//        def accessModifier: Option[JawaAccessFlagAnnotation] = {
//        val stub = getStub
//        if (stub != null) {
//        val am = stub.findChildStubByType(JawaStubElementTypes.ACCESS_FLAG)
//        if (am != null) {
//        return Some(am.getPsi)
//        } else return None
//        }
//        findChild(classOf[JawaAccessFlagAnnotation])
//        }
//
//        def getModifiersStrings: Array[String] = {
//        val modifier = getStubOrPsiChild(JawaStubElementTypes.ACCESS_FLAG)
//        modifier.getModifiers
//        }
//
//        def hasExplicitModifiers: Boolean = !getModifiersStrings.isEmpty
        }