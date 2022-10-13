package org.fandev.lang.fan.psi.impl.statements.expressions;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.expressions.FanThisReferenceExpression;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.utils.FanUtil;


public class FanThisReferenceExpressionImpl
        extends FanExpressionImpl
        implements FanThisReferenceExpression {
    public FanThisReferenceExpressionImpl(ASTNode astNode) {
        super(astNode);
    }

    public FanTypeDefinition getReferencedType() {
        return FanUtil.getContainingType(this);
    }
}