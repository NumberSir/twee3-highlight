package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3File;
import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.github.numbersir.twee3highlight.psi.impl.Twee3PropertyImpl;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Twee3StructureViewElement implements StructureViewTreeElement, SortableTreeElement {

    private final NavigatablePsiElement twee3Element;

    public Twee3StructureViewElement(NavigatablePsiElement element) {
        this.twee3Element = element;
    }

    @Override
    public Object getValue() {
        return twee3Element;
    }

    @Override
    public void navigate(boolean requestFocus) {
        twee3Element.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return twee3Element.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return twee3Element.canNavigateToSource();
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = twee3Element.getName();
        return name != null ? name : "";
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        ItemPresentation presentation = twee3Element.getPresentation();
        return presentation != null ? presentation : new PresentationData();
    }

    @Override
    public TreeElement @NotNull [] getChildren() {
        if (twee3Element instanceof Twee3File) {
            List<Twee3Property> properties = PsiTreeUtil.getChildrenOfTypeAsList(twee3Element, Twee3Property.class);
            List<TreeElement> treeElements = new ArrayList<>(properties.size());
            for (Twee3Property property : properties) {
                treeElements.add(new Twee3StructureViewElement((Twee3PropertyImpl) property));
            }
            return treeElements.toArray(new TreeElement[0]);
        }
        return EMPTY_ARRAY;
    }

}
