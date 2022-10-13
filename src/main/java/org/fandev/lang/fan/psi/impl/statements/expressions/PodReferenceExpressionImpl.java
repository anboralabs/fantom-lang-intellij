package org.fandev.lang.fan.psi.impl.statements.expressions;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.expressions.PodReferenceExpression;


public class PodReferenceExpressionImpl
        extends FanExpressionImpl
        implements PodReferenceExpression {
    public PodReferenceExpressionImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getPodName() {
        return getText();
    }
}