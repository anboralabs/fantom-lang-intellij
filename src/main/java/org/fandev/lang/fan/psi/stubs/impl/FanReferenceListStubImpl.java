package org.fandev.lang.fan.psi.stubs.impl;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanReferenceList;
import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;


public class FanReferenceListStubImpl
        extends StubBase<FanReferenceList>
        implements FanReferenceListStub {
    private final String[] myRefNames;

    public FanReferenceListStubImpl(StubElement parent, IStubElementType elementType, String[] refNames) {
        super(parent, elementType);
        this.myRefNames = refNames;
    }

    public String[] getBaseClasses() {
        return this.myRefNames;
    }
}