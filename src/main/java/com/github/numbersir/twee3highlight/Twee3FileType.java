package com.github.numbersir.twee3highlight;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public final class Twee3FileType extends LanguageFileType {
    public static final Twee3FileType INSTANCE = new Twee3FileType();

    private Twee3FileType() {
        super(Twee3Language.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Twee3 File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Twee3 language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "twee";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return Twee3Icons.FILE;
    }
}
