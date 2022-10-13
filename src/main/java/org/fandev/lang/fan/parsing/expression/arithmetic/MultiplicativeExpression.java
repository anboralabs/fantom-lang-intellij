package org.fandev.lang.fan.parsing.expression.arithmetic;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.logical.SeparatorRepeatExpression;


public class MultiplicativeExpression
        extends SeparatorRepeatExpression {
    private static final MultiplicativeExpression instance = new MultiplicativeExpression();

    public MultiplicativeExpression() {
        super(FanElementTypes.MULT_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.MULT, FanTokenTypes.DIV, FanTokenTypes.PERC}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return ParenExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}