package org.fandev.lang.fan.psi.impl.statements.expressions;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.expressions.FanTermExpression;


public class FanTermExpressionImpl
        extends FanExpressionImpl
        implements FanTermExpression {
    public FanTermExpressionImpl(ASTNode astNode) {
        super(astNode);
    }
}