package com.github.numbersir.twee3highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

final class Twee3ColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[] {
            new AttributesDescriptor("Key", Twee3SyntaxHighlighter.KEY),
            new AttributesDescriptor("Separator", Twee3SyntaxHighlighter.SEPERATOR),
            new AttributesDescriptor("Value", Twee3SyntaxHighlighter.VALUE),
            new AttributesDescriptor("Bad value", Twee3SyntaxHighlighter.BAD_CHARACTER),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return Twee3Icons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new Twee3SyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return """
                :: Hello World [widget]
                <<widget>>
                    hello world $name!
                    <span>this is a _desc test</span>
                    <<set $arg to 123>>
                    <!-- some comments -->
                    /* other comments */
                <</widget>>
                """;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Twee3";
    }
}
