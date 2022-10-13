package org.fandev.lang.fan.psi.api.types;

import org.jetbrains.annotations.NotNull;

public interface FanClassTypeElement extends FanTypeElement {
    @NotNull
    FanCodeReferenceElement getReferenceElement();
}
