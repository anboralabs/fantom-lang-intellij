package org.fandev.lang.fan.psi.api.statements.arguments;

import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.NotNull;

public interface FanArgumentList extends FanElement {
    FanArgument[] getArguments();

    int indexOf(@NotNull FanArgument paramFanArgument);
}