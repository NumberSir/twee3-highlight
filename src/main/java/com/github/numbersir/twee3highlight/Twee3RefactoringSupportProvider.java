package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class Twee3RefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement elementToRename, @Nullable PsiElement context) {
        return (elementToRename instanceof Twee3Property);
    }

}
