package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.util.PsiLiteralUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Twee3FoldingBuilder extends FoldingBuilderEx implements DumbAware {

    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        // Initialize the group of folding regions that will expand/collapse together.
        FoldingGroup group = FoldingGroup.newGroup(Twee3Annotator.TWEE3_PREFIX_STR);
        // Initialize the list of folding regions
        List<FoldingDescriptor> descriptors = new ArrayList<>();

        root.accept(new JavaRecursiveElementWalkingVisitor() {

            @Override
            public void visitLiteralExpression(@NotNull PsiLiteralExpression literalExpression) {
                super.visitLiteralExpression(literalExpression);

                String value = PsiLiteralUtil.getStringLiteralContent(literalExpression);
                if (value != null && value.startsWith(Twee3Annotator.TWEE3_PREFIX_STR + Twee3Annotator.TWEE3_SEPARATOR_STR)) {
                    Project project = literalExpression.getProject();
                    String key = value.substring(
                            Twee3Annotator.TWEE3_PREFIX_STR.length() + Twee3Annotator.TWEE3_SEPARATOR_STR.length()
                    );
                    // find Twee3Property for the given key in the project
                    Twee3Property twee3Property = ContainerUtil.getOnlyItem(Twee3Util.findProperties(project, key));
                    if (twee3Property != null) {
                        // Add a folding descriptor for the literal expression at this node.
                        descriptors.add(new FoldingDescriptor(literalExpression.getNode(),
                                new TextRange(literalExpression.getTextRange().getStartOffset() + 1,
                                        literalExpression.getTextRange().getEndOffset() - 1),
                                group, Collections.singleton(twee3Property)));
                    }
                }
            }
        });

        return descriptors.toArray(FoldingDescriptor.EMPTY);
    }

    /**
     * Gets the Twee3 Language 'value' string corresponding to the 'key'
     *
     * @param node Node corresponding to PsiLiteralExpression containing a string in the format
     *             TWEE3_PREFIX_STR + TWEE3_SEPARATOR_STR + Key, where Key is
     *             defined by the Twee3 language file.
     */
    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        if (node.getPsi() instanceof PsiLiteralExpression psiLiteralExpression) {
            String text = PsiLiteralUtil.getStringLiteralContent(psiLiteralExpression);
            if (text == null) {
                return null;
            }

            String key = text.substring(Twee3Annotator.TWEE3_PREFIX_STR.length() +
                    Twee3Annotator.TWEE3_SEPARATOR_STR.length());

            Twee3Property twee3Property = ContainerUtil.getOnlyItem(
                    Twee3Util.findProperties(psiLiteralExpression.getProject(), key)
            );
            if (twee3Property == null) {
                return StringUtil.THREE_DOTS;
            }

            String propertyValue = twee3Property.getValue();
            // IMPORTANT: keys can come with no values, so a test for null is needed
            // IMPORTANT: Convert embedded \n to backslash n, so that the string will look
            // like it has LF embedded in it and embedded " to escaped "
            if (propertyValue == null) {
                return StringUtil.THREE_DOTS;
            }

            return propertyValue.replaceAll("\n", "\\n").replaceAll("\"", "\\\\\"");
        }

        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return true;
    }

}
