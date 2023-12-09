package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.parser.Twee3Parser;
import com.github.numbersir.twee3highlight.psi.Twee3File;
import com.github.numbersir.twee3highlight.psi.Twee3TokenSets;
import com.github.numbersir.twee3highlight.psi.Twee3Types;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

final class Twee3ParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(Twee3Language.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new Twee3LexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return Twee3TokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new Twee3Parser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new Twee3File(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return Twee3Types.Factory.createElement(node);
    }
}
