package org.fandev.lang.fan.parsing.auxiliary.facets;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class Facet {
    public static boolean parse(PsiBuilder builder) {
        ParserUtils.removeNls(builder);
        while (FanTokenTypes.AT.equals(builder.getTokenType())) {
            PsiBuilder.Marker facetMarker = builder.mark();
            builder.advanceLexer();

            if (!ParserUtils.parseName(builder)) {
                facetMarker.drop();
                return false;
            }
            if (FanTokenTypes.EQ.equals(builder.getTokenType())) {
                ParserUtils.advanceNoNls(builder);
                Expression.parseExpr(builder, FanTokenTypes.EOS, FanElementTypes.FACET_VALUE);
            }
            facetMarker.done(FanElementTypes.FACET);
            ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
        }
        return true;
    }
}