package org.fandev.lang.fan.psi.stubs.impl;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.stubs.FanMethodStub;


public class FanMethodStubImpl
        extends FanSlotStubImpl<FanMethod>
        implements FanMethodStub {
    public FanMethodStubImpl(StubElement parent, StringRef name, String[] facetNames) {
        super(parent, (IStubElementType) FanElementTypes.METHOD_DEFINITION, name, facetNames);
    }
}
