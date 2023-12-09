package com.github.numbersir.twee3highlight.psi;

import com.github.numbersir.twee3highlight.Twee3FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;

public class Twee3ElementFactory {
    public static Twee3Property createProperty(Project project, String name) {
        Twee3File file = createFile(project, name);
        return (Twee3Property) file.getFirstChild();
    }

    public static Twee3File createFile(Project project, String text) {
        String name = "dummy.twee3";
        return (Twee3File) PsiFileFactory.getInstance(project).createFileFromText(name, Twee3FileType.INSTANCE, text);
    }

    public static Twee3Property createProperty(Project project, String name, String value) {
        final Twee3File file = createFile(project, name + " = " + value);
        return (Twee3Property) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final Twee3File file = createFile(project, "\n");
        return file.getFirstChild();
    }
}
