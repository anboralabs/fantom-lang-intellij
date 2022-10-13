package org.fandev.lang.fan.psi.api.statements.expressions;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.jetbrains.annotations.Nullable;

public interface FanTypeReferenceExpression {
    @Nullable
    FanTypeDefinition getReferencedType();
}