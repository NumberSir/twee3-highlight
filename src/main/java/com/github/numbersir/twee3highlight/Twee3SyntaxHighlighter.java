package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Types;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class Twee3SyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey SEPERATOR = createTextAttributesKey("TWEE3_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey KEY = createTextAttributesKey("TWEE3_KEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey VALUE = createTextAttributesKey("TWEE3_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("TWEE3_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("TWEE3_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPERATOR};
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new Twee3LexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(Twee3Types.SEPARATOR)) {
            return SEPARATOR_KEYS;
        }
        if (tokenType.equals(Twee3Types.KEY)) {
            return KEY_KEYS;
        }
        if (tokenType.equals(Twee3Types.VALUE)) {
            return VALUE_KEYS;
        }
        if (tokenType.equals(Twee3Types.COMMENT)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }
}
