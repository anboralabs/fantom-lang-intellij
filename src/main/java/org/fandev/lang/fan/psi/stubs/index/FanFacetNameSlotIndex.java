package org.fandev.lang.fan.psi.stubs.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;


public class FanFacetNameSlotIndex
        extends StringStubIndexExtension<FanSlot> {
    public static final StubIndexKey<String, FanSlot> KEY = StubIndexKey.createIndexKey("fan.facet.slot");


    public StubIndexKey<String, FanSlot> getKey() {
        return KEY;
    }
}