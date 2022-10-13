package org.fandev.lang.fan.psi.stubs.elements;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.impl.statements.typedefs.FanEnumDefinitionImpl;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;


public class FanEnumDefinitionElementType
        extends FanTypeDefinitionElementType<FanEnumDefinition> {
    public FanEnumDefinitionElementType() {
        super("enum definition");
    }

    public FanEnumDefinition createPsi(FanTypeDefinitionStub stub) {
        return new FanEnumDefinitionImpl(stub);
    }
}