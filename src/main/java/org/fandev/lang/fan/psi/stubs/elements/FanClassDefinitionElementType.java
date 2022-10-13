package org.fandev.lang.fan.psi.stubs.elements;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanClassDefinition;
import org.fandev.lang.fan.psi.impl.statements.typedefs.FanClassDefinitionImpl;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;

public class FanClassDefinitionElementType
        extends FanTypeDefinitionElementType<FanClassDefinition> {
    public FanClassDefinitionElementType() {
        super("class definition");
    }

    public FanClassDefinition createPsi(FanTypeDefinitionStub stub) {
        return new FanClassDefinitionImpl(stub);
    }
}