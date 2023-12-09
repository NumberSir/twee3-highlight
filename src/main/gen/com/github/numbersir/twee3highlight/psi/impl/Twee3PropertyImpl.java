// This is a generated file. Not intended for manual editing.
package com.github.numbersir.twee3highlight.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.numbersir.twee3highlight.psi.Twee3Types.*;
import com.github.numbersir.twee3highlight.psi.*;
import com.intellij.navigation.ItemPresentation;

public class Twee3PropertyImpl extends Twee3NamedElementImpl implements Twee3Property {

  public Twee3PropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Twee3Visitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Twee3Visitor) accept((Twee3Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getKey() {
    return Twee3PsiImplUtil.getKey(this);
  }

  @Override
  public String getValue() {
    return Twee3PsiImplUtil.getValue(this);
  }

  @Override
  public String getName() {
    return Twee3PsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return Twee3PsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return Twee3PsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public ItemPresentation getPresentation() {
    return Twee3PsiImplUtil.getPresentation(this);
  }

}
