package org.fandev.lang.fan.psi.impl.synthetic;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.light.LightIdentifier;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;


public class FanLightIdentifier
        extends LightIdentifier {
    private PsiFile myFile;
    private TextRange myRange;

    public FanLightIdentifier(PsiManager manager, PsiFile file, TextRange range) {
        super(manager, file.getText().substring(range.getStartOffset(), range.getEndOffset()));
        this.myFile = file;
        this.myRange = range;
    }

    public FanLightIdentifier(PsiManager manager, PsiFile file, String text) {
        super(manager, text);
        this.myFile = file;
        this.myRange = null;
    }

    public TextRange getTextRange() {
        return this.myRange;
    }

    public PsiFile getContainingFile() {
        return this.myFile;
    }


    public PsiElement replace(@NotNull PsiElement newElement) throws IncorrectOperationException {
        return super.replace(newElement);
    }
}