package org.fandev.lang.fan.parsing.expression;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.logical.LogicalOrExpression;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class Expression {
    public static boolean parseExpr(PsiBuilder builder, TokenSet stopper, IElementType expressionType) {
        PsiBuilder.Marker m = builder.mark();
        TokenSet newStopper = TokenSet.orSet(new TokenSet[]{stopper, FanTokenTypes.ASSIGN_OP, TokenSet.create(new IElementType[]{FanTokenTypes.QUEST})});
        boolean res = LogicalOrExpression.parse(builder, newStopper);
        if (!res) {
            m.drop();
            return false;
        }
        if (ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS}) == FanTokenTypes.QUEST) {
            PsiBuilder.Marker condExprMarker = m.precede();
            PsiBuilder.Marker exprWrapper = condExprMarker.precede();
            m.done(FanElementTypes.CONDITION_EXPR);
            ParserUtils.removeNls(builder);
            ParserUtils.advanceNoNls(builder);
            res = parseExpr(builder, TokenSet.orSet(new TokenSet[]{stopper, TokenSet.create(new IElementType[]{FanTokenTypes.COLON})}), FanElementTypes.COND_TRUE_BLOCK);
            IElementType firstAfter = ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS});
            if (res && firstAfter == FanTokenTypes.COLON) {
                ParserUtils.removeNls(builder);
                ParserUtils.advanceNoNls(builder);
                PsiBuilder.Marker falseBlock = builder.mark();
                res = parseExpr(builder, stopper, FanElementTypes.COND_FALSE_BLOCK);
                falseBlock.done(FanElementTypes.COND_FALSE_BLOCK);
            }
            condExprMarker.done(FanElementTypes.COND_EXPR);
            exprWrapper.done(expressionType);
        } else if (FanTokenTypes.ASSIGN_OP.contains(builder.getTokenType())) {
            PsiBuilder.Marker assignExprMarker = m.precede();
            PsiBuilder.Marker exprWrapper = assignExprMarker.precede();
            m.done(FanElementTypes.ASSIGN_LEFT_EXPR);
            ParserUtils.advanceNoNls(builder);
            res = parseExpr(builder, stopper, FanElementTypes.ASSIGN_RIGHT_EXPR);
            assignExprMarker.done(FanElementTypes.ASSIGN_EXPRESSION);
            exprWrapper.done(expressionType);
        } else {
            m.done(expressionType);
        }
        return res;
    }
}
