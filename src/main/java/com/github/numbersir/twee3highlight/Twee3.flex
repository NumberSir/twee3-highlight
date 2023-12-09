package com.github.numbersir.twee3highlight;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.numbersir.twee3highlight.psi.Twee3Types;
import com.intellij.psi.TokenType;

%%

%class Twee3Lexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
SEPARATOR=[:=]
KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "

// from sugarcube-2 parserlib.js
QUOTE_BY_BLOCK=^<<<\n
QUOTE_BY_LINE=^>+\n

MACRO_NAME=[A-Za-z][\w-]*|[=-]
MACRO=<<(/?{MACRO_NAME})(?:\s*)((?:(?:/\*[^*]*\*+(?:[^/*][^*]*\*+)*/)|(?://.*\n)|(?:`(?:\\.|[^`\\])*`)|(?:"(?:\\.|[^"\\])*")|(?:'(?:\\.|[^'\\])*')|(?:\[(?:[<>]?[Ii][Mm][Gg])?\[[^\r\n]*?\]\]+)|[^>]|(?:>(?!>)))*)>>
LINK=\[\[[^]
URL_LINK=(:file|https?|mailto|ftp|javascript|irc|news|data):[^\s\'\"]+
IMAGE=\[[<>]?[Ii][Mm][Gg]\[
MONOSPACED_BY_BLOCK=^\{\{\{\n((?:^[^\n]*\n)+?)(^}}}$\n?)
FORMAT_BY_CHAR=''|//|__|\^\^|~~|==|\{\{\{
CUSTOM_STYLE=@@
VERBATIM_TEXT=(?:"{3}((?:.|\n)*?)"{3})|(?:<[Nn][Oo][Ww][Ii][Kk][Ii]>((?:.|\n)*?)</[Nn][Oo][Ww][Ii][Kk][Ii]>)
HORIZONTAL_RULE=^----+$\n?|<[Hh][Rr]\s*/?>\n?
EMDASH=[]
DOUBLE_DOLLAR_SIGN=[]
NAKED_VARIABLE=[]
TEMPLATE_NAME=[A-Za-z][\w-]*
TEMPLATE=[]
HEADING=[]
TABLE=[]
LIST=[]
COMMENT_BY_BLOCK=[]
LINE_CONTINUATION=[]
LINE_BREAK=[]
HTML_CHARACTER_REFERENCE=[]
XML_PROLOG=[]
VERBATIM_HTML=[]
VERBATIM_SCRIPT_TAG=[]
STYLE_TAG=[]
SVG_TAG=[]
HTML_TAG=[]

%state WAITING_VALUE

%%

<YYINITIAL> {END_OF_LINE_COMMENT}   { yybegin(YYINITIAL); return Twee3Types.COMMENT; }
<YYINITIAL> {KEY_CHARACTER}+        { yybegin(YYINITIAL); return Twee3Types.KEY; }
<YYINITIAL> {SEPARATOR}             { yybegin(WAITING_VALUE); return Twee3Types.SEPARATOR; }

<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return Twee3Types.VALUE; }

({CRLF}|{WHITE_SPACE})+     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                         { return TokenType.BAD_CHARACTER; }
