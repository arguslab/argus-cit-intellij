/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.element.wrappers;

import com.intellij.psi.PsiFile;
import com.intellij.psi.stubs.PsiFileStubImpl;
import com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public class PsiFileStubWrapperImpl<T extends PsiFile> extends PsiFileStubImpl<T> {

  public PsiFileStubWrapperImpl(T file) {
    super(file);
  }

  @NotNull
  @Override
  public IStubFileElementType<?> getType() {
    return super.getType();
  }
}
