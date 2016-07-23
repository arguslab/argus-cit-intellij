/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa.local

import java.lang.ref.SoftReference
import java.util
import java.util.Map.Entry

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */
class Cache[K, V](capacity: Int) {
  private val lock = new Object()

  private val map = new util.LinkedHashMap[K, SoftReference[V]](capacity, 0.75F, true) {
    override def removeEldestEntry(eldest: Entry[K, SoftReference[V]]) = size > capacity
  }

  def getOrUpdate(key: K)(value: => V): V = lock.synchronized {
    Option(map.get(key)).flatMap(reference => Option(reference.get())).getOrElse {
      val v = value
      map.put(key, new SoftReference(v))
      v
    }
  }
}
