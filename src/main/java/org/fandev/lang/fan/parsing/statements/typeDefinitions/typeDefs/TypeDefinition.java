package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class TypeDefinition {
    public static boolean parse(PsiBuilder builder) {
        boolean res;
        PsiBuilder.Marker tdMarker = builder.mark();
        Facet.parse(builder);
        ParserUtils.removeNls(builder);
        if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.CLASS_KEYWORD, new IElementType[]{FanTokenTypes.LBRACE})) {
            res = ClassDefinition.parse(builder);
            tdMarker.done(FanElementTypes.CLASS_DEFINITION);
        } else if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.POD_KEYWORD, new IElementType[]{FanTokenTypes.LBRACE})) {

            res = PodDefinition.parse(builder);
            tdMarker.done(FanElementTypes.POD_DEFINITION);
        } else if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.ENUM_KEYWORD, new IElementType[]{FanTokenTypes.LBRACE})) {
            res = EnumDefinition.parse(builder);
            tdMarker.done(FanElementTypes.ENUM_DEFINITION);
        } else if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.MIXIN_KEYWORD, new IElementType[]{FanTokenTypes.LBRACE})) {
            res = MixinDefinition.parse(builder);
            tdMarker.done(FanElementTypes.MIXIN_DEFINITION);
        } else {
            res = false;
            tdMarker.error(FanBundle.message("typedef.expected", new Object[0]));
        }
        ParserUtils.removeNls(builder);
        return res;
    }
}