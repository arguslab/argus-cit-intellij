/*
 * Copyright (c) 2016. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.cit.intellij.core.lang.lexer

import com.intellij.lexer.{Lexer, LexerPosition}
import com.intellij.psi.tree.IElementType
import org.argus.jawa.core.{DefaultReporter, Reporter}
import org.argus.jawa.core.io.{Position, SourceFile}
import org.argus.jawa.compiler.lexer.{Token, JawaLexer => CompilerJawaLexer, Tokens => CompilerTokens}
import org.argus.cit.intellij.core.lang.lexer.JawaTokenTypes._
import org.sireum.util._

/**
  * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
  */
class JawaLexer extends Lexer {
  def tokenise(source: Either[String, SourceFile], reporter: Reporter): List[(JawaElementType, Position)] = {
    val tokens = CompilerJawaLexer.tokenise(source, reporter)
    tokens.map { case Token(typ, pos, _) =>
      val jawatyp = typ match {
        case CompilerTokens.CLASS_OR_INTERFACE => CLASS_OR_INTERFACE
        case CompilerTokens.METHOD => METHOD
        case CompilerTokens.STATIC_FIELD => STATIC_FIELD
        case CompilerTokens.EXTENDS_AND_IMPLEMENTS => EXTENDS_AND_IMPLEMENTS
        case CompilerTokens.CONST_CLASS => CONST_CLASS
        case CompilerTokens.LENGTH => LENGTH
        case CompilerTokens.EQUALS => EQUALS
        case CompilerTokens.ID => ID
        case CompilerTokens.LOCATION_ID => LOCATION_ID
        case CompilerTokens.STATIC_ID => STATIC_ID
        case CompilerTokens.EXCEPTION => EXCEPTION
        case CompilerTokens.INSTANCEOF => INSTANCEOF
        case CompilerTokens.NEW => NEW
        case CompilerTokens.THROW => THROW
        case CompilerTokens.CATCH => CATCH
        case CompilerTokens.IF => IF
        case CompilerTokens.THEN => THEN
        case CompilerTokens.GOTO => GOTO
        case CompilerTokens.SWITCH => SWITCH
        case CompilerTokens.ELSE => ELSE
        case CompilerTokens.RETURN => RETURN
        case CompilerTokens.CALL => CALL
        case CompilerTokens.MONITOR_ENTER => MONITOR_ENTER
        case CompilerTokens.MONITOR_EXIT => MONITOR_EXIT
        case CompilerTokens.EOF => EOF
        case CompilerTokens.LBRACKET => LBRACKET
        case CompilerTokens.RBRACKET => RBRACKET
        case CompilerTokens.LPAREN => LPAREN
        case CompilerTokens.RPAREN => RPAREN
        case CompilerTokens.LBRACE => LBRACE
        case CompilerTokens.RBRACE => RBRACE
        case CompilerTokens.STRING_LITERAL => STRING_LITERAL
        case CompilerTokens.FLOATING_POINT_LITERAL => FLOATING_POINT_LITERAL
        case CompilerTokens.INTEGER_LITERAL => INTEGER_LITERAL
        case CompilerTokens.CHARACTER_LITERAL => CHARACTER_LITERAL
        case CompilerTokens.TRUE => TRUE
        case CompilerTokens.FALSE => FALSE
        case CompilerTokens.NULL => NULL
        case CompilerTokens.COMMA => COMMA
        case CompilerTokens.DOT => DOT
        case CompilerTokens.SEMI => SEMI
        case CompilerTokens.COLON => COLON
        case CompilerTokens.ARROW => ARROW
        case CompilerTokens.AT => AT
        case CompilerTokens.RANGE => RANGE
        case CompilerTokens.ASSIGN_OP => ASSIGN_OP
        case CompilerTokens.HAT => HAT
        case CompilerTokens.CMP => CMP
        case CompilerTokens.LINE_COMMENT => LINE_COMMENT
        case CompilerTokens.MULTILINE_COMMENT => MULTILINE_COMMENT
        case CompilerTokens.DOC_COMMENT => DOC_COMMENT
        case CompilerTokens.WS => WS
        case CompilerTokens.OP => OP
        case CompilerTokens.UNKNOWN => UNKNOWN
      }
      (jawatyp, pos)
    }
  }

  private val tokens: MList[(JawaElementType, Position)] = mlistEmpty
  private var myTokenStart: Int = 0
  private var myTokenEnd: Int = 0
  private var myTokenType: IElementType = null
  private var myState: Int = 0
  private var myBufferEnd: Int = 0
  private var myBuffer: CharSequence = null

  override def start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int): Unit = {
    myBuffer = buffer.subSequence(startOffset, endOffset)
    myTokenStart = startOffset
    myTokenEnd = startOffset
    myTokenType = null
    myState = initialState
    myBufferEnd = endOffset
    tokens ++= tokenise(Left(myBuffer.toString), new DefaultReporter)
  }

  override def advance(): Unit = {
    if(tokens.nonEmpty) {
      val (nextTokenType, pos) = tokens.remove(0)
      myTokenType = nextTokenType
      myTokenEnd = pos.end
    } else {
      myTokenType = null
    }
  }

  override def getTokenEnd: Int = myTokenEnd

  override def getCurrentPosition: LexerPosition = MyPosition(myTokenStart, myState)

  override def getBufferEnd: Int = myBufferEnd

  override def getTokenType: IElementType = myTokenType

  override def getBufferSequence: CharSequence = myBuffer

  override def getState: Int = myState

  override def getTokenStart: Int = myTokenStart

  override def restore(lexerPosition: LexerPosition): Unit = {
    start(getBufferSequence, lexerPosition.getOffset, myBufferEnd, myState)
  }

  private case class MyPosition(start: Int, state: Int) extends LexerPosition {
    def getOffset: Int = start
    def getState: Int = state
  }
}