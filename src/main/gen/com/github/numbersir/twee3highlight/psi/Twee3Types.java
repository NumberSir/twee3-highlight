// This is a generated file. Not intended for manual editing.
package com.github.numbersir.twee3highlight.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.numbersir.twee3highlight.psi.impl.*;

public interface Twee3Types {

  IElementType PROPERTY = new Twee3ElementType("PROPERTY");

  IElementType ANY_CHAR = new Twee3TokenType("ANY_CHAR");
  IElementType ANY_LETTER = new Twee3TokenType("ANY_LETTER");
  IElementType COMMENT = new Twee3TokenType("COMMENT");
  IElementType CRLF = new Twee3TokenType("CRLF");
  IElementType CSS_ID_OR_CLASS_SIGIL = new Twee3TokenType("CSS_ID_OR_CLASS_SIGIL");
  IElementType CSS_IMAGE = new Twee3TokenType("CSS_IMAGE");
  IElementType CSS_STYLE = new Twee3TokenType("CSS_STYLE");
  IElementType C_EN_CHAR = new Twee3TokenType("C_EN_CHAR");
  IElementType HTML_TAG_NAME = new Twee3TokenType("HTML_TAG_NAME");
  IElementType IDENTIFIER = new Twee3TokenType("IDENTIFIER");
  IElementType IDENTIFIER_FIRST_CHAR = new Twee3TokenType("IDENTIFIER_FIRST_CHAR");
  IElementType IDENTIFIER_NEXT_CHAR = new Twee3TokenType("IDENTIFIER_NEXT_CHAR");
  IElementType ID_OR_CLASS = new Twee3TokenType("ID_OR_CLASS");
  IElementType INLINE_CSS = new Twee3TokenType("INLINE_CSS");
  IElementType KEY = new Twee3TokenType("KEY");
  IElementType MACRO_LOOKAHEAD = new Twee3TokenType("MACRO_LOOKAHEAD");
  IElementType MACRO_MATCH = new Twee3TokenType("MACRO_MATCH");
  IElementType MACRO_NAME = new Twee3TokenType("MACRO_NAME");
  IElementType QUOTE_BY_BLOCK_TERMINATOR = new Twee3TokenType("QUOTE_BY_BLOCK_TERMINATOR");
  IElementType QUOTE_BY_LINE_LOOKAHEAD = new Twee3TokenType("QUOTE_BY_LINE_LOOKAHEAD");
  IElementType SEPARATOR = new Twee3TokenType("SEPARATOR");
  IElementType TEMPLATE_NAME = new Twee3TokenType("TEMPLATE_NAME");
  IElementType TW_STYLE = new Twee3TokenType("TW_STYLE");
  IElementType URL = new Twee3TokenType("URL");
  IElementType VALUE = new Twee3TokenType("VALUE");
  IElementType VARIABLE = new Twee3TokenType("VARIABLE");
  IElementType VARIABLE_SIGIL = new Twee3TokenType("VARIABLE_SIGIL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new Twee3PropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
