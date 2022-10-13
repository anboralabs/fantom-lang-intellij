package org.fandev.lang.fan.parsing.statements;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.types.SimpleTypeSpec;
import org.fandev.lang.fan.parsing.types.TypeSpec;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class Statement {
    public static final TokenSet FOR_STOPPERS = TokenSet.create(new IElementType[]{FanTokenTypes.SEMICOLON, FanTokenTypes.RPAR});
    public static final TokenSet RPAR_STOPPER = TokenSet.create(new IElementType[]{FanTokenTypes.RPAR});
    public static final TokenSet SWITCH_CASE_STOPPER = TokenSet.create(new IElementType[]{FanTokenTypes.COLON, FanTokenTypes.RBRACE});
    public static final TokenSet CLOSURE_EOS = TokenSet.orSet(new TokenSet[]{FanTokenTypes.EOS, TokenSet.create(new IElementType[]{FanTokenTypes.COMMA})});

    public static boolean parse(PsiBuilder builder) {
        return parse(builder, false);
    }

    public static boolean parse(PsiBuilder builder, boolean inClosure) {
        IElementType statementType = null;
        PsiBuilder.Marker statementMark = builder.mark();
        IElementType tokenType = builder.getTokenType();
        if (FanTokenTypes.BREAK_KEYWORD.equals(tokenType) || FanTokenTypes.CONTINUE_KEYWORD.equals(tokenType)) {

            builder.advanceLexer();

            if (FanTokenTypes.EOS.contains(builder.getTokenType())) {
                ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
                statementType = FanElementTypes.CONTROL_FLOW;
            } else {
                builder.error(FanBundle.message("separator.expected", new Object[0]));
            }
        } else if (FanTokenTypes.FOR_KEYWORD.equals(tokenType)) {
            statementType = FanElementTypes.FOR_STATEMENT;
            parseFor(builder);
        } else if (FanTokenTypes.IF_KEYWORD.equals(tokenType)) {
            statementType = FanElementTypes.IF_STATEMENT;
            parseIf(builder);
        } else if (FanTokenTypes.RETURN_KEYWORD.equals(tokenType)) {
            statementType = FanElementTypes.RETURN_STATEMENT;
            parseReturnExpression(builder);
        } else if (FanTokenTypes.SWITCH_KEYWORD.equals(tokenType)) {
            statementType = FanElementTypes.SWITCH_STATEMENT;
            parseSwitch(builder);
        } else if (FanTokenTypes.THROW_KEYWORD.equals(tokenType)) {
            statementType = FanElementTypes.THROW_STATEMENT;
            parseThrowExpression(builder);
        } else if (FanTokenTypes.WHILE_KEYWORD.equals(tokenType)) {
            statementType = FanElementTypes.WHILE_STATEMENT;
            parseWhile(builder);
        } else if (FanTokenTypes.TRY_KEYWORD.equals(tokenType)) {
            statementType = FanElementTypes.TRY_STATEMENT;
            parseTry(builder);
        } else {
            TokenSet stopper = inClosure ? CLOSURE_EOS : FanTokenTypes.EOS;
            boolean res = expressionOrLocalDef(builder, stopper, FanElementTypes.EXPRESSION, FanElementTypes.LOCAL_DEF_STATEMENT);
            if (inClosure && res && ParserUtils.getToken(builder, FanTokenTypes.COMMA)) {
                statementMark.done(FanElementTypes.IT_ADD_STATEMENT);
            } else {
                statementMark.drop();
            }
            ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
            return res;
        }
        if (statementType != null) {
            statementMark.done(statementType);
            ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
            return true;
        }
        statementMark.drop();
        return false;
    }


    private static boolean parseFor(PsiBuilder builder) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.FOR_KEYWORD, FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.FOR_KEYWORD}))) {
            return false;
        }
        ParserUtils.removeNls(builder);
        ParserUtils.getToken(builder, FanTokenTypes.LPAR, FanBundle.message("lpar.expected", new Object[0]));
        expressionOrLocalDef(builder, FOR_STOPPERS, FanElementTypes.FOR_INIT_EXPR, FanElementTypes.FOR_INIT_LOCAL_DEF);
        ParserUtils.removeNls(builder);
        ParserUtils.getToken(builder, FanTokenTypes.SEMICOLON, FanBundle.message("semicolon.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        Expression.parseExpr(builder, FOR_STOPPERS, FanElementTypes.FOR_CONDITION);
        ParserUtils.removeNls(builder);
        ParserUtils.getToken(builder, FanTokenTypes.SEMICOLON, FanBundle.message("semicolon.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        Expression.parseExpr(builder, FOR_STOPPERS, FanElementTypes.FOR_REPEAT);
        ParserUtils.removeNls(builder);
        ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        Block.parse(builder, FanElementTypes.FOR_BLOCK);
        ParserUtils.removeNls(builder);
        return true;
    }


    private static boolean expressionOrLocalDef(PsiBuilder builder, TokenSet stopper, IElementType exprType, IElementType localDefType) {
        ParserUtils.removeNls(builder);

        TokenSet lookAheadStoppers = TokenSet.orSet(new TokenSet[]{stopper, TokenSet.create(new IElementType[]{FanTokenTypes.LBRACE})});
        if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.COLON_EQ, lookAheadStoppers)) {
            return parseLocalDef(builder, stopper, localDefType);
        }

        return Expression.parseExpr(builder, stopper, exprType);
    }


    private static boolean parseLocalDef(PsiBuilder builder, TokenSet stopper, IElementType localDefType) {
        boolean res = true;

        PsiBuilder.Marker localDef = builder.mark();

        PsiBuilder.Marker nameMark = builder.mark();
        if (ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET) && FanTokenTypes.COLON_EQ == builder.getTokenType()) {
            nameMark.done(FanElementTypes.NAME_ELEMENT);
        } else {
            nameMark.rollbackTo();
            res = TypeSpec.parse(builder);
            if (res) {
                ParserUtils.removeNls(builder);
                res = ParserUtils.parseName(builder);
            }
        }
        if (res && ParserUtils.getToken(builder, FanTokenTypes.COLON_EQ, FanBundle.message("localDef.assign.expected", new Object[0]))) {
            ParserUtils.removeNls(builder);
            res = Expression.parseExpr(builder, stopper, FanElementTypes.PARAM_DEFAULT);
        }
        if (res) {
            localDef.done(localDefType);
            return true;
        }

        localDef.done(localDefType);
        return false;
    }


    private static boolean parseIf(PsiBuilder builder) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.IF_KEYWORD, FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.IF_KEYWORD}))) {
            return false;
        }

        parseIfCondition(builder);
        boolean res = Block.parse(builder, FanElementTypes.COND_TRUE_BLOCK);
        ParserUtils.removeNls(builder);
        while (res && !builder.eof() && ParserUtils.getToken(builder, FanTokenTypes.ELSE_KEYWORD)) {
            ParserUtils.removeNls(builder);
            if (ParserUtils.getToken(builder, FanTokenTypes.IF_KEYWORD)) {
                parseIfCondition(builder);
                res = Block.parse(builder, FanElementTypes.COND_TRUE_BLOCK);
            } else {
                res = Block.parse(builder, FanElementTypes.COND_FALSE_BLOCK);
            }
            ParserUtils.removeNls(builder);
        }
        return res;
    }

    private static void parseIfCondition(PsiBuilder builder) {
        ParserUtils.removeNls(builder);
        ParserUtils.getToken(builder, FanTokenTypes.LPAR, FanBundle.message("lpar.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        Expression.parseExpr(builder, RPAR_STOPPER, FanElementTypes.CONDITION_EXPR);
        ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
        ParserUtils.removeNls(builder);
    }

    private static boolean parseReturnExpression(PsiBuilder builder) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.RETURN_KEYWORD, FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.RETURN_KEYWORD}))) {
            return false;
        }
        boolean res = true;
        if (!FanTokenTypes.EOS.contains(builder.getTokenType())) {
            res = Expression.parseExpr(builder, FanTokenTypes.EOS, FanElementTypes.EXPRESSION);
            ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
        }
        return res;
    }

    private static boolean parseThrowExpression(PsiBuilder builder) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.THROW_KEYWORD, FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.THROW_KEYWORD}))) {
            return false;
        }
        boolean res = Expression.parseExpr(builder, FanTokenTypes.EOS, FanElementTypes.EXPRESSION);
        ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
        return res;
    }

    private static boolean parseSwitch(PsiBuilder builder) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.SWITCH_KEYWORD, FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.SWITCH_KEYWORD}))) {
            return false;
        }
        ParserUtils.removeNls(builder);
        ParserUtils.getToken(builder, FanTokenTypes.LPAR, FanBundle.message("lpar.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        Expression.parseExpr(builder, RPAR_STOPPER, FanElementTypes.SWITCH_VALUE);
        ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        ParserUtils.getToken(builder, FanTokenTypes.LBRACE, FanBundle.message("lcurly.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        boolean hasDefault = false;
        while (!builder.eof() && !FanTokenTypes.RBRACE.equals(builder.getTokenType())) {
            PsiBuilder.Marker inSwitchMark = builder.mark();
            if (ParserUtils.getToken(builder, FanTokenTypes.CASE_KEYWORD)) {
                if (hasDefault) {
                    builder.error(FanBundle.message("case.after.default", new Object[0]));
                }
                Expression.parseExpr(builder, SWITCH_CASE_STOPPER, FanElementTypes.SWITCH_CASE_VALUE);
            } else if (ParserUtils.getToken(builder, FanTokenTypes.DEFAULT_KEYWORD)) {
                hasDefault = true;
            } else {
                inSwitchMark.error(FanBundle.message("case.default.expected", new Object[0]));
                ParserUtils.advanceNoNls(builder);
                continue;
            }
            if (ParserUtils.getToken(builder, FanTokenTypes.COLON, FanBundle.message("colon.expected", new Object[0]))) {
                ParserUtils.removeNls(builder);
                PsiBuilder.Marker mark = builder.mark();
                while (!builder.eof() && !FanTokenTypes.SWITCH_BLOCK_TOKENS.contains(builder.getTokenType())) {
                    if (!parse(builder)) {
                        builder.error(FanBundle.message("expression.expected", new Object[0]));

                        ParserUtils.advanceNoNls(builder);
                        ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
                    }
                }
                mark.done(FanElementTypes.SWITCH_CASE_STATEMENT);
            }
            inSwitchMark.done(FanElementTypes.SWITCH_CASE);
            ParserUtils.removeNls(builder);
        }
        ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        return true;
    }

    private static boolean parseWhile(PsiBuilder builder) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.WHILE_KEYWORD, FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.WHILE_KEYWORD}))) {
            return false;
        }
        ParserUtils.removeNls(builder);
        ParserUtils.getToken(builder, FanTokenTypes.LPAR, FanBundle.message("lpar.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        Expression.parseExpr(builder, RPAR_STOPPER, FanElementTypes.WHILE_CONDITION);
        ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
        ParserUtils.removeNls(builder);
        Block.parse(builder, FanElementTypes.WHILE_BLOCK);
        ParserUtils.removeNls(builder);
        return true;
    }

    private static boolean parseTry(PsiBuilder builder) {
        if (!ParserUtils.getToken(builder, FanTokenTypes.TRY_KEYWORD, FanBundle.message("keywords.expected", new Object[]{FanTokenTypes.TRY_KEYWORD}))) {
            return false;
        }
        ParserUtils.removeNls(builder);
        Block.parse(builder, FanElementTypes.TRY_BLOCK);
        ParserUtils.removeNls(builder);
        if (!FanTokenTypes.TRY_BLOCK_TOKENS.contains(builder.getTokenType())) {
            builder.error(FanBundle.message("catch.finally.expected", new Object[0]));
        } else {
            boolean hasFinally = false;
            while (!builder.eof() && FanTokenTypes.TRY_BLOCK_TOKENS.contains(builder.getTokenType())) {
                PsiBuilder.Marker catchMark = builder.mark();
                if (ParserUtils.getToken(builder, FanTokenTypes.CATCH_KEYWORD)) {
                    if (hasFinally) {
                        builder.error(FanBundle.message("catch.after.finally", new Object[0]));
                    }
                    ParserUtils.removeNls(builder);

                    if (ParserUtils.getToken(builder, FanTokenTypes.LPAR)) {
                        ParserUtils.removeNls(builder);
                        SimpleTypeSpec.parseSimpleType(builder, false);
                        ParserUtils.removeNls(builder);
                        PsiBuilder.Marker nameMarker = builder.mark();
                        if (ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]))) {
                            nameMarker.done(FanElementTypes.NAME_ELEMENT);
                        } else {
                            nameMarker.drop();
                        }
                        ParserUtils.removeNls(builder);
                        ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
                        ParserUtils.removeNls(builder);
                    }
                    Block.parse(builder, FanElementTypes.CATCH_BLOCK);
                    catchMark.done(FanElementTypes.CATCH_STATEMENT);
                } else if (ParserUtils.getToken(builder, FanTokenTypes.FINALLY_KEYWORD)) {
                    hasFinally = true;
                    ParserUtils.removeNls(builder);
                    Block.parse(builder, FanElementTypes.FINALLY_BLOCK);
                    catchMark.done(FanElementTypes.FINALLY_STATEMENT);
                } else {
                    catchMark.drop();
                    break;
                }
                ParserUtils.removeNls(builder);
            }
        }
        return true;
    }
}