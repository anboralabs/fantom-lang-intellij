package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
import org.fandev.lang.fan.parsing.statements.Block;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.ReferenceElement;
import org.fandev.lang.fan.parsing.types.TypeParameters;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class ConstructorDefinition {
    public static boolean parse(PsiBuilder builder, boolean isBuiltInType) {
        PsiBuilder.Marker constructorMarker = builder.mark();

        Facet.parse(builder);

        TokenSet modifiers = Modifiers.parse(builder, DeclarationType.CONSTRUCTOR);

        if (!FanTokenTypes.NEW_KEYWORD.equals(builder.getTokenType())) {
            constructorMarker.error("Constructor should have <modifiers> new <id> ()");
            return false;
        }

        ParserUtils.advanceNoNls(builder);

        if (!ParserUtils.parseName(builder)) {
            constructorMarker.drop();
            return false;
        }
        ParserUtils.removeNls(builder);


        if (FanElementTypes.TYPE_PARAMETER_LIST != TypeParameters.parse(builder)) {
            builder.error(FanBundle.message("params.expected", new Object[0]));
            constructorMarker.drop();
            return false;
        }


        parseCtorChain(builder);

        ParserUtils.removeNls(builder);

        if (FanTokenTypes.LBRACE.equals(builder.getTokenType())) {
            Block.parse(builder, FanElementTypes.METHOD_BODY);
            constructorMarker.done(FanElementTypes.CTOR_DEFINITION);
            ParserUtils.removeNls(builder);
            return true;
        }
        if (isBuiltInType) {
            constructorMarker.done(FanElementTypes.CTOR_DEFINITION);
            ParserUtils.removeNls(builder);
            return true;
        }
        constructorMarker.error(FanBundle.message("lcurly.expected", new Object[0]));
        return false;
    }


    private static boolean parseCtorChain(PsiBuilder builder) {
        if (FanTokenTypes.COLON == builder.getTokenType()) {
            PsiBuilder.Marker ctorChainMarker = builder.mark();

            ParserUtils.advanceNoNls(builder);
            if (FanTokenTypes.SUPER_KEYWORD == builder.getTokenType() || FanTokenTypes.THIS_KEYWORD == builder.getTokenType()) {

                builder.advanceLexer();

                if (FanTokenTypes.DOT == builder.getTokenType()) {
                    builder.advanceLexer();
                    if (!ReferenceElement.parseReferenceElement(builder)) {
                        ctorChainMarker.error(FanBundle.message("identifier.expected", new Object[0]));
                    }
                }

                if (Arguments.parse(builder)) {
                    ctorChainMarker.done(FanElementTypes.CTOR_CHAIN);
                    return true;
                }
                ctorChainMarker.error(FanBundle.message("argument.expected", new Object[0]));
            } else {

                ctorChainMarker.error(FanBundle.message("super.or.this.expected", new Object[0]));
            }
        }
        return false;
    }
}