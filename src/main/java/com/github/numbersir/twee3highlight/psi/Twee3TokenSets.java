package com.github.numbersir.twee3highlight.psi;

import com.intellij.psi.tree.TokenSet;

public interface Twee3TokenSets {
    TokenSet IDENTIFIERS = TokenSet.create(Twee3Types.KEY);
    TokenSet COMMENTS = TokenSet.create(Twee3Types.COMMENT);
}
