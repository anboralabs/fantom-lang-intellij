package org.fandev.lang.fan.highlighting;


import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanTokenTypes;


public class FanCommenter
        implements CodeDocumentationAwareCommenter {
    public String getLineCommentPrefix() {
        return "//";
    }

    public String getBlockCommentPrefix() {
        return "/*";
    }

    public String getBlockCommentSuffix() {
        return "*/";
    }

    public IElementType getLineCommentTokenType() {
        return FanTokenTypes.END_OF_LINE_COMMENT;
    }

    public IElementType getBlockCommentTokenType() {
        return FanTokenTypes.C_STYLE_COMMENT;
    }

    public IElementType getDocumentationCommentTokenType() {
        return FanTokenTypes.FANDOC_LINE_COMMENT;
    }

    public String getDocumentationCommentPrefix() {
        return null;
    }

    public String getDocumentationCommentLinePrefix() {
        return "**";
    }

    public String getDocumentationCommentSuffix() {
        return null;
    }

    public boolean isDocumentationComment(PsiComment element) {
        return false;
    }

    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}