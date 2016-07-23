/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jc.incremental.jawa
package remote

import java.io.{PrintStream, PrintWriter}

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class ClientEventProcessor(client: Client) {
  def process(event: Event) {
    event match {
      case MessageEvent(kind, text, source, line, column) =>
        client.message(kind, text, source, line, column)

      case ProgressEvent(text, done) =>
        client.progress(text, done)

      case DebugEvent(text) =>
        client.debug(text)

      case TraceEvent(message, lines) =>
        client.trace(new ServerException(message, lines))

      case GeneratedEvent(source, module, name) =>
        client.generated(source, module, name)

      case DeletedEvent(module) =>
        client.deleted(module)

      case SourceProcessedEvent(source) =>
        client.processed(source)

      case CompilationEndEvent() =>
        client.compilationEnd()
    }
  }
}

class ServerException(message: String, lines: Array[String]) extends Exception {
  override def getMessage = message

  override def printStackTrace(s: PrintWriter) {
    lines.foreach(s.println)
  }

  override def printStackTrace(s: PrintStream) {
    lines.foreach(s.println)
  }
}
