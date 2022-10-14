package org.fandev.lang.fan.highlighting;


import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanTokenTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FanPairedBraceMatcher
        implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(FanTokenTypes.LBRACE, FanTokenTypes.RBRACE, true),
            new BracePair(FanTokenTypes.LPAR, FanTokenTypes.RPAR, false),
            new BracePair(FanTokenTypes.LBRACKET, FanTokenTypes.RBRACKET, false)
    };

    public BracePair[] getPairs() {
        return PAIRS;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
