package org.fandev.lang.fan.parsing.expression.arithmetic;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.ExpressionParser;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class UnaryExpression
        implements ExpressionParser {
    public static TokenSet PREFIXES = TokenSet.create(new IElementType[]{FanTokenTypes.EXCL, FanTokenTypes.PLUS, FanTokenTypes.MINUS, FanTokenTypes.TILDE, FanTokenTypes.AND, FanTokenTypes.PLUSPLUS, FanTokenTypes.MINUSMINUS});
    private static TokenSet POSTFIXES = TokenSet.create(new IElementType[]{FanTokenTypes.PLUSPLUS, FanTokenTypes.MINUSMINUS});
    private static UnaryExpression instance = new UnaryExpression();

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        boolean res = parsePrefixExpression(builder, stopper, instance);
        if (!res) {
            PsiBuilder.Marker marker = builder.mark();
            TokenSet newStopper = TokenSet.orSet(new TokenSet[]{stopper, POSTFIXES});
            res = TermExpression.parse(builder, newStopper);
            if (POSTFIXES.contains(builder.getTokenType())) {
                builder.advanceLexer();
                marker.done(FanElementTypes.POSTFIX_EXPR);
            } else {
                marker.done(FanElementTypes.UNARY_EXPR);
            }
        }
        return res;
    }

    public static boolean parsePrefixExpression(PsiBuilder builder, TokenSet stopper, ExpressionParser nextParser) {
        if (PREFIXES.contains(builder.getTokenType())) {
            PsiBuilder.Marker marker = builder.mark();
            ParserUtils.advanceNoNls(builder);
            nextParser.innerParse(builder, stopper);
            marker.done(FanElementTypes.PREFIX_EXPR);
            return true;
        }
        return false;
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return ParenExpression.parse(builder, stopper);
    }
}