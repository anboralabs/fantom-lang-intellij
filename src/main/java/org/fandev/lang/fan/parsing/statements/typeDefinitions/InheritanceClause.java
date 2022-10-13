package org.fandev.lang.fan.parsing.statements.typeDefinitions;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class InheritanceClause {
    public static IElementType parse(PsiBuilder builder) {
        PsiBuilder.Marker sccMarker = builder.mark();

        if (!ParserUtils.getToken(builder, FanTokenTypes.COLON)) {
            sccMarker.rollbackTo();
            return FanElementTypes.NONE;
        }

        do {
            ParserUtils.removeNls(builder);
            if (!ReferenceElement.parseReferenceElement(builder)) {
                sccMarker.rollbackTo();
                return FanElementTypes.WRONGWAY;
            }
            ParserUtils.removeNls(builder);
        } while (ParserUtils.getToken(builder, FanTokenTypes.COMMA));

        sccMarker.done(FanElementTypes.INHERITANCE_CLAUSE);
        return FanElementTypes.INHERITANCE_CLAUSE;
    }
}