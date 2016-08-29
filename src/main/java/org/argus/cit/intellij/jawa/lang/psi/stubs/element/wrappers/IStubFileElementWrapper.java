/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.stubs.element.wrappers;

import com.intellij.lang.Language;
import com.intellij.psi.PsiFile;
import com.intellij.psi.stubs.PsiFileStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
public abstract class IStubFileElementWrapper<S extends PsiFile, T extends PsiFileStub<S>> extends IStubFileElementType<T> {

  public IStubFileElementWrapper(@NonNls String debugName, Language language) {
    super(debugName, language);
  }

  //Dirty delegate hack to avoid problems with inheritance in Scala which doesn't allow rawtyped parameters
  @NotNull
  @Override
  public T deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    return deserializeImpl(dataStream, parentStub);
  }

  protected abstract T deserializeImpl(StubInputStream dataStream, Object parentStub);

}
