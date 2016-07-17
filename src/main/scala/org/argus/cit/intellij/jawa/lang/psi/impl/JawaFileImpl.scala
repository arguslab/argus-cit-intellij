/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.lang.psi.impl

import java.util

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileTypes.{FileType, LanguageFileType}
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.roots.impl.LibraryScopeCache
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.impl.ResolveScopeManager
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiUtilCore
import com.intellij.psi.{FileResolveScopeProvider, FileViewProvider, PsiClass}
import com.intellij.util.indexing.FileBasedIndex
import org.argus.cit.intellij.jawa.decompiler.{CompiledFileAdjuster, DecompilerUtil}
import org.argus.cit.intellij.jawa.lang.psi.JawaPsiElement
import org.argus.cit.intellij.jawa.lang.psi.api.JawaFile
import org.argus.cit.intellij.jawa.lang.JawaFileType
import org.argus.cit.intellij.jawa.lang.psi.stubs.JawaFileStub
import org.jetbrains.annotations.Nullable

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaFileImpl(viewProvider: FileViewProvider, fileType: LanguageFileType = JawaFileType.INSTANCE)
  extends PsiFileBase(viewProvider, fileType.getLanguage) with JawaFile with CompiledFileAdjuster with FileResolveScopeProvider {
  override def getFileType: FileType = fileType
  override def toString: String = "Jawa File"
  override def getIcon(flags: Int) = super.getIcon(flags)

  override def getPackageName: String = {
    val res = packageName
    if (res == null) ""
    else res
  }

  override def sourceName: String = if(isCompiled) {
    val stub = getStub
    if(stub != null) {
      return stub.getFileName
    }
    val virtualFile = getVirtualFile
    DecompilerUtil.decompile(virtualFile, virtualFile.contentsToByteArray).sourceName
  } else ""

  override def getStub: JawaFileStub = super[PsiFileBase].getStub match {
    case null => null
    case s: JawaFileStub => s
    case _ =>
      val faultyContainer: VirtualFile = PsiUtilCore.getVirtualFile(this)
      JawaFileImpl.LOG.error("Jawa File has wrong stub file: " + faultyContainer)
      if (faultyContainer != null && faultyContainer.isValid) {
        FileBasedIndex.getInstance.requestReindex(faultyContainer)
      }
      null
  }

  override def isCompiled: Boolean = compiled

  @Nullable
  override def packageName: String = {
    val defs = this.typeDefinitions
    if(defs.isEmpty) ""
    else defs.head.getTypeDefSymbol.getJawaType.getPackageName
  }

  override def getFileResolveScope: GlobalSearchScope = {
    val vFile = getOriginalFile.getVirtualFile
    if (vFile == null) GlobalSearchScope.allScope(getProject)
    else if (isCompiled) compiledFileResolveScope
    else ResolveScopeManager.getInstance(getProject).getDefaultResolveScope(vFile)
  }

//  @CachedInsidePsiElement(this, ProjectRootManager.getInstance(getProject))
  private def compiledFileResolveScope: GlobalSearchScope = {
    val vFile = getOriginalFile.getVirtualFile
    val orderEntries = ProjectRootManager.getInstance(getProject).getFileIndex.getOrderEntriesForFile(vFile)
    LibraryScopeCache.getInstance(getProject).getLibraryScope(orderEntries) //this cache is very inefficient when orderEntries.size is large
  }

  override def ignoreReferencedElementAccessibility(): Boolean = true

  override def getClassNames: util.Set[String] = {
    val res = new util.HashSet[String]
    typeDefinitions.foreach{ cid =>
      res.add(cid.getTypeDefSymbol.getJawaType.simpleName)
    }
    res
  }

  override def setPackageName(s: String): Unit = {}

  override def getClasses: Array[PsiClass] = {
    if (JawaFileImpl.isDuringMoveRefactoring) {
      return typeDefinitions.toArray
    }
    typeDefinitions.toArray
  }

  override protected def findChildByClassJawa[T >: Null <: JawaPsiElement](clazz: Class[T]): T = findChildByClass[T](clazz)

  override protected def findChildrenByClassJawa[T >: Null <: JawaPsiElement](clazz: Class[T]): Array[T] = findChildrenByClass[T](clazz)
}

object JawaFileImpl {
  private val LOG: Logger = Logger.getInstance("#org.argus.cit.intellij.jawa.lang.psi.impl.JawaFileImpl")

  private[this] var duringMoveRefactoring: Boolean = false

  private def isDuringMoveRefactoring: Boolean = duringMoveRefactoring

  def performMoveRefactoring(body: => Unit) {
    synchronized {
      try {
        duringMoveRefactoring = true
        body
      } finally {
        duringMoveRefactoring = false
      }
    }
  }
}