package org.fandev.lang.fan;


import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import org.jetbrains.annotations.NotNull;


public abstract class FanStubElementType<S extends StubElement<?>, T extends PsiElement>
        extends IStubElementType<S, T> {
    public FanStubElementType(@NotNull String debugName) {
        super(debugName, FantomLanguage.INSTANCE);
    }

    public String getExternalId() {
        return "fan." + toString();
    }
}
