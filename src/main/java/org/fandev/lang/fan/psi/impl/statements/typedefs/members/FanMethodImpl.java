package org.fandev.lang.fan.psi.impl.statements.typedefs.members;

import com.intellij.lang.ASTNode;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.stubs.IStubElementType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.stubs.FanMethodStub;
import org.jetbrains.annotations.NotNull;


public class FanMethodImpl
        extends FanMethodBaseImpl<FanMethodStub>
        implements FanMethod, StubBasedPsiElement<FanMethodStub> {
    public FanMethodImpl(FanMethodStub fanMethodStub, @NotNull IStubElementType iStubElementType) {
        super(fanMethodStub, iStubElementType);
    }

    public FanMethodImpl(ASTNode astNode) {
        super(astNode);
    }

    public boolean isConstructor() {
        return false;
    }
}