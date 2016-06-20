package org.argus.cit.intellij.core.lang.lexer;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static org.argus.cit.intellij.core.lang.psi.JawaTypes.*;

%%

%{
  public _JawaLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _JawaLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL="\r"|"\n"|"\r\n"
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+

WS=[ \t\n\x0B\f\r]+
ID=([:letter:]([\w]|[$])*)
APOSTROPHE_ID=(`[^`]+`)
STATIC_ID=(`@@([^`]|[^@])+`)
NUMBER=[0-9]+(\.[0-9]*)?
STRING_LITERAL=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")

%%
<YYINITIAL> {
  {WHITE_SPACE}         { return com.intellij.psi.TokenType.WHITE_SPACE; }

  ";"                   { return SEMI; }
  ","                   { return COMMA; }
  "@"                   { return AT; }
  "@@"                  { return STATIC_PREFIX; }
  "^"                   { return HAT; }
  "|"                   { return BAR; }
  ":="                  { return ASSIGN_OP; }
  "=>"                  { return ARROW; }
  "."                   { return DOT; }
  ".."                  { return RANGE; }
  "#"                   { return POUND; }
  "{"                   { return LBRACE; }
  "}"                   { return RBRACE; }
  "["                   { return LBRACKET; }
  "]"                   { return RBRACKET; }
  "("                   { return LPAREN; }
  ")"                   { return RPAREN; }
  "record"              { return CLASS_OR_INTERFACE; }
  "extends"             { return EXTENDS_AND_IMPLEMENTS; }
  "global"              { return STATIC_FIELD; }
  "procedure"           { return METHOD; }
  "call"                { return CALL; }
  "throw"               { return THROW; }
  "if"                  { return IF; }
  "then"                { return THEN; }
  "goto"                { return GOTO; }
  "switch"              { return SWITCH; }
  "else"                { return ELSE; }
  "return"              { return RETURN; }
  "monitorenter"        { return MONITOR_ENTER; }
  "monitorexit"         { return MONITOR_EXIT; }
  "new"                 { return NEW; }
  "Exception"           { return EXCEPTION; }
  "constclass"          { return CONST_CLASS; }
  "length"              { return LENGTH; }
  "instanceof"          { return INSTANCEOF; }
  "null"                { return NULL_LITERAL; }
  "catch"               { return CATCH; }

  {WS}                  { return WS; }
  {ID}                  { return ID; }
  {APOSTROPHE_ID}       { return APOSTROPHE_ID; }
  {STATIC_ID}           { return STATIC_ID; }
  {NUMBER}              { return NUMBER; }
  {STRING_LITERAL}      { return STRING_LITERAL; }

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
