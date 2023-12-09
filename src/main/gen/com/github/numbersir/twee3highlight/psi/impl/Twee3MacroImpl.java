// This is a generated file. Not intended for manual editing.
package com.github.numbersir.twee3highlight.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.numbersir.twee3highlight.psi.Twee3Types.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.numbersir.twee3highlight.psi.*;

public class Twee3MacroImpl extends ASTWrapperPsiElement implements Twee3Macro {

  public Twee3MacroImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Twee3Visitor visitor) {
    visitor.visitMacro(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Twee3Visitor) accept((Twee3Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getMacroLookahead() {
    return findChildByType(MACRO_LOOKAHEAD);
  }

}
