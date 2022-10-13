package org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.EnumValueDefinition;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.SlotDefinition;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class EnumBlock {
    public static boolean parse(PsiBuilder builder, boolean isBuiltinType) {
        ParserUtils.removeNls(builder);
        PsiBuilder.Marker blockMark = builder.mark();
        if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACE)) {
            blockMark.error(FanBundle.message("lcurly.expected", new Object[0]));
            return false;
        }
        ParserUtils.removeNls(builder);

        while (EnumValueDefinition.parse(builder) &&
                ParserUtils.getToken(builder, FanTokenTypes.COMMA)) {

            eatCommas(builder);
        }
        eatCommas(builder);
        ParserUtils.getToken(builder, FanTokenTypes.SEMICOLON);
        ParserUtils.removeNls(builder);

        while (!builder.eof() && builder.getTokenType() != FanTokenTypes.RBRACE &&
                SlotDefinition.parse(builder, DeclarationType.ENUM, isBuiltinType)) {

            ParserUtils.removeNls(builder);
        }
        if (ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]))) {
            blockMark.done(FanElementTypes.ENUM_BODY);
            return true;
        }
        ParserUtils.cleanAfterErrorInBlock(builder);
        blockMark.done(FanElementTypes.ENUM_BODY);
        return false;
    }

    private static void eatCommas(PsiBuilder builder) {
        ParserUtils.removeNls(builder);
        while (FanTokenTypes.COMMA == builder.getTokenType()) {
            builder.error(FanBundle.message("enum.value.expected", new Object[0]));
            ParserUtils.getToken(builder, FanTokenTypes.COMMA);
            ParserUtils.removeNls(builder);
        }
    }
}