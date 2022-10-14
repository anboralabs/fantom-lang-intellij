package org.fandev.lang.fan.highlighting;


import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighterProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.fandev.lang.fan.FantomLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FanSyntaxHighlighterProvider
        implements SyntaxHighlighterProvider {
    public SyntaxHighlighter create(@NotNull FileType fileType, @Nullable Project project, @Nullable VirtualFile virtualFile) {
        Language lang = FantomLanguage.INSTANCE;
        return SyntaxHighlighterFactory.getSyntaxHighlighter(lang, project, virtualFile);
    }
}