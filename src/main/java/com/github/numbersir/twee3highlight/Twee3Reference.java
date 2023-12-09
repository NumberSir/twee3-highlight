package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

final class Twee3Reference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    private final String key;

    Twee3Reference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<Twee3Property> properties = Twee3Util.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (Twee3Property property : properties) {
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[0]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @Override
    public Object @NotNull [] getVariants() {
        Project project = myElement.getProject();
        List<Twee3Property> properties = Twee3Util.findProperties(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final Twee3Property property : properties) {
            if (property.getKey() != null && !property.getKey().isEmpty()) {
                variants.add(LookupElementBuilder
                        .create(property).withIcon(Twee3Icons.FILE)
                        .withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }

}
