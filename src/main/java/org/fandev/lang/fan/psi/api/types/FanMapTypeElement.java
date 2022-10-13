package org.fandev.lang.fan.psi.api.types;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

public interface FanMapTypeElement extends FanTypeElement {
    FanTypeDefinition getMapType();
}
