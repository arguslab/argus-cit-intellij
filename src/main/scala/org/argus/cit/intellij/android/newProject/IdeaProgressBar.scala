/*
 * Copyright (c) 2017. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.android.newProject

import com.google.common.base.Preconditions
import com.intellij.openapi.progress.ProgressIndicator
import hu.ssh.progressbar.{AbstractProgressBar, Progress, ProgressBar}
import org.argus.jawa.core.util._

/**
  * Created by fgwei on 5/7/17.
  */
class IdeaProgressBar(progressIndicator: ProgressIndicator, totalSteps: Int) extends AbstractProgressBar(totalSteps) {

  var phase: Int = 0
  val textMap: MMap[Int, String] = mmapEmpty

  def this(progressIndicator: ProgressIndicator) = this(progressIndicator, 30)

  override def start(): Unit = {
    super.start()
    val text = textMap.getOrElse(phase, "Resolving...")
    progressIndicator.setText(text)
  }

  override def updateProgressBar(progress: Progress): Unit = {
    progressIndicator.setFraction(progress.getPercentage)
  }

  override def finishProgressBar(): Unit = {
    progressIndicator.setFraction(1)
  }

  override def withTotalSteps(totalSteps: Int): ProgressBar = {
    Preconditions.checkArgument(totalSteps != 0)
    val bar = new IdeaProgressBar(progressIndicator, totalSteps)
    phase += 1
    bar.phase = phase
    bar.textMap ++= textMap
    bar
  }
}
