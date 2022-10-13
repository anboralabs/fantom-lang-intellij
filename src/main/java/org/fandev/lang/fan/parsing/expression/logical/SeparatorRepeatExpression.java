package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.parsing.expression.ExpressionParser;
import org.fandev.lang.fan.parsing.expression.arithmetic.UnaryExpression;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public abstract class SeparatorRepeatExpression
        implements ExpressionParser {
    protected final IElementType expressionType;
    protected final TokenSet separators;
    protected final boolean checkPrefixExpression;

    protected SeparatorRepeatExpression(IElementType expressionType, TokenSet separators) {
        this.expressionType = expressionType;
        this.separators = separators;

        boolean needToCheckPrefix = false;
        IElementType[] prefixes = UnaryExpression.PREFIXES.getTypes();
        for (IElementType prefix : prefixes) {
            if (separators.contains(prefix)) {
                needToCheckPrefix = true;
                break;
            }
        }
        this.checkPrefixExpression = needToCheckPrefix;
    }

    protected boolean parseThis(PsiBuilder builder, TokenSet stopper) {
        if (stopper.contains(builder.getTokenType())) {
            return false;
        }
        PsiBuilder.Marker marker = builder.mark();
        TokenSet newStopper = TokenSet.orSet(new TokenSet[]{stopper, this.separators});
        if (lheParser(builder, newStopper)) {
            if (this.separators.contains(builder.getTokenType())) {
                IElementType separator = builder.getTokenType();
                ParserUtils.advanceNoNls(builder);
                if (!rheParse(builder, newStopper, separator)) {
                    marker.error(FanBundle.message("expression.expected", new Object[0]));
                    return false;
                }
                PsiBuilder.Marker newMarker = marker.precede();
                marker.done(this.expressionType);
                if (this.separators.contains(builder.getTokenType())) {
                    subParse(builder, newMarker, newStopper);
                } else {
                    newMarker.drop();
                }
            } else {
                marker.drop();
            }
            return true;
        }
        marker.drop();
        return false;
    }


    protected boolean lheParser(PsiBuilder builder, TokenSet newStopper) {
        if (this.checkPrefixExpression &&
                this.separators.contains(builder.getTokenType()) && UnaryExpression.parsePrefixExpression(builder, newStopper, this)) {
            return true;
        }

        return innerParse(builder, newStopper);
    }

    protected boolean rheParse(PsiBuilder builder, TokenSet newStopper, IElementType separator) {
        if (this.checkPrefixExpression &&
                this.separators.contains(builder.getTokenType()) && UnaryExpression.parsePrefixExpression(builder, newStopper, this)) {
            return true;
        }

        return innerParse(builder, newStopper);
    }

    protected void subParse(PsiBuilder builder, PsiBuilder.Marker marker, TokenSet stopper) {
        IElementType separator = builder.getTokenType();
        ParserUtils.advanceNoNls(builder);
        if (!rheParse(builder, stopper, separator)) {
            builder.error(FanBundle.message("expression.expected", new Object[0]));
            marker.drop();
            return;
        }
        PsiBuilder.Marker newMarker = marker.precede();
        marker.done(this.expressionType);
        if (this.separators.contains(builder.getTokenType())) {
            subParse(builder, newMarker, stopper);
        } else {
            newMarker.drop();
        }
    }
}
