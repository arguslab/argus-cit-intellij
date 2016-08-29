/*
 * Copyright (c) 2016. Fengguo (Hugo) Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa
package remote

import java.net.{ConnectException, InetAddress, UnknownHostException}

import org.argus.jc.incremental.jawa.data.{CompilationData, CompilerData}
import org.jetbrains.jps.incremental.ModuleLevelBuilder.ExitCode

/**
 * @author Pavel Fatin
 */
class RemoteServer(val address: InetAddress, val port: Int) extends Server with RemoteResourceOwner {
  def compile(compilerData: CompilerData, compilationData: CompilationData, client: Client): ExitCode = {
    val arguments = Arguments(compilerData, compilationData).asStrings

    try {
      send(serverAlias, arguments, client)
      ExitCode.OK
    } catch {
      case e: ConnectException =>
        val firstLine = s"Cannot connect to compile server at ${address.toString}:$port"
        val secondLine = "Trying to compile without it"
        val message = s"$firstLine\n$secondLine"
        client.warning(message)
        client.debug(s"$firstLine\n${e.toString}\n${e.getStackTrace.mkString("\n")}")
        JawaBuilder.localServer.compile(compilerData, compilationData, client)
      case e: UnknownHostException =>
        val message = "Unknown IP address of compile server host: " + address.toString
        client.error(message)
        client.debug(s"$message\n${e.toString}\n${e.getStackTrace.mkString("\n")}")
        ExitCode.ABORT
    }
  }
}