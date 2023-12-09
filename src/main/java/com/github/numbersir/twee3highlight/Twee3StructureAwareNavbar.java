package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3File;
import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.intellij.icons.AllIcons;
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

final class Twee3StructureAwareNavbar extends StructureAwareNavBarModelExtension {

    @NotNull
    @Override
    protected Language getLanguage() {
        return Twee3Language.INSTANCE;
    }

    @Override
    public @Nullable String getPresentableText(Object object) {
        if (object instanceof Twee3File) {
            return ((Twee3File) object).getName();
        }
        if (object instanceof Twee3Property) {
            return ((Twee3Property) object).getName();
        }

        return null;
    }

    @Override
    @Nullable
    public Icon getIcon(Object object) {
        if (object instanceof Twee3Property) {
            return AllIcons.Nodes.Property;
        }

        return null;
    }

}
