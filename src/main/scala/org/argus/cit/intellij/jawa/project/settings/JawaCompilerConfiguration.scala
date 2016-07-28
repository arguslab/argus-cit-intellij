///*
// * Copyright (c) 2016. Fengguo Wei and others.
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Detailed contributors are listed in the CONTRIBUTOR.md
// */
//
//package org.argus.cit.intellij.jawa.project.settings
//
//import com.intellij.openapi.components.{PersistentStateComponent, ServiceManager, State, Storage}
//import com.intellij.openapi.module.Module
//import com.intellij.openapi.project.Project
//import com.intellij.util.xmlb.{SkipDefaultValuesSerializationFilters, XmlSerializer}
//import org.jdom.Element
//
//import scala.annotation.tailrec
//
///**
//  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
//  */
//@State(
//  name = "JawaCompilerConfiguration",
//  storages = Array(new Storage("jawa_compiler.xml"))
//)
//class JawaCompilerConfiguration(project: Project) extends PersistentStateComponent[Element] {
//  var defaultProfile: JawaCompilerSettingsProfile = new JawaCompilerSettingsProfile("Default")
//
//  var customProfiles: Seq[JawaCompilerSettingsProfile] = Seq.empty
//
//  def getSettingsForModule(module: Module): JawaCompilerSettings = {
//    val profile = customProfiles.find(_.getModuleNames.contains(module.getName)).getOrElse(defaultProfile)
//    profile.getSettings
//  }
//
//  def configureSettingsForModule(module: Module, source: String, options: Seq[String]) {
//    customProfiles.foreach { profile =>
//      profile.removeModuleName(module.getName)
//      if (profile.getName.startsWith(source) && profile.getModuleNames.isEmpty) {
//        customProfiles = customProfiles.filterNot(_ == profile)
//      }
//    }
//
//    val settings = new JawaCompilerSettings()
//    settings.initFrom(options)
//
//    customProfiles.find(_.getSettings.getState == settings.getState) match {
//      case Some(profile) => profile.addModuleName(module.getName)
//      case None =>
//        val profileNames = customProfiles.iterator.map(_.getName).filter(_.startsWith(source)).toSet
//        @tailrec def firstFreeName(i: Int): String = {
//          val name = source + " " + i
//          if (profileNames.contains(name)) firstFreeName(i + 1) else name
//        }
//        val profile = new JawaCompilerSettingsProfile(firstFreeName(1))
//        profile.setSettings(settings)
//        profile.addModuleName(module.getName)
//        customProfiles = (customProfiles :+ profile).sortBy(_.getName)
//    }
//  }
//
//  def getState: Element = {
//    val configurationElement = XmlSerializer.serialize(defaultProfile.getSettings.getState, new SkipDefaultValuesSerializationFilters())
//
//    customProfiles.foreach { profile =>
//      val profileElement = XmlSerializer.serialize(profile.getSettings.getState, new SkipDefaultValuesSerializationFilters())
//      profileElement.setName("profile")
//      profileElement.setAttribute("name", profile.getName)
//      profileElement.setAttribute("modules", profile.getModuleNames.asScala.mkString(","))
//
//      configurationElement.addContent(profileElement)
//    }
//
//    configurationElement
//  }
//
//  def loadState(configurationElement: Element) {
//
//    defaultProfile.setSettings(new JawaCompilerSettings(XmlSerializer.deserialize(configurationElement, classOf[JawaCompilerSettingsState])))
//
//    customProfiles = configurationElement.getChildren("profile").asScala.map { profileElement =>
//      val profile = new JawaCompilerSettingsProfile(profileElement.getAttributeValue("name"))
//
//      val settings = new JawaCompilerSettings(XmlSerializer.deserialize(profileElement, classOf[JawaCompilerSettingsState]))
//      profile.setSettings(settings)
//
//      val moduleNames = profileElement.getAttributeValue("modules").split(",").filter(!_.isEmpty)
//      moduleNames.foreach(profile.addModuleName)
//
//      profile
//    }
//  }
//}
//
//object ScalaCompilerConfiguration {
//  def instanceIn(project: Project): JawaCompilerConfiguration =
//    ServiceManager.getService(project, classOf[JawaCompilerConfiguration])
//}