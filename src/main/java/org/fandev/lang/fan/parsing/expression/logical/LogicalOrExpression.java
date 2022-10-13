package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;


public class LogicalOrExpression
        extends SeparatorRepeatExpression {
    private static final LogicalOrExpression instance = new LogicalOrExpression();

    public LogicalOrExpression() {
        super(FanElementTypes.LOGICAL_OR_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.OROR}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return LogicalAndExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}