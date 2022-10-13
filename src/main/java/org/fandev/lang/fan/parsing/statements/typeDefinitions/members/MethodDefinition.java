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
import org.fandev.lang.fan.parsing.types.TypeParameters;
import org.fandev.lang.fan.parsing.types.TypeSpec;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class MethodDefinition {
    public static boolean parse(PsiBuilder builder, boolean isBuiltInType) {
        PsiBuilder.Marker declMarker = builder.mark();

        Facet.parse(builder);

        TokenSet modifiers = Modifiers.parse(builder, DeclarationType.METHOD);
        boolean modifiersParsed = ((modifiers.getTypes()).length > 0);

        if (!TypeSpec.parse(builder)) {
            declMarker.error(FanBundle.message("type.expected", new Object[0]));
            return false;
        }

        if (!ParserUtils.parseName(builder)) {
            declMarker.drop();
            return false;
        }

        if (FanElementTypes.TYPE_PARAMETER_LIST != TypeParameters.parse(builder)) {

            declMarker.error(FanBundle.message("type.expected", new Object[0]));
            return false;
        }
        if (FanTokenTypes.LBRACE.equals(builder.getTokenType())) {
            Block.parse(builder, FanElementTypes.METHOD_BODY);
            declMarker.done(FanElementTypes.METHOD_DEFINITION);
            ParserUtils.removeNls(builder);
            return true;
        }

        if ((modifiersParsed && (modifiers.contains(FanTokenTypes.ABSTRACT_KEYWORD) || modifiers.contains(FanTokenTypes.NATIVE_KEYWORD))) || isBuiltInType) {
            declMarker.done(FanElementTypes.METHOD_DEFINITION);
            ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
            return true;
        }
        declMarker.error(FanBundle.message("lcurly.expected", new Object[0]));
        return false;
    }
}