package org.fandev.lang.fan.psi.stubs;

import com.intellij.psi.stubs.NamedStub;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

public interface FanTypeDefinitionStub extends NamedStub<FanTypeDefinition> {
    String getPodName();
}
