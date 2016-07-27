/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa.local

import java.io.File

import com.intellij.openapi.util.io.FileUtil
import org.argus.jawa.core.io.SourceFile
import org.jetbrains.jps.incremental.{BinaryContent, CompiledClass}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class LazyCompiledClass(outputFile: File, sourceFile: SourceFile, className: String)
  extends CompiledClass(outputFile, sourceFile.file.file, className, new BinaryContent(Array.empty)){

  private var loadedContent: Option[BinaryContent] = None
  private var contentIsSet = false

  override def getContent = {
    if (contentIsSet) super.getContent else loadedContent.getOrElse {
      val content = new BinaryContent(FileUtil.loadFileBytes(outputFile))
      loadedContent = Some(content)
      content
    }
  }

  override def setContent(content: BinaryContent) {
    super.setContent(content)
    loadedContent = None
    contentIsSet = true
  }
}
