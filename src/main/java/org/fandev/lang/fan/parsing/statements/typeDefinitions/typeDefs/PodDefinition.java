package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks.PodBlock;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class PodDefinition {
    public static boolean parse(PsiBuilder builder) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.POD_KEYWORD)) {
            builder.error(FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.POD_KEYWORD.toString()}));
            return false;
        }
        ParserUtils.removeNls(builder);


        if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
            builder.error(FanBundle.message("identifier.expected", new Object[0]));
            return false;
        }
        PsiBuilder.Marker idMark = builder.mark();
        builder.advanceLexer();
        idMark.done(FanElementTypes.NAME_ELEMENT);
        ParserUtils.removeNls(builder);

        return PodBlock.parse(builder);
    }
}