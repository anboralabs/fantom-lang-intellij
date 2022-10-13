package org.fandev.lang.fan.psi.impl.statements.expressions;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.expressions.FanSuperReferenceExpression;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.utils.FanUtil;
import org.jetbrains.annotations.Nullable;


public class FanSuperReferenceExpressionImpl
        extends FanExpressionImpl
        implements FanSuperReferenceExpression {
    public FanSuperReferenceExpressionImpl(ASTNode astNode) {
        super(astNode);
    }

    @Nullable
    public FanTypeDefinition getReferencedType() {
        FanTypeDefinition thisTypeDefinition = FanUtil.getContainingType(this);
        if (thisTypeDefinition != null) {
            return thisTypeDefinition.getSuperType();
        }
        return null;
    }
}