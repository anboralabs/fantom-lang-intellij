package org.fandev.lang.fan.psi.api.types;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

public interface FanListTypeElement extends FanTypeElement {
    FanTypeDefinition getListType();

    FanClassTypeElement getTypeElement();
}
