package org.fandev.lang.fan.parsing.expression.arithmetic;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.logical.SeparatorRepeatExpression;


public class AdditiveExpression
        extends SeparatorRepeatExpression {
    private static final AdditiveExpression instance = new AdditiveExpression();

    public AdditiveExpression() {
        super(FanElementTypes.ADD_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.PLUS, FanTokenTypes.MINUS}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return MultiplicativeExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}