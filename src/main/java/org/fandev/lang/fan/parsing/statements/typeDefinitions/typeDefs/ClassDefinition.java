package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.InheritanceClause;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks.ClassBlock;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class ClassDefinition {
    public static boolean parse(PsiBuilder builder) {
        Modifiers.parse(builder, DeclarationType.CLASS);

        if (!ParserUtils.getToken(builder, FanTokenTypes.CLASS_KEYWORD)) {
            builder.error(FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.CLASS_KEYWORD.toString()}));
            return false;
        }
        ParserUtils.removeNls(builder);


        if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
            builder.error(FanBundle.message("identifier.expected", new Object[0]));
            return false;
        }
        boolean isBuiltInType = (FanTokenTypes.FAN_SYS_TYPE == builder.getTokenType());

        PsiBuilder.Marker idMark = builder.mark();
        builder.advanceLexer();
        idMark.done(FanElementTypes.NAME_ELEMENT);
        ParserUtils.removeNls(builder);

        if (FanTokenTypes.COLON.equals(builder.getTokenType())) {
            InheritanceClause.parse(builder);
            ParserUtils.removeNls(builder);
        }
        return ClassBlock.parse(builder, isBuiltInType);
    }
}