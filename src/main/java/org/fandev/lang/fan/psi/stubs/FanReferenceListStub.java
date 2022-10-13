package org.fandev.lang.fan.psi.stubs;

import com.intellij.psi.stubs.StubElement;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanReferenceList;

public interface FanReferenceListStub extends StubElement<FanReferenceList> {
    String[] getBaseClasses();
}
