package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.intellij.ide.IconProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

final class Twee3PropertyIconProvider extends IconProvider {

    @Override
    public @Nullable Icon getIcon(@NotNull PsiElement element, int flags) {
        return element instanceof Twee3Property ? Twee3Icons.FILE : null;
    }

}
