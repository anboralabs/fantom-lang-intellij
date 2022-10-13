package org.fandev.lang.fan.psi.impl.statements.expressions;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.expressions.FanUnaryExpression;


public class FanUnaryExpressionImpl
        extends FanExpressionImpl
        implements FanUnaryExpression {
    public FanUnaryExpressionImpl(ASTNode astNode) {
        super(astNode);
    }
}
