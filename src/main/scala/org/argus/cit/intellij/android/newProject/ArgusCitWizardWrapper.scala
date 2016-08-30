/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.android.newProject

import java.awt.Dimension
import javax.swing.{Icon, JComponent}

import com.android.tools.idea.wizard.dynamic.{DynamicWizard, DynamicWizardHost, DynamicWizardPath}
import com.android.tools.idea.wizard.dynamic.DynamicWizardHost.CloseAction
import com.intellij.ide.util.newProjectWizard.WizardDelegate
import com.intellij.ide.util.projectWizard.{ModuleWizardStep, WizardContext}
import com.intellij.ide.wizard.AbstractWizard
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.{DialogEarthquakeShaker, DialogWrapper}
import org.argus.cit.intellij.jawa.JawaBundle
import org.argus.cit.intellij.jawa.icons.Icons
import org.jetbrains.android.newProject.AndroidWizardWrapper

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ArgusCitWizardWrapper() extends AndroidWizardWrapper() {
  private var myWizard: NewProjectWizard = _

  override def getPresentableName: String = "Argus-CIT"

  override def getNodeIcon: Icon = Icons.ARGUS

  override def getDescription: String = JawaBundle.message("argus.module.type.description")

  override def getCustomOptionsStep(context: WizardContext, parentDisposable: Disposable): ModuleWizardStep = {
    if(myWizard == null) {
      val host = new WizardHostDelegate(context.getWizard)
      myWizard = new ProjectWizard(context.getProject, host)
      myWizard.init()
    }
    new ModuleWizardStep {
      override def getComponent: JComponent = myWizard.getContentPane.asInstanceOf[JComponent]
      override def updateDataModel(): Unit = {}
    }
  }

  override def doNextAction(): Unit = myWizard.doNextAction()

  override def doPreviousAction(): Unit = myWizard.doPreviousAction()

  override def doFinishAction(): Unit = myWizard.doFinishAction()

  override def canProceed: Boolean = myWizard.asInstanceOf[WizardDelegate].canProceed

  private class WizardHostDelegate(wizard: AbstractWizard[_]) extends DynamicWizardHost {
    override def init(dynamicWizard: DynamicWizard): Unit = {}

    override def setTitle(s: String): Unit = {}

    override def runSensitiveOperation(progressIndicator: ProgressIndicator, b: Boolean, runnable: Runnable): Unit = runnable.run()

    override def showAndGet(): Boolean = false

    override def getDisposable: Disposable = wizard.getDisposable

    override def shakeWindow(): Unit = if(!ApplicationManager.getApplication.isUnitTestMode) DialogEarthquakeShaker.shake(wizard.getPeer.getWindow)

    override def close(closeAction: CloseAction): Unit = wizard.close(DialogWrapper.CLOSE_EXIT_CODE)

    override def setIcon(icon: Icon): Unit = {}

    override def updateButtons(canGoPrev: Boolean, canGoNext: Boolean, canCancel: Boolean, canFinish: Boolean): Unit = wizard.updateButtons(canFinish, canGoNext || canFinish, !canGoPrev)

    override def show(): Unit = {}

    override def setPreferredWindowSize(dimension: Dimension): Unit = {}
  }

  private class ProjectWizard(project: Project, host: DynamicWizardHost) extends NewProjectWizard(project, null, host) with WizardDelegate {
    override def init(): Unit = {
      super.init()
      getCurrentPath match {
        case path: DynamicWizardPath => path.invokeUpdate(null)
        case _ =>
      }
    }

    override def checkSdk(): Unit = {}

    override def canProceed: Boolean = canGoNext
  }
}
