/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.android.infoViewer

import java.awt.BorderLayout

import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.DataProvider
import com.intellij.openapi.editor.impl.EditorFactoryImpl
import com.intellij.openapi.editor.{Editor, EditorFactory}
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.content.Content
import com.intellij.util.DocumentUtil

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
abstract class ApkInfoPanel(project: Project, content: Content) extends SimpleToolWindowPanel(false, true) with DataProvider with Disposable {
  private val factory = EditorFactory.getInstance()
  private val doc = factory.asInstanceOf[EditorFactoryImpl].createDocument("", true, false)
  doc.setReadOnly(true)
  private val myEditor: Editor = factory.createEditor(doc, project)
  private val settings = myEditor.getSettings
  settings.setLineMarkerAreaShown(false)
  settings.setIndentGuidesShown(false)
  settings.setLineNumbersShown(true)
  settings.setFoldingOutlineShown(false)
  myEditor.setBorder(null)
  add(myEditor.getComponent, BorderLayout.CENTER)

  def setText(info: String) = {
    DocumentUtil.writeInRunUndoTransparentAction(new Runnable {
      override def run(): Unit = {
        val fragmentDoc = myEditor.getDocument
        fragmentDoc.setReadOnly(false)
        fragmentDoc.replaceString(0, fragmentDoc.getTextLength, info)
        fragmentDoc.setReadOnly(true)
      }
    })
  }

  def getText: String = myEditor.getDocument.getText()

  override def dispose(): Unit = EditorFactory.getInstance().releaseEditor(myEditor)
}
