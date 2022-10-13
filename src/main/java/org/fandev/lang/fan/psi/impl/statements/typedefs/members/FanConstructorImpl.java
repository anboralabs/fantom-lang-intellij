package org.fandev.lang.fan.psi.impl.statements.typedefs.members;

import com.intellij.lang.ASTNode;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.stubs.IStubElementType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
import org.fandev.lang.fan.psi.stubs.FanConstructorStub;
import org.jetbrains.annotations.NotNull;


public class FanConstructorImpl
        extends FanMethodBaseImpl<FanConstructorStub>
        implements FanConstructor, StubBasedPsiElement<FanConstructorStub> {
    public FanConstructorImpl(FanConstructorStub fanMethodStub, @NotNull IStubElementType iStubElementType) {
        super(fanMethodStub, iStubElementType);
    }

    public FanConstructorImpl(ASTNode astNode) {
        super(astNode);
    }

    public boolean isConstructor() {
        return true;
    }

}