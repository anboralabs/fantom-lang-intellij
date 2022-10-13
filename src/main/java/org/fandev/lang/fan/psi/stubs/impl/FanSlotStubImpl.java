package org.fandev.lang.fan.psi.stubs.impl;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
import org.fandev.lang.fan.psi.stubs.FanSlotStub;


public abstract class FanSlotStubImpl<T extends FanSlot>
        extends StubBase<T>
        implements FanSlotStub<T> {
    protected final StringRef myName;
    protected final String[] facetNames;

    public FanSlotStubImpl(StubElement element, IStubElementType type, StringRef name, String[] facetNames) {
        super(element, type);
        this.myName = name;
        this.facetNames = facetNames;
    }

    public String getName() {
        return this.myName.getString();
    }

    public String[] getFacetNames() {
        return this.facetNames;
    }
}
