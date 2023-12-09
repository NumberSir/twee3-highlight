package com.github.numbersir.twee3highlight.psi.impl;

import com.github.numbersir.twee3highlight.psi.Twee3NamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class Twee3NamedElementImpl extends ASTWrapperPsiElement implements Twee3NamedElement {
    public Twee3NamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
