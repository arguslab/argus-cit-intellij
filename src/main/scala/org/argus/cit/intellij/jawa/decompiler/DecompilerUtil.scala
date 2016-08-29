/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.jawa.decompiler

import java.io.IOException

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.newvfs.FileAttribute
import com.intellij.openapi.vfs.{VirtualFile, VirtualFileWithId}
import com.intellij.reference.SoftReference
import org.argus.cit.intellij.jawa.JawaLoader

/**
  * Created by fgwei on 6/22/16.
  */
object DecompilerUtil {
  protected val LOG: Logger = Logger.getInstance("#org.argus.cit.intellij.jawa.decompiler.DecompilerUtil")
  val DECOMPILER_VERSION = 1
  private val JAWA_DECOMPILER_FILE_ATTRIBUTE = new FileAttribute("_is_jawa_compiled_new_key_", DECOMPILER_VERSION, true)
  private val JAWA_DECOMPILER_KEY = new Key[SoftReference[DecompilationResult]]("Is Jawa File Key")
  class DecompilationResult(val isJawa: Boolean, val sourceName: String, val timeStamp: Long) {
    def sourceText: String = ""
  }
  object DecompilationResult {
    def empty: DecompilationResult = new DecompilationResult(isJawa = false, sourceName = "", timeStamp = 0L)
  }

  // Underlying VFS implementation may not support attributes (e.g. Upsource's file system).
  private def attributesSupported = !JawaLoader.isUnderUpsource

  def decompile(file: VirtualFile, bytes: => Array[Byte]): DecompilationResult = {
    if (!file.isInstanceOf[VirtualFileWithId]) return DecompilationResult.empty
    val timeStamp = file.getTimeStamp
    var data = file.getUserData(JAWA_DECOMPILER_KEY)
    var res: DecompilationResult = if (data == null) null else data.get()
    if (res == null || res.timeStamp != timeStamp) {
      val readAttribute = if (attributesSupported) JAWA_DECOMPILER_FILE_ATTRIBUTE.readAttribute(file) else null
      def updateAttributeAndData() {
        val decompilationResult = decompileInner(file, bytes)
        if (attributesSupported) {
          val writeAttribute = JAWA_DECOMPILER_FILE_ATTRIBUTE.writeAttribute(file)
          try {
            writeAttribute.writeBoolean(decompilationResult.isJawa)
            writeAttribute.writeUTF(decompilationResult.sourceName)
            writeAttribute.writeLong(decompilationResult.timeStamp)
            writeAttribute.close()
          } catch {
            case e: IOException =>
          }
        }
        res = decompilationResult
      }
      if (readAttribute != null) {
        try {
          val isScala = readAttribute.readBoolean()
          val sourceName = readAttribute.readUTF()
          val attributeTimeStamp = readAttribute.readLong()
          if (attributeTimeStamp != timeStamp) updateAttributeAndData()
          else res = new DecompilationResult(isScala, sourceName, attributeTimeStamp) {
            override lazy val sourceText: String = {
              decompileInner(file, bytes).sourceText
            }
          }
        }
        catch {
          case e: IOException => updateAttributeAndData()
        }
      } else updateAttributeAndData()
      data = new SoftReference[DecompilationResult](res)
      file.putUserData(JAWA_DECOMPILER_KEY, data)
    }
    res
  }

  private def decompileInner(file: VirtualFile, bytes: Array[Byte]): DecompilationResult = {
//    try {
//      Decompiler.decompile(file.getName, bytes) match {
//        case Some((sourceFileName, decompiledSourceText)) =>
//          new DecompilationResult(isScala = true, sourceFileName, file.getTimeStamp) {
//            override def sourceText: String = decompiledSourceText
//          }
//        case _ => new DecompilationResult(isScala = false, "", file.getTimeStamp)
//      }
//    } catch {
//      case m: MatchError =>
//        LOG.warn(s"Error during decompiling $file: ${m.getMessage()}. Stacktrace is suppressed.")
//        new DecompilationResult(isScala = false, "", file.getTimeStamp)
//      case t: Throwable =>
//        LOG.warn(s"Error during decompiling $file: ${t.getMessage}. Stacktrace is suppressed.")
        new DecompilationResult(isJawa = false, "", file.getTimeStamp)
//    }
  }
}
