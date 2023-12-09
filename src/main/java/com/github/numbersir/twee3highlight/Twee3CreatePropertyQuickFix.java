package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3ElementFactory;
import com.github.numbersir.twee3highlight.psi.Twee3File;
import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

class Twee3CreatePropertyQuickFix extends BaseIntentionAction {

    private final String key;

    Twee3CreatePropertyQuickFix(String key) {
        this.key = key;
    }

    @NotNull
    @Override
    public String getText() {
        return "Create property '" + key + "'";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "Create property";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(Twee3FileType.INSTANCE, GlobalSearchScope.allScope(project));
            if (virtualFiles.size() == 1) {
                createProperty(project, virtualFiles.iterator().next());
            } else {
                final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(Twee3FileType.INSTANCE);
                descriptor.setRoots(ProjectUtil.guessProjectDir(project));
                final VirtualFile file1 = FileChooser.chooseFile(descriptor, project, null);
                if (file1 != null) {
                    createProperty(project, file1);
                }
            }
        });
    }

    private void createProperty(final Project project, final VirtualFile file) {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            Twee3File twee3File = (Twee3File) PsiManager.getInstance(project).findFile(file);
            assert twee3File != null;
            ASTNode lastChildNode = twee3File.getNode().getLastChildNode();
            // TODO: Add another check for CRLF
            if (lastChildNode != null/* && !lastChildNode.getElementType().equals(Twee3Types.CRLF)*/) {
                twee3File.getNode().addChild(Twee3ElementFactory.createCRLF(project).getNode());
            }
            // IMPORTANT: change spaces to escaped spaces or the new node will only have the first word for the key
            Twee3Property property = Twee3ElementFactory.createProperty(project, key.replaceAll(" ", "\\\\ "), "");
            twee3File.getNode().addChild(property.getNode());
            ((Navigatable) property.getLastChild().getNavigationElement()).navigate(true);
            Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
            assert editor != null;
            editor.getCaretModel().moveCaretRelatively(2, 0, false, false, false);
        });
    }

}
