package org.fandev.lang.fan.psi.stubs.elements;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanMixinDefinition;
import org.fandev.lang.fan.psi.impl.statements.typedefs.FanMixinDefinitionImpl;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;


public class FanMixinDefinitionElementType
        extends FanTypeDefinitionElementType<FanMixinDefinition> {
    public FanMixinDefinitionElementType() {
        super("mixin definition");
    }

    public FanMixinDefinition createPsi(FanTypeDefinitionStub stub) {
        return new FanMixinDefinitionImpl(stub);
    }
}