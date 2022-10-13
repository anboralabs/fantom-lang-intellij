package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;


public class LogicalAndExpression
        extends SeparatorRepeatExpression {
    private static final LogicalAndExpression instance = new LogicalAndExpression();

    public LogicalAndExpression() {
        super(FanElementTypes.LOGICAL_AND_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.ANDAND}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return EqualityExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}