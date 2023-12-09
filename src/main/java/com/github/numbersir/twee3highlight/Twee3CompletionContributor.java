package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Types;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

final class Twee3CompletionContributor extends CompletionContributor {
    Twee3CompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(Twee3Types.VALUE), new CompletionProvider<>() {
            public void addCompletions(@NotNull CompletionParameters parameters,
                                       @NotNull ProcessingContext context,
                                       @NotNull CompletionResultSet resultSet) {
                resultSet.addElement(LookupElementBuilder.create("Hello"));
            }
        });
    }
}
