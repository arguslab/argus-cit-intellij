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

import java.io.File

import com.android.tools.idea.npw.ConfigureAndroidProjectPath
import com.android.tools.idea.sdk.VersionCheck
import com.android.tools.idea.wizard.WizardConstants
import com.android.tools.idea.wizard.dynamic.DynamicWizardPath
import com.android.tools.idea.wizard.dynamic.DynamicWizardStepWithHeaderAndDescription.WizardStepHeaderSettings
import com.intellij.openapi.Disposable
import com.intellij.openapi.diagnostic.Logger
import org.argus.amandroid.core.decompile.ApkDecompiler
import org.jetbrains.android.sdk.AndroidSdkUtils

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ConfigureArgusProjectPath(parentDisposable: Disposable) extends DynamicWizardPath {
  final val LOG = Logger.getInstance(classOf[ConfigureArgusProjectPath])
  override def init() = {
    ConfigureAndroidProjectPath.putSdkDependentParams(myState)
    addStep(new ConfigureArgusProjectStep(parentDisposable))
  }

  override def validate(): Boolean = {
    if(!AndroidSdkUtils.isAndroidSdkAvailable) {
      setErrorHtml("<html>Your Android SDK is missing, out of date, or is missing templates. " +
        "Please ensure you are using SDK version " + VersionCheck.MIN_TOOLS_REV + " or later.<br>" +
        "You can configure your SDK via <b>Configure | Project Defaults | Project Structure | SDKs</b></html>")
      false
    } else true
  }

  override def getPathName: String = "Configure Argus-Cit Project"

  override def canPerformFinishingActions: Boolean = performFinishingActions()

  override def performFinishingActions(): Boolean = {
    val path = myState.get(WizardConstants.APPLICATION_NAME_KEY)
    val output = myState.get(WizardConstants.PROJECT_LOCATION_KEY)
    try {
      ApkDecompiler.decompile(new File(path), new File(output), None, dexLog = false, debugMode = false, removeSupportGen = true, forceDelete = true, None, createFolder = false)
      true
    } catch {
      case e: Exception =>
        setErrorHtml("<html>Your APK cannot be decompiled. Error message: " + e.getMessage + "</html>")
        LOG.error(e)
        false
    }
  }
}

object ConfigureArgusProjectPath {
  protected def buildConfigurationHeader = WizardStepHeaderSettings.createTitleOnlyHeader("New Analysis")
}