package org.fandev.lang.fan.parsing.expression.arithmetic;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.statements.Statement;
import org.fandev.lang.fan.parsing.types.TypeSpec;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class ParenExpression {
    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        boolean res;
        PsiBuilder.Marker marker = builder.mark();

        if (ParserUtils.getToken(builder, FanTokenTypes.LPAR)) {
            ParserUtils.removeNls(builder);

            res = parseCastExpression(builder, stopper);
            if (res) {
                marker.done(FanElementTypes.CAST_EXPR);
            } else {
                marker.rollbackTo();
                marker = builder.mark();

                ParserUtils.advanceNoNls(builder);
                res = Expression.parseExpr(builder, Statement.RPAR_STOPPER, FanElementTypes.EXPRESSION);
                ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
                if (res) {
                    res = TermExpression.parseTermChainLoop(builder, stopper);
                }
                marker.done(FanElementTypes.GROUPED_EXPR);
            }
        } else {
            marker.drop();
            res = UnaryExpression.parse(builder, stopper);
        }
        return res;
    }

    private static boolean parseCastExpression(PsiBuilder builder, TokenSet stopper) {
        if (TypeSpec.parse(builder) &&
                ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]))) {
            return parse(builder, stopper);
        }

        return false;
    }
}