package org.fandev.lang.fan.psi.api.types;

import org.fandev.lang.fan.psi.api.statements.params.FanFormals;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

public interface FanFuncTypeElement extends FanTypeElement {
    FanFormals getFormals();

    FanTypeElement getReturnType();

    FanTypeDefinition getFuncType();
}
