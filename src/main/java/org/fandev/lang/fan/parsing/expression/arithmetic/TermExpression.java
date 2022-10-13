package org.fandev.lang.fan.parsing.expression.arithmetic;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.expression.argument.LiteralExpression;
import org.fandev.lang.fan.parsing.statements.Block;
import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.FieldDefinition;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.PropertyBlock;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class TermExpression {
    private static final TokenSet DOTS = TokenSet.create(new IElementType[]{FanTokenTypes.DOT, FanTokenTypes.DYN_CALL, FanTokenTypes.SAFE_DOT, FanTokenTypes.SAFE_DYN_CALL});


    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        PsiBuilder.Marker marker = builder.mark();
        TokenSet newStopper = TokenSet.orSet(new TokenSet[]{stopper, DOTS});
        boolean res = parseBase(builder, newStopper);
        if (res && (hasDot(builder) || !stopper.contains(builder.getTokenType()))) {
            res = parseTermChainLoop(builder, stopper);
        }
        if (res) {
            if (parseWithBlock(builder) == null) {
                res = false;
            }
        }
        marker.done(FanElementTypes.TERM_EXPR);
        return res;
    }

    public static boolean parseTermChainLoop(PsiBuilder builder, TokenSet stopper) {
        boolean res = true;
        if (!stopper.contains(FanTokenTypes.NLS)) {
            ParserUtils.removeNls(builder);
        }
        while (res && !builder.eof() && (hasDot(builder) || !stopper.contains(builder.getTokenType()))) {
            res = parseTermChain(builder, stopper);
            if (!stopper.contains(FanTokenTypes.NLS)) {
                ParserUtils.removeNls(builder);
            }
        }
        return res;
    }

    public static boolean parseBase(PsiBuilder builder, TokenSet stopper) {
        if (LiteralExpression.parse(builder, stopper)) {
            return true;
        }
        if (IdExpression.parse(builder)) {
            return true;
        }
        return ClosureExpression.parse(builder);
    }

    private static boolean parseTermChain(PsiBuilder builder, TokenSet stopper) {
        if (hasDot(builder)) {

            ParserUtils.removeNls(builder);
            ParserUtils.advanceNoNls(builder);

            if (FanTokenTypes.SUPER_KEYWORD == builder.getTokenType()) {
                builder.advanceLexer();
            } else {
                IdExpression.parse(builder);
            }
        } else if (FanTokenTypes.LBRACKET.equals(builder.getTokenType())) {

            ParserUtils.advanceNoNls(builder);
            boolean res = true;
            while (res && !builder.eof() && FanTokenTypes.RBRACKET != builder.getTokenType()) {
                res = Expression.parseExpr(builder, TokenSet.create(new IElementType[]{FanTokenTypes.RBRACKET}), FanElementTypes.INDEX_EXPR);
                ParserUtils.removeNls(builder);
            }
            ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
        } else if (FanTokenTypes.LPAR.equals(builder.getTokenType())) {

            ParserUtils.removeNls(builder);
            Arguments.parse(builder);
            ParserUtils.removeNls(builder);

            ClosureExpression.parse(builder);
        } else {
            return (parseWithBlock(builder) == FanElementTypes.WITH_BLOCK_EXPR);
        }
        return true;
    }


    private static boolean hasDot(PsiBuilder builder) {
        return DOTS.contains(ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS}));
    }

    private static IElementType parseWithBlock(PsiBuilder builder) {
        if (ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS}) == FanTokenTypes.LBRACE) {
            if (builder.getUserData(FieldDefinition.FIELD_NAME) != null) {

                PropertyBlock block = FieldDefinition.findPropertyBlockType(builder);
                if (block != PropertyBlock.NONE) {
                    return FanElementTypes.WRONGWAY;
                }
            }
            ParserUtils.removeNls(builder);
            if (Block.parse(builder, FanElementTypes.WITH_BLOCK_EXPR)) {
                return FanElementTypes.WITH_BLOCK_EXPR;
            }
            return null;
        }

        return FanElementTypes.WRONGWAY;
    }
}