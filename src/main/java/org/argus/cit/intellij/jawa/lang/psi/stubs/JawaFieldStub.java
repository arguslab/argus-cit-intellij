/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs;

import com.intellij.psi.stubs.NamedStub;
import org.argus.cit.intellij.jawa.lang.psi.JawaFieldDeclaration;
import org.argus.jawa.core.JawaType;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public interface JawaFieldStub extends NamedStub<JawaFieldDeclaration> {
    String getFQN();
    JawaType getType();
    int getFlag();
}
