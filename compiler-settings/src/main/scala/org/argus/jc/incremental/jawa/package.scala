/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental

import java.io.{BufferedInputStream, File, IOException, InputStream}
import java.net.URL
import java.util.Properties

import scala.language.implicitConversions

/**
  * Created by fgwei on 7/23/16.
  */
package object jawa {
  type Closeable = {
    def close()
  }

  def using[A <: Closeable, B](resource: A)(block: A => B): B = {
    import _root_.scala.language.reflectiveCalls

    try {
      block(resource)
    } finally {
      resource.close()
    }
  }

  def extractor[A, B](f: A => B) = new Extractor[A, B](f)

  class Extractor[A, B](f: A => B) {
    def unapply(a: A): Some[B] = Some(f(a))
  }

  implicit def toRightBiasedEither[A, B](either: Either[A, B]): Either.RightProjection[A, B] = either.right

  implicit class PipedObject[T](val v: T) extends AnyVal {
    def |>[R](f: T => R) = f(v)
  }

  def readProperty(classLoader: ClassLoader, resource: String, name: String): Option[String] = {
    Option(classLoader.getResourceAsStream(resource))
      .flatMap(it => using(new BufferedInputStream(it))(readProperty(_, name)))
  }

  def readProperty(file: File, resource: String, name: String): Option[String] = {
    try {
      val url = new URL("jar:%s!/%s".format(file.toURI.toString, resource))
      Option(url.openStream).flatMap(it => using(new BufferedInputStream(it))(readProperty(_, name)))
    } catch {
      case _: IOException => None
    }
  }

  private def readProperty(input: InputStream, name: String): Option[String] = {
    val properties = new Properties()
    properties.load(input)
    Option(properties.getProperty(name))
  }
}