package org.fandev.lang.fan.psi.api.statements.arguments;

import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.NotNull;

public interface FanArgument extends FanElement {
    int getIndex();

    @NotNull
    FanArgumentList getArgumentList();
}