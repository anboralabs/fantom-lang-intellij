package org.fandev.lang.fan.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.NotNull;


public class FanStubElementImpl<T extends StubElement>
        extends StubBasedPsiElementBase<T>
        implements FanElement {
    public FanStubElementImpl(T t, @NotNull IStubElementType iStubElementType) {
        super(t, iStubElementType);
    }

    public FanStubElementImpl(ASTNode astNode) {
        super(astNode);
    }
}