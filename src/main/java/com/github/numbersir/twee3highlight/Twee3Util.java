package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3File;
import com.github.numbersir.twee3highlight.psi.Twee3Property;
import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jsoup.internal.StringUtil;

import java.util.*;

public class Twee3Util {
    /**
     * Searches the entire project for Twee3 language files with instances of the Twee3 property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
    public static List<Twee3Property> findProperties(Project project, String key) {
        List<Twee3Property> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(Twee3FileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            Twee3File twee3File = (Twee3File) PsiManager.getInstance(project).findFile(virtualFile);
            if (twee3File != null) {
                Twee3Property[] properties = PsiTreeUtil.getChildrenOfType(twee3File, Twee3Property.class);
                if (properties != null) {
                    for (Twee3Property property : properties) {
                        if (key.equals(property.getKey())) {
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<Twee3Property> findProperties(Project project) {
        List<Twee3Property> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(Twee3FileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            Twee3File twee3File = (Twee3File) PsiManager.getInstance(project).findFile(virtualFile);
            if (twee3File != null) {
                Twee3Property[] properties = PsiTreeUtil.getChildrenOfType(twee3File, Twee3Property.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }

    /**
     * Attempts to collect any comment elements above the Twee3 key/value pair.
     */
    public static @NotNull String findDocumentationComment(Twee3Property property) {
        List<String> result = new LinkedList<>();
        PsiElement element = property.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[!# ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result), "\n ");
    }
}
