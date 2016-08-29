/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.actions

import java.util.Properties

import com.intellij.ide.actions.{CreateFileFromTemplateDialog, CreateTemplateInPackageAction}
import com.intellij.ide.fileTemplates.{FileTemplate, FileTemplateManager, JavaTemplateUtil}
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx
import com.intellij.openapi.project.{DumbAware, Project}
import com.intellij.openapi.ui.InputValidatorEx
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi._
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.util.PlatformIcons
import org.argus.cit.intellij.jawa.JawaBundle
import org.argus.cit.intellij.jawa.lang.JawaFileType
import org.argus.cit.intellij.jawa.lang.psi.api.JawaFile
import org.argus.cit.intellij.jawa.lang.psi.api.toplevel.JawaTypeDefinition
import org.argus.cit.intellij.jawa.lang.refactoring.util.JawaNamesUtil
import org.jetbrains.annotations.NonNls
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class NewJawaClassAction extends CreateTemplateInPackageAction[JawaTypeDefinition](
  JawaBundle.message("newclass.menu.action.text"), JawaBundle.message("newclass.menu.action.description"), PlatformIcons.CLASS_ICON,
  JavaModuleSourceRootTypes.SOURCES) with DumbAware {

  protected def buildDialog(project: Project, directory: PsiDirectory,
                            builder: CreateFileFromTemplateDialog.Builder) {
    builder.addKind("Class", PlatformIcons.CLASS_ICON, JawaFileTemplateUtil.JAWA_CLASS)
    builder.addKind("Interface", PlatformIcons.INTERFACE_ICON, JawaFileTemplateUtil.JAWA_INTERFACE)

    for (template <- FileTemplateManager.getInstance(project).getAllTemplates) {
      if (isJawaTemplate(template) && checkPackageExists(directory)) {
        builder.addKind(template.getName, JawaFileType.INSTANCE.getIcon, template.getName)
      }
    }

    builder.setTitle("Create New Jawa Class")
    builder.setValidator(new InputValidatorEx {
      def getErrorText(inputString: String): String = {
        if (inputString.length > 0 && !PsiNameHelper.getInstance(project).isQualifiedName(inputString)) {
          return "This is not a valid Jawa qualified name"
        }
        null
      }

      def checkInput(inputString: String): Boolean = {
        true
      }

      def canClose(inputString: String): Boolean = {
        !StringUtil.isEmptyOrSpaces(inputString) && getErrorText(inputString) == null
      }
    })
  }

  private def isJawaTemplate(template: FileTemplate): Boolean = {
    val fileType: FileType = FileTypeManagerEx.getInstanceEx.getFileTypeByExtension(template.getExtension)
    fileType == JawaFileType.INSTANCE
  }

  def getActionName(directory: PsiDirectory, newName: String, templateName: String): String = {
    JawaBundle.message("newclass.menu.action.text")
  }

  def getNavigationElement(createdElement: JawaTypeDefinition): PsiElement = createdElement.getLBrace

  def doCreate(directory: PsiDirectory, newName: String, templateName: String): JawaTypeDefinition = {
    createClassFromTemplate(directory, newName, templateName) match {
      case jawaFile: JawaFile =>
        jawaFile.typeDefinitions.headOption.orNull
      case _ => null
    }
  }

  private def createClassFromTemplate(directory: PsiDirectory, className: String, templateName: String,
                                      parameters: String*): PsiFile = {
    NewJawaClassAction.createFromTemplate(directory, className, className + JAWA_EXTENSION, templateName, parameters: _*)
  }

  private val JAWA_EXTENSION = ".pilar"

  def checkPackageExists(directory: PsiDirectory) = {
    JavaDirectoryService.getInstance.getPackage(directory) != null
  }
}

object NewJawaClassAction {
  @NonNls private[actions] val NAME_TEMPLATE_PROPERTY: String = "NAME"
  @NonNls private[actions] val LOW_CASE_NAME_TEMPLATE_PROPERTY: String = "lowCaseName"

  def createFromTemplate(directory: PsiDirectory, name: String, fileName: String, templateName: String,
                         parameters: String*): PsiFile = {
    val project = directory.getProject
    val template: FileTemplate = FileTemplateManager.getInstance(project).getInternalTemplate(templateName)
    val properties: Properties = new Properties(FileTemplateManager.getInstance(project).getDefaultProperties())

    properties.setProperty(FileTemplate.ATTRIBUTE_PACKAGE_NAME,
      JawaNamesUtil.escapeKeywordsFqn(JavaTemplateUtil.getPackageName(directory)))

    properties.setProperty(NAME_TEMPLATE_PROPERTY, name)
    properties.setProperty(LOW_CASE_NAME_TEMPLATE_PROPERTY, name.substring(0, 1).toLowerCase + name.substring(1))

    var i: Int = 0
    while (i < parameters.length) {
      {
        properties.setProperty(parameters(i), parameters(i + 1))
      }
      i += 2
    }
    var text: String = null
    try {
      text = template.getText(properties)
    }
    catch {
      case e: Exception =>
        throw new RuntimeException("Unable to load template for " + FileTemplateManager.getDefaultInstance.internalTemplateToSubject(templateName), e)
    }
    val factory: PsiFileFactory = PsiFileFactory.getInstance(project)
    val file: PsiFile = factory.createFileFromText(fileName, JawaFileType.INSTANCE, text)
    CodeStyleManager.getInstance(project).reformat(file)
    directory.add(file).asInstanceOf[PsiFile]
  }
}