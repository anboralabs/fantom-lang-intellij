package org.fandev.lang.fan.psi.stubs.elements;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanFieldImpl;
import org.fandev.lang.fan.psi.stubs.FanFieldStub;
import org.fandev.lang.fan.psi.stubs.impl.FanFieldStubImpl;
import org.jetbrains.annotations.NotNull;

public class FanFieldElementType
        extends FanSlotElementType<FanField, FanFieldStub> {
    public FanFieldElementType(@NotNull String debugName) {
        super(debugName);
    }

    public FanField createPsi(FanFieldStub stub) {
        return new FanFieldImpl(stub, (IStubElementType) FanElementTypes.FIELD_DEFINITION);
    }

    protected FanFieldStub createStubImpl(StubElement element, StringRef name, String[] facets) {
        return new FanFieldStubImpl(element, name, facets);
    }
}
