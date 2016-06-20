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

ID=([:letter:]([\w]|[$])*)
APOSTROPHE_ID=(`[^`]+`)
STATIC_ID=(`@@([^`]|[^@])+`)
NUMBER=[0-9]+(\.[0-9]*)?
STRING_LITERAL=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")

END_OF_LINE_COMMENT="/""/"[^\r\n]*

%%
<YYINITIAL> {
  {WHITE_SPACE}           { return com.intellij.psi.TokenType.WHITE_SPACE; }

  {END_OF_LINE_COMMENT}   { return JawaTokenTypes.LINE_COMMENT(); }

  ";"                     { return SEMI; }
  ","                     { return COMMA; }
  "@"                     { return AT; }
  "@@"                    { return STATIC_PREFIX; }
  "^"                     { return HAT; }
  "|"                     { return BAR; }
  ":="                    { return ASSIGN_OP; }
  "=>"                    { return ARROW; }
  "."                     { return DOT; }
  ".."                    { return RANGE; }
  "#"                     { return POUND; }
  "{"                     { return LBRACE; }
  "}"                     { return RBRACE; }
  "["                     { return LBRACKET; }
  "]"                     { return RBRACKET; }
  "("                     { return LPAREN; }
  ")"                     { return RPAREN; }
  "record"                { return CLASS_OR_INTERFACE; }
  "extends"               { return EXTENDS_AND_IMPLEMENTS; }
  "global"                { return STATIC_FIELD; }
  "procedure"             { return METHOD; }
  "call"                  { return CALL; }
  "throw"                 { return THROW; }
  "if"                    { return IF; }
  "then"                  { return THEN; }
  "goto"                  { return GOTO; }
  "switch"                { return SWITCH; }
  "else"                  { return ELSE; }
  "return"                { return RETURN; }
  "monitorenter"          { return MONITOR_ENTER; }
  "monitorexit"           { return MONITOR_EXIT; }
  "new"                   { return NEW; }
  "Exception"             { return EXCEPTION; }
  "constclass"            { return CONST_CLASS; }
  "length"                { return LENGTH; }
  "instanceof"            { return INSTANCEOF; }
  "null"                  { return NULL_LITERAL; }
  "catch"                 { return CATCH; }
  "@owner"                { return OWNER_KEY; }
  "@type"                 { return TYPE_KEY; }
  "@classDescriptor"      { return CLASS_DESCRIPTOR_KEY; }
  "@signature"            { return SIGNATURE_KEY; }
  "fcmpl"                 { return FCMPL; }
  "fcmpg"                 { return FCMPG; }
  "dcmpl"                 { return DCMPL; }
  "dcmpg"                 { return DCMPG; }
  "lcmp"                  { return LCMP; }
  "+"                     { return ADD; }
  "-"                     { return SUB; }
  "*"                     { return MUL; }
  "/"                     { return DIV; }
  "%%"                    { return REM; }
  "^&"                    { return AND; }
  "^|"                    { return OR; }
  "^~"                    { return XOR; }
  "^<"                    { return SHL; }
  "^>"                    { return SHR; }
  "^>>"                   { return USHR; }

  {ID}                    { return ID; }
  {APOSTROPHE_ID}         { return APOSTROPHE_ID; }
  {STATIC_ID}             { return STATIC_ID; }
  {NUMBER}                { return NUMBER; }
  {STRING_LITERAL}        { return STRING_LITERAL; }

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}


"/**" ("*"? [^\/])* "*/" { //for comments in interpolated strings
    return JawaTokenTypes.DOC_COMMENT();
}

"/*" ("*"? [^\/])* "*/" { //for comments in interpolated strings
    return JawaTokenTypes.BLOCK_COMMENT();
}