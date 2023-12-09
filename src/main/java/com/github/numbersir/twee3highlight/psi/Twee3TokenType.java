package com.github.numbersir.twee3highlight.psi;

import com.intellij.psi.tree.IElementType;
import com.github.numbersir.twee3highlight.Twee3Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class Twee3TokenType extends IElementType {
    public Twee3TokenType(@NotNull @NonNls String debugName) {
        super(debugName, Twee3Language.INSTANCE);
    }

    @Override
    public String toString() {
        return "Twee3TokenType." + super.toString();
    }
}
