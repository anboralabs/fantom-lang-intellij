package org.fandev.lang.fan.parsing.expression.argument;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.types.TypeSpec;
import org.fandev.lang.fan.parsing.types.TypeType;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class LiteralExpression {
    private static final TokenSet LITERALS = TokenSet.orSet(new TokenSet[]{FanTokenTypes.FAN_LITERALS, TokenSet.create(new IElementType[]{FanTokenTypes.NULL_KEYWORD, FanTokenTypes.THIS_KEYWORD, FanTokenTypes.SUPER_KEYWORD})});

    private static final TokenSet COLON_STOPPER = TokenSet.create(new IElementType[]{FanTokenTypes.COLON});
    private static final TokenSet COLON_COMMA_RBRACKET_STOPPER = TokenSet.create(new IElementType[]{FanTokenTypes.COLON, FanTokenTypes.COMMA, FanTokenTypes.RBRACKET});
    private static final TokenSet RBRACKET_COMMA = TokenSet.create(new IElementType[]{FanTokenTypes.RBRACKET, FanTokenTypes.COMMA});

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        PsiBuilder.Marker marker = builder.mark();

        if (FanTokenTypes.THIS_KEYWORD.equals(builder.getTokenType())) {
            builder.advanceLexer();
            marker.done(FanElementTypes.THIS_REFERENCE_EXPRESSION);
            return true;
        }
        if (FanTokenTypes.SUPER_KEYWORD.equals(builder.getTokenType())) {
            builder.advanceLexer();
            marker.done(FanElementTypes.SUPER_REFERENCE_EXPRESSION);
            return true;
        }
        if (LITERALS.contains(builder.getTokenType())) {
            builder.advanceLexer();
            marker.done(FanElementTypes.LITERAL);
            return true;
        }

        if (ParserUtils.getToken(builder, FanTokenTypes.SHARP)) {
            if (ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]))) {
                marker.done(FanElementTypes.LITERAL);
                return true;
            }
            marker.drop();
            return false;
        }

        TypeType typeType = TypeSpec.parseType(builder, true);
        if (typeType != TypeType.NONE) {
            if (ParserUtils.getToken(builder, FanTokenTypes.SHARP)) {

                ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET);
                marker.done(FanElementTypes.LITERAL);
                return true;
            }
            if (ParserUtils.getToken(builder, FanTokenTypes.DSL_STRING)) {
                marker.done(FanElementTypes.LITERAL);
                return true;
            }
            if (FanTokenTypes.LBRACKET == builder.getTokenType()) {
                if (typeType == TypeType.MAP) {
                    return parseListOrMapLiteral(builder, LiteralType.MAP, marker);
                }
                if (typeType == TypeType.LIST) {
                    return parseListOrMapLiteral(builder, LiteralType.LIST, marker);
                }
            }
            marker.rollbackTo();
            marker = builder.mark();
        }
        return parseListOrMapLiteral(builder, LiteralType.UNKNOW, marker);
    }

    enum LiteralType {
        UNKNOW, LIST, MAP, ERROR;
    }

    private static boolean parseListOrMapLiteral(PsiBuilder builder, LiteralType litType, PsiBuilder.Marker marker) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACKET)) {
            marker.rollbackTo();
            return false;
        }
        ParserUtils.removeNls(builder);

        LiteralType emptyLiteralType = emptyMapOrList(builder, litType);
        switch (emptyLiteralType) {
            case LIST:
                ParserUtils.removeNls(builder);
                ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
                marker.done(FanElementTypes.LIST_LITERAL);
                return true;
            case MAP:
                ParserUtils.removeNls(builder);
                ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
                marker.done(FanElementTypes.MAP_LITERAL);
                return true;
            case ERROR:
                marker.rollbackTo();
                return false;
        }


        litType = mapOrListLiteralWithValues(builder, litType);
        switch (litType) {
            case LIST:
                ParserUtils.removeNls(builder);
                ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
                marker.done(FanElementTypes.LIST_LITERAL);
                return true;
            case MAP:
                ParserUtils.removeNls(builder);
                ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
                marker.done(FanElementTypes.MAP_LITERAL);
                return true;
        }
        marker.rollbackTo();
        return false;
    }

    private static LiteralType mapOrListLiteralWithValues(PsiBuilder builder, LiteralType litType) {
        PsiBuilder.Marker valMark = builder.mark();

        boolean res = Expression.parseExpr(builder, COLON_COMMA_RBRACKET_STOPPER, FanElementTypes.EXPRESSION);
        if (res) {
            litType = findLiteralType(builder, litType);
        }
        if (!res || litType == LiteralType.ERROR) {
            valMark.drop();
            return LiteralType.ERROR;
        }
        if (litType == LiteralType.LIST) {
            valMark.done(FanElementTypes.LIST_ITEM);
            if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {
                ParserUtils.advanceNoNls(builder);
            }
            while (res && !builder.eof() && FanTokenTypes.RBRACKET != builder.getTokenType()) {
                res = Expression.parseExpr(builder, RBRACKET_COMMA, FanElementTypes.LIST_ITEM);
                ParserUtils.removeNls(builder);
                if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {
                    ParserUtils.advanceNoNls(builder);
                }
            }
            return LiteralType.LIST;
        }
        if (litType == LiteralType.MAP) {
            PsiBuilder.Marker mapEntryMark = valMark.precede();
            valMark.done(FanElementTypes.MAP_ITEM_KEY);
            ParserUtils.advanceNoNls(builder);
            if (!Expression.parseExpr(builder, RBRACKET_COMMA, FanElementTypes.MAP_ITEM_VALUE)) {
                mapEntryMark.drop();
                return LiteralType.ERROR;
            }
            mapEntryMark.done(FanElementTypes.MAP_ITEM);
            ParserUtils.removeNls(builder);
            if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {
                ParserUtils.advanceNoNls(builder);
            }
            while (!builder.eof() && FanTokenTypes.RBRACKET != builder.getTokenType()) {
                mapEntryMark = builder.mark();
                if (Expression.parseExpr(builder, COLON_STOPPER, FanElementTypes.MAP_ITEM_KEY)) {
                    ParserUtils.advanceNoNls(builder);
                    if (Expression.parseExpr(builder, RBRACKET_COMMA, FanElementTypes.MAP_ITEM_VALUE)) {
                        mapEntryMark.done(FanElementTypes.MAP_ITEM);
                        ParserUtils.removeNls(builder);
                        if (FanTokenTypes.COMMA.equals(builder.getTokenType()))
                            ParserUtils.advanceNoNls(builder);
                        continue;
                    }
                    mapEntryMark.drop();
                    return LiteralType.MAP;
                }

                mapEntryMark.drop();
                return LiteralType.MAP;
            }

            return LiteralType.MAP;
        }
        return LiteralType.ERROR;
    }

    private static LiteralType findLiteralType(PsiBuilder builder, LiteralType litType) {
        switch (litType) {
            case LIST:
                if (!RBRACKET_COMMA.contains(builder.getTokenType())) {
                    builder.error(FanBundle.message("comma.rbracket.expected", new Object[0]));
                    litType = LiteralType.ERROR;
                }


                return litType;
            case MAP:
                if (!FanTokenTypes.COLON.equals(builder.getTokenType())) {
                    builder.error(FanBundle.message("colon.expected", new Object[0]));
                    litType = LiteralType.ERROR;
                }
                return litType;
        }
        if (RBRACKET_COMMA.contains(builder.getTokenType())) {
            litType = LiteralType.LIST;
        } else if (FanTokenTypes.COLON.equals(builder.getTokenType())) {
            litType = LiteralType.MAP;
        } else {
            builder.error(FanBundle.message("literal.listOrMap.expected", new Object[0]));
            litType = LiteralType.ERROR;
        }
        return litType;
    }

    private static LiteralType emptyMapOrList(PsiBuilder builder, LiteralType litType) {
        LiteralType res = LiteralType.UNKNOW;
        if (ParserUtils.getToken(builder, FanTokenTypes.COMMA)) {

            if (litType == LiteralType.UNKNOW) {
                res = LiteralType.LIST;
            } else if (litType != LiteralType.LIST) {
                res = LiteralType.LIST;
            } else {
                res = litType;
            }
        } else if (ParserUtils.getToken(builder, FanTokenTypes.COLON)) {

            if (litType == LiteralType.UNKNOW) {
                res = LiteralType.MAP;
            } else if (litType != LiteralType.MAP) {
                builder.error(FanBundle.message("literal.map.unexpected", new Object[0]));
                res = LiteralType.MAP;
            } else {
                res = litType;
            }
        }
        return res;
    }
}