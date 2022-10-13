package org.fandev.lang.fan.psi.stubs.elements;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanPodDefinition;
import org.fandev.lang.fan.psi.impl.statements.typedefs.FanPodDefinitionImpl;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;


public class FanPodDefinitionElementType
        extends FanTypeDefinitionElementType<FanPodDefinition> {
    public FanPodDefinitionElementType() {
        super("pod definition");
    }

    public FanPodDefinition createPsi(FanTypeDefinitionStub stub) {
        return new FanPodDefinitionImpl(stub);
    }
}