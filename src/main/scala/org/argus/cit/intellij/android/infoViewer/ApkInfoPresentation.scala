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

import java.io.{File, FileInputStream}
import java.nio.file.{Files, Paths}
import java.nio.file.attribute.BasicFileAttributes
import java.security.MessageDigest
import java.text.SimpleDateFormat

import org.argus.amandroid.core.Apk
import org.argus.amandroid.core.parser.ComponentType
import org.argus.amandroid.core.util.AndroidUrlCollector
import org.argus.jawa.core.Global
import org.sireum.util.FileUtil

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
object ApkInfoPresentation {
  case class Presentation(apkInfo: String, applicationInfo: String, apisAndStrings: String)

  def prepare(apk: Apk, global: Global): Presentation = {
    val appName = apk.getAppName
    val app = FileUtil.toFile(apk.nameUri)
    var md = MessageDigest.getInstance("MD5")
    val md5checksum = getFileChecksum(md, app)
    md = MessageDigest.getInstance("SHA-1")
    val sha1checksum = getFileChecksum(md, app)
    md = MessageDigest.getInstance("SHA-256")
    val sha256checksum = getFileChecksum(md, app)
    val fileSize = getFileSize(app)
    val certs = apk.getCertificates
    val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val attr = Files.readAttributes(Paths.get(app.getPath), classOf[BasicFileAttributes])
    val highestDatetime = sdf.format(attr.lastModifiedTime().toMillis)
    val lowestDatetime = sdf.format(attr.creationTime().toMillis)
    val apkInfo: String =
      s"""App Name: $appName
         |
         |File Size:          $fileSize
         |Creation Time:      $lowestDatetime
         |Last Modified Time: $highestDatetime
         |
         |MD5:    $md5checksum
         |SHA1:   $sha1checksum
         |SHA256: $sha256checksum
         |
         |Certificates:
         |
         |${certs.mkString("\n\n")}
       """.stripMargin

    val uses_permissions = apk.getUsesPermissions
    val components = apk.getComponentInfos
    val activities = components.filter(_.typ == ComponentType.ACTIVITY)
    val services = components.filter(_.typ == ComponentType.SERVICE)
    val receivers = components.filter(_.typ == ComponentType.RECEIVER)
    val providers = components.filter(_.typ == ComponentType.PROVIDER)
    var applicationInfo: StringBuilder = new StringBuilder
    applicationInfo.append(s"""Uses Permissions:
                              |${uses_permissions.mkString("\n").trim}
                            """.stripMargin)
    if(activities.nonEmpty) {
      applicationInfo.append(
      s"""
         |Activities:
         |
         |${activities.map(a =>
          s"""${a.compType.jawaName}
             |  enabled:     ${a.enabled}
             |  exported:    ${a.exported}
             |  permissions: ${a.permission.mkString(" ")}
          """.stripMargin).mkString("\n")}
       """.stripMargin
      )
    }
    if(services.nonEmpty) {
      applicationInfo.append(
      s"""
         |Services:
         |
         |${services.map(a =>
          s"""${a.compType.jawaName}
             |  enabled:     ${a.enabled}
             |  exported:    ${a.exported}
             |  permissions: ${a.permission.mkString(" ")}
          """.stripMargin).mkString("\n")}
       """.stripMargin
      )
    }
    if(receivers.nonEmpty) {
      applicationInfo.append(
      s"""
         |Broadcast Receivers:
         |
         |${receivers.map(a =>
          s"""${a.compType.jawaName}
             |  enabled:     ${a.enabled}
             |  exported:    ${a.exported}
             |  permissions: ${a.permission.mkString(" ")}
          """.stripMargin).mkString("\n")}
       """.stripMargin
      )
    }
    if(providers.nonEmpty) {
      applicationInfo.append(
      s"""
         |Content Providers:
         |
         |${providers.map(a =>
          s"""${a.compType.jawaName}
             |  enabled:     ${a.enabled}
             |  exported:    ${a.exported}
             |  permissions: ${a.permission.mkString(" ")}
          """.stripMargin).mkString("\n")}
       """.stripMargin
      )
    }
    val filterMap = apk.getIntentFilterDB.getIntentFmap
    if(filterMap.nonEmpty) {
      val filters = filterMap.values.reduce(_ ++ _)
      applicationInfo.append(
      s"""
         |Intent Filters:
         |
         |${filters.map{
            f =>
              var fStr: String = f.getHolder.jawaName
              if(f.getActions.nonEmpty) fStr += "\n  actions: " + f.getActions.mkString(", ")
              if(f.getCategorys.nonEmpty) fStr += "\n  categories: " + f.getCategorys.mkString(", ")
              if(!f.getData.isEmpty){
                fStr +=
                  s"""
                     |  data:
                     |    schemes = ${f.getData.getSchemes.mkString(", ")}
                     |    authorities = ${f.getData.getAuthorities.mkString(", ")}
                     |    paths = ${f.getData.getPaths.mkString(", ")}
                     |    pathPrefixs = ${f.getData.getPathPrefixs.mkString(", ")}
                     |    pathPatterns = ${f.getData.getPathPatterns.mkString(", ")}
                     |    mimeTypes = ${f.getData.getMimeTypes.mkString(", ")}
                   """.stripMargin
              }
              fStr
          }.mkString("\n")}
         |
       """.stripMargin
      )
    }

    val urls = AndroidUrlCollector.collectUrls(global, apk.nameUri)
    var apisAndStrings = ""
    if(urls.nonEmpty) {
      apisAndStrings +=
      s"""Urls:
         |${urls.mkString("\n")}
       """.stripMargin
    }

    Presentation(apkInfo, applicationInfo.toString().trim, apisAndStrings)
  }

  private def getFileSize(file: File): String = {
    val filesize = file.length()
    filesize match {
      case mb if filesize >= 1024*1024 => s"${mb / (1024*1024)} MB"
      case kb if filesize >= 1024 => s"${kb / 1024} KB"
      case b => s"$b bytes"
    }
  }

  private def getFileChecksum(digest: MessageDigest, file: File): String = {
    //Get file input stream for reading the file content
    val fis = new FileInputStream(file)

    //Create byte array to read data in chunks
    val byteArray = new Array[Byte](1024)
    var bytesCount: Int = fis.read(byteArray)
    //Read file data and update in message digest
    while (bytesCount != -1) {
      digest.update(byteArray, 0, bytesCount)
      bytesCount = fis.read(byteArray)
    }

    fis.close()

    //Get the hash's bytes
    val bytes = digest.digest()

    //This bytes[] has bytes in decimal format;
    //Convert it to hexadecimal format
    val sb = new StringBuilder()
    for(i <- 0 until bytes.length) {
      sb.append(Integer.toString((bytes(i) & 0xff) + 0x100, 16).substring(1))
    }

    //return complete hash
    sb.toString()
  }
}
