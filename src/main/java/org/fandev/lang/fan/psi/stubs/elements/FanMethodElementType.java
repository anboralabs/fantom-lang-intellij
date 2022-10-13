package org.fandev.lang.fan.psi.stubs.elements;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanMethodImpl;
import org.fandev.lang.fan.psi.stubs.FanMethodStub;
import org.fandev.lang.fan.psi.stubs.impl.FanMethodStubImpl;
import org.jetbrains.annotations.NotNull;

public class FanMethodElementType
        extends FanSlotElementType<FanMethod, FanMethodStub> {
    public FanMethodElementType(@NotNull String debugName) {
        super(debugName);
    }

    public FanMethod createPsi(FanMethodStub stub) {
        return new FanMethodImpl(stub, (IStubElementType) FanElementTypes.METHOD_DEFINITION);
    }

    protected FanMethodStubImpl createStubImpl(StubElement element, StringRef name, String[] facets) {
        return new FanMethodStubImpl(element, name, facets);
    }
}