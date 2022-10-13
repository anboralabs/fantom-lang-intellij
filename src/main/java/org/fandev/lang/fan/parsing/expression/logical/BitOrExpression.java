package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.arithmetic.TermExpression;
import org.fandev.lang.fan.parsing.expression.arithmetic.UnaryExpression;


public class BitOrExpression
        extends SeparatorRepeatExpression {
    private static final BitOrExpression instance = new BitOrExpression();

    public BitOrExpression() {
        super(FanElementTypes.BIT_OR_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.XOR, FanTokenTypes.OR}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        if (FanTokenTypes.XOR == builder.getTokenType()) {
            return UnaryExpression.parse(builder, stopper);
        }
        if (FanTokenTypes.OR == builder.getTokenType()) {
            return TermExpression.parse(builder, stopper);
        }
        return BitAndExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}