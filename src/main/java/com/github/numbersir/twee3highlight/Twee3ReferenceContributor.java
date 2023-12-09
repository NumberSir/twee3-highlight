package com.github.numbersir.twee3highlight;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.github.numbersir.twee3highlight.Twee3Annotator.TWEE3_PREFIX_STR;
import static com.github.numbersir.twee3highlight.Twee3Annotator.TWEE3_SEPARATOR_STR;

final class Twee3ReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class), new PsiReferenceProvider() {
            @Override
            public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
                if ((value != null && value.startsWith(TWEE3_PREFIX_STR + TWEE3_SEPARATOR_STR))) {
                    TextRange property = new TextRange(TWEE3_PREFIX_STR.length() + TWEE3_SEPARATOR_STR.length() + 1, value.length() + 1);
                    return new PsiReference[]{new Twee3Reference(element, property)};
                }
                return PsiReference.EMPTY_ARRAY;
            }
        });
    }

}
