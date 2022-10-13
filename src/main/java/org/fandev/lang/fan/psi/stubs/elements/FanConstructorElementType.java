package org.fandev.lang.fan.psi.stubs.elements;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanConstructorImpl;
import org.fandev.lang.fan.psi.stubs.FanConstructorStub;
import org.fandev.lang.fan.psi.stubs.impl.FanConstructorStubImpl;
import org.jetbrains.annotations.NotNull;

public class FanConstructorElementType
        extends FanSlotElementType<FanConstructor, FanConstructorStub> {
    public FanConstructorElementType(@NotNull String debugName) {
        super(debugName);
    }

    public FanConstructor createPsi(FanConstructorStub stub) {
        return new FanConstructorImpl(stub, (IStubElementType) FanElementTypes.CTOR_DEFINITION);
    }

    protected FanConstructorStub createStubImpl(StubElement element, StringRef name, String[] facets) {
        return new FanConstructorStubImpl(element, name, facets);
    }
}