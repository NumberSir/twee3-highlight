package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;

import java.util.List;

final class Twee3Annotator implements Annotator {
    // Define strings for the Twee3 language prefix - used for annotations, line markers, etc.
    public static final String TWEE3_PREFIX_STR = "twee3";
    public static final String TWEE3_SEPARATOR_STR = ":";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof PsiLiteralExpression literalExpression)) {
            return;
        }

        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if (value == null || !value.startsWith(TWEE3_PREFIX_STR + TWEE3_SEPARATOR_STR)) {
            return;
        }

        TextRange prefixRange = TextRange.from(element.getTextRange().getStartOffset(), TWEE3_PREFIX_STR.length() + 1);
        TextRange separatorRange = TextRange.from(prefixRange.getEndOffset(), TWEE3_SEPARATOR_STR.length());
        TextRange keyRange = new TextRange(separatorRange.getEndOffset(), element.getTextRange().getEndOffset() - 1);

        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(prefixRange)
                .textAttributes(DefaultLanguageHighlighterColors.KEYWORD)
                .create();
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(separatorRange)
                .textAttributes(Twee3SyntaxHighlighter.SEPERATOR)
                .create();

        String key = value.substring(TWEE3_PREFIX_STR.length() + TWEE3_SEPARATOR_STR.length());
        List<Twee3Property> properties = Twee3Util.findProperties(element.getProject(), key);
        if (properties.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved property")
                    .range(keyRange)
                    .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                    .withFix(new Twee3CreatePropertyQuickFix(key))
                    .create();
        } else {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(keyRange)
                    .textAttributes(Twee3SyntaxHighlighter.VALUE)
                    .create();
        }
    }
}
