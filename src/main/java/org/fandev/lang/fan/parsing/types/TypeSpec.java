package org.fandev.lang.fan.parsing.types;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class TypeSpec {
    public static boolean parse(PsiBuilder builder) {
        ParserUtils.removeNls(builder);
        boolean res = (parseType(builder, false) != TypeType.NONE);
        return res;
    }


    private static TypeType parseFunctionOrSimpleType(PsiBuilder builder, boolean forLiteral) {
        if (FanTokenTypes.OR == builder.getTokenType()) {
            return FuncTypeSpec.parseFuncType(builder, forLiteral);
        }
        return SimpleTypeSpec.parseSimpleType(builder, forLiteral);
    }


    public static TypeType parseType(PsiBuilder builder, boolean forLiteral) {
        boolean bracketFlag = false;


        PsiBuilder.Marker typeMarker = builder.mark();

        boolean forLiteralInnerType = forLiteral;
        if (FanTokenTypes.LBRACKET == builder.getTokenType()) {
            bracketFlag = true;
            builder.advanceLexer();

            forLiteralInnerType = false;
        }

        TypeType result = parseFunctionOrSimpleType(builder, forLiteralInnerType);
        if (result == TypeType.NONE) {
            typeMarker.rollbackTo();
            return result;
        }

        if (FanTokenTypes.COLON != builder.getTokenType()) {
            if (bracketFlag) {

                if (forLiteral) {

                    typeMarker.rollbackTo();
                    return TypeType.NONE;
                }
                builder.error(FanBundle.message("colon.expected", new Object[0]));

                if (ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS}) == FanTokenTypes.RBRACKET) {
                    ParserUtils.removeNls(builder);
                    ParserUtils.advanceNoNls(builder);
                }
            }
            typeMarker.drop();
            return result;
        }
        result = TypeType.MAP;
        builder.advanceLexer();
        TypeType valueType = parseFunctionOrSimpleType(builder, false);
        if (valueType != TypeType.NONE) {


            if (!FanTokenTypes.RBRACKET.equals(builder.getTokenType()) && bracketFlag) {
                typeMarker.error(FanBundle.message("rbrack.expected", new Object[0]));
                return result;
            }
            if (FanTokenTypes.RBRACKET.equals(builder.getTokenType()) && !bracketFlag) {
                typeMarker.error(FanBundle.message("rbrack.no.lbrack", new Object[0]));
                return result;
            }
            if (FanTokenTypes.RBRACKET.equals(builder.getTokenType()) && bracketFlag) {
                builder.advanceLexer();
            }
            if (FanTokenTypes.LBRACKET == builder.getTokenType() || FanTokenTypes.QUEST == builder.getTokenType()) {
                PsiBuilder.Marker arrMarker = typeMarker;
                typeMarker = arrMarker.precede();
                result = endOfTypeParse(builder, arrMarker, forLiteral, TypeType.MAP);
            }
            typeMarker.done(FanElementTypes.MAP_TYPE);
            return result;
        }
        if (bracketFlag) ;


        typeMarker.error(FanBundle.message("type.expected", new Object[0]));
        return result;
    }


    static TypeType endOfTypeParse(PsiBuilder builder, PsiBuilder.Marker marker, boolean forLiteral, TypeType defaultType) {
        PsiBuilder.Marker rollTo = builder.mark();
        if (FanTokenTypes.QUEST == builder.getTokenType()) {

            int offset = builder.getCurrentOffset();
            if (offset > 0) {
                char c = builder.getOriginalText().charAt(offset - 1);
                if (!Character.isWhitespace(c)) {
                    builder.advanceLexer();
                    rollTo.done(FanElementTypes.NULLABLE_TYPE);
                    rollTo = builder.mark();
                }
            }
        }
        if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACKET)) {
            rollTo.rollbackTo();
            marker.drop();
            return defaultType;
        }
        ParserUtils.removeNls(builder);
        if (!ParserUtils.getToken(builder, FanTokenTypes.RBRACKET)) {

            if (forLiteral) {


                if (FanTokenTypes.COMMA == builder.getTokenType()) {
                    rollTo.rollbackTo();
                    marker.done(FanElementTypes.LIST_TYPE);
                    return TypeType.LIST;
                }

                if (FanTokenTypes.COLON == builder.getTokenType()) {
                    rollTo.rollbackTo();
                    marker.done(FanElementTypes.MAP_TYPE);
                    return TypeType.MAP;
                }

                if (defaultType != TypeType.MAP && defaultType != TypeType.LIST) {


                    boolean hasComma = false;
                    while (!builder.eof() && builder.getTokenType() != FanTokenTypes.RBRACKET) {
                        if (builder.getTokenType() == FanTokenTypes.COMMA) {
                            hasComma = true;
                            break;
                        }
                        builder.advanceLexer();
                    }
                    if (hasComma) {
                        rollTo.rollbackTo();
                        marker.done(FanElementTypes.LIST_TYPE);
                        return TypeType.LIST;
                    }
                }
            }
            rollTo.rollbackTo();
            marker.drop();
            return defaultType;
        }
        rollTo.drop();
        ParserUtils.removeNls(builder);
        marker.done(FanElementTypes.LIST_TYPE);
        PsiBuilder.Marker newMarker = builder.mark();
        return endOfTypeParse(builder, newMarker, forLiteral, TypeType.LIST);
    }
}
