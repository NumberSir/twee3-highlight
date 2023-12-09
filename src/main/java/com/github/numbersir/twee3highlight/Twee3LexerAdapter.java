package com.github.numbersir.twee3highlight;

import com.intellij.lexer.FlexAdapter;

public class Twee3LexerAdapter extends FlexAdapter {
    public Twee3LexerAdapter() {
        super(new Twee3Lexer(null));
    }
}
