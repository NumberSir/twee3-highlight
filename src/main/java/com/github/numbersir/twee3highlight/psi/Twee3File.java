package com.github.numbersir.twee3highlight.psi;

import com.github.numbersir.twee3highlight.Twee3FileType;
import com.github.numbersir.twee3highlight.Twee3Language;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class Twee3File extends PsiFileBase {
    public Twee3File(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, Twee3Language.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return Twee3FileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Twee3 File";
    }
}
