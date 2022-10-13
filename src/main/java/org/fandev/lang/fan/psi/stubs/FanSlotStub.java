package org.fandev.lang.fan.psi.stubs;

import com.intellij.psi.stubs.NamedStub;

public interface FanSlotStub<T extends org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot> extends NamedStub<T> {
    String[] getFacetNames();
}
