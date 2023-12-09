package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

final class Twee3LineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        // This must be an element with a literal expression as a parent
        if (!(element instanceof PsiJavaTokenImpl) || !(element.getParent() instanceof PsiLiteralExpression)) {
            return;
        }

        // The literal expression must start with the Twee3 language literal expression
        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element.getParent();
        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if ((value == null) || !value.startsWith(Twee3Annotator.TWEE3_PREFIX_STR + Twee3Annotator.TWEE3_SEPARATOR_STR)) {
            return;
        }

        // Get the Twee3 language property usage
        Project project = element.getProject();
        String possibleProperties = value.substring(
                Twee3Annotator.TWEE3_PREFIX_STR.length() + Twee3Annotator.TWEE3_SEPARATOR_STR.length()
        );
        final List<Twee3Property> properties = Twee3Util.findProperties(project, possibleProperties);
        if (!properties.isEmpty()) {
            // Add the property to a collection of line marker info
            NavigationGutterIconBuilder<PsiElement> builder = NavigationGutterIconBuilder.create(Twee3Icons.FILE)
                    .setTargets(properties)
                    .setTooltipText("Navigate to Twee3 language property");
            result.add(builder.createLineMarkerInfo(element));
        }
    }
}
