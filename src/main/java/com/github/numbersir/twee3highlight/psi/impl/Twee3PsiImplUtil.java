package com.github.numbersir.twee3highlight.psi.impl;

import com.github.numbersir.twee3highlight.psi.Twee3ElementFactory;
import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.github.numbersir.twee3highlight.psi.Twee3Types;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Twee3PsiImplUtil {
    public static String getKey(Twee3Property element) {
        ASTNode keyNode = element.getNode().findChildByType(Twee3Types.KEY);
        if (keyNode != null) {
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(Twee3Property element) {
        ASTNode valueNode = element.getNode().findChildByType(Twee3Types.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(Twee3Property element) {
        return getKey(element);
    }

    public static PsiElement setName(Twee3Property element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(Twee3Types.KEY);
        if (keyNode != null) {
            Twee3Property property = Twee3ElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(Twee3Property element) {
        ASTNode keyNode = element.getNode().findChildByType(Twee3Types.KEY);
        return keyNode != null ? keyNode.getPsi() : null;
    }

    public static ItemPresentation getPresentation(final Twee3Property element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Override
            public Icon getIcon(boolean unused) {
                return element.getIcon(0);
            }
        };
    }
}
