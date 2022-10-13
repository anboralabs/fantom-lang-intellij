package org.fandev.lang.fan.psi.stubs.impl;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
import org.fandev.lang.fan.psi.stubs.FanConstructorStub;


public class FanConstructorStubImpl
        extends FanSlotStubImpl<FanConstructor>
        implements FanConstructorStub {
    public FanConstructorStubImpl(StubElement parent, StringRef name, String[] facetNames) {
        super(parent, (IStubElementType) FanElementTypes.CTOR_DEFINITION, name, facetNames);
    }
}