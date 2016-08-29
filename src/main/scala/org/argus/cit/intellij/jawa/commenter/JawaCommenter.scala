/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.commenter

import com.intellij.lang.Commenter

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaCommenter extends Commenter {
  override def getCommentedBlockCommentPrefix: String = "/*"
  override def getCommentedBlockCommentSuffix: String = "*/"

  override def getBlockCommentPrefix: String = "/*"
  override def getBlockCommentSuffix: String = "*/"

  override def getLineCommentPrefix: String = "//"
}
