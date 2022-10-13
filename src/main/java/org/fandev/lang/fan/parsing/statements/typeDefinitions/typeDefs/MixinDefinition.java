package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.InheritanceClause;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks.MixinBlock;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class MixinDefinition {
    public static boolean parse(PsiBuilder builder) {
        TokenSet modifers = Modifiers.parse(builder, DeclarationType.MIXIN);
        ParserUtils.removeNls(builder);
        if (!ParserUtils.getToken(builder, FanTokenTypes.MIXIN_KEYWORD)) {
            builder.error(FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.MIXIN_KEYWORD.toString()}));
            return false;
        }

        boolean isBuiltInType = (FanTokenTypes.FAN_SYS_TYPE == builder.getTokenType());
        if (!ParserUtils.parseName(builder)) {
            return false;
        }
        ParserUtils.removeNls(builder);


        if (modifers.contains(FanTokenTypes.CONST_KEYWORD) && !isBuiltInType) {

            String tokenText = builder.getTokenText();
            builder.error(FanBundle.message("illegal.modifier", new Object[]{tokenText, DeclarationType.MIXIN}));
        }

        if (FanTokenTypes.COLON.equals(builder.getTokenType())) {
            InheritanceClause.parse(builder);
            ParserUtils.removeNls(builder);
        }

        return MixinBlock.parse(builder, isBuiltInType);
    }
}