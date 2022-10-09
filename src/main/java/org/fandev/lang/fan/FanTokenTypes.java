package org.fandev.lang.fan;


import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;


public interface FanTokenTypes {
    public static final IElementType IDENTIFIER = new FanElementType("identifier");
    public static final IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    public static final IElementType NLS = new FanElementType("new line");
    public static final IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    public static final IElementType SEMANTIC_LINEFEED = new FanElementType("SEMANTIC_LINEFEED");

    public static final IElementType END_OF_LINE_COMMENT = new FanElementType("end of line comment");
    public static final IElementType FANDOC_LINE_COMMENT = new FanElementType("fandoc comment");
    public static final IElementType C_STYLE_COMMENT = new FanElementType("c style comment");


    public static final IElementType ASSERT_KEYWORD = new FanElementType("assert");
    public static final IElementType BREAK_KEYWORD = new FanElementType("break");
    public static final IElementType CASE_KEYWORD = new FanElementType("case");
    public static final IElementType CATCH_KEYWORD = new FanElementType("catch");
    public static final IElementType CLASS_KEYWORD = new FanElementType("class");
    public static final IElementType CONTINUE_KEYWORD = new FanElementType("continue");
    public static final IElementType DEFAULT_KEYWORD = new FanElementType("default");
    public static final IElementType DO_KEYWORD = new FanElementType("do");
    public static final IElementType ELSE_KEYWORD = new FanElementType("else");
    public static final IElementType ENUM_KEYWORD = new FanElementType("enum");
    public static final IElementType POD_KEYWORD = new FanElementType("pod");
    public static final IElementType FALSE_KEYWORD = new FanElementType("false");
    public static final IElementType FINALLY_KEYWORD = new FanElementType("finally");
    public static final IElementType FOR_KEYWORD = new FanElementType("for");
    public static final IElementType FOREACH_KEYWORD = new FanElementType("foreach");
    public static final IElementType IF_KEYWORD = new FanElementType("if");
    public static final IElementType MIXIN_KEYWORD = new FanElementType("mixin");
    public static final IElementType ONCE_KEYWORD = new FanElementType("once");
    public static final IElementType READONLY_KEYWORD = new FanElementType("readonly");
    public static final IElementType RETURN_KEYWORD = new FanElementType("return");
    public static final IElementType SWITCH_KEYWORD = new FanElementType("switch");
    public static final IElementType THROW_KEYWORD = new FanElementType("throw");
    public static final IElementType TRUE_KEYWORD = new FanElementType("true");
    public static final IElementType TRY_KEYWORD = new FanElementType("try");
    public static final IElementType USING_KEYWORD = new FanElementType("using");
    public static final IElementType VOLATILE_KEYWORD = new FanElementType("volatile");
    public static final IElementType WHILE_KEYWORD = new FanElementType("while");


    public static final IElementType FINAL_KEYWORD = new FanElementType("final");


    public static final IElementType ABSTRACT_KEYWORD = new FanElementType("abstract");
    public static final IElementType CONST_KEYWORD = new FanElementType("const");
    public static final IElementType NATIVE_KEYWORD = new FanElementType("native");
    public static final IElementType NEW_KEYWORD = new FanElementType("new");
    public static final IElementType OVERRIDE_KEYWORD = new FanElementType("override");
    public static final IElementType STATIC_KEYWORD = new FanElementType("static");
    public static final IElementType VIRTUAL_KEYWORD = new FanElementType("virtual");


    public static final IElementType GET_KEYWORD = new FanElementType("get");
    public static final IElementType SET_KEYWORD = new FanElementType("set");


    public static final IElementType PUBLIC_KEYWORD = new FanElementType("public");
    public static final IElementType PRIVATE_KEYWORD = new FanElementType("private");
    public static final IElementType PROTECTED_KEYWORD = new FanElementType("protected");
    public static final IElementType INTERNAL_KEYWORD = new FanElementType("internal");


    public static final IElementType INT_LITERAL = new FanElementType("integer literal");
    public static final IElementType FLOAT_LITERAL = new FanElementType("float literal");
    public static final IElementType DECIMAL_LITERAL = new FanElementType("decimal literal");
    public static final IElementType DURATION_LITERAL = new FanElementType("duration literal");
    public static final IElementType URI_LITERAL = new FanElementType("URI_LITERAL");
    public static final IElementType STRING_LITERAL = new FanElementType("STRING_LITERAL");
    public static final IElementType DSL_STRING = new FanElementType("DSL_STRING");
    public static final IElementType CHAR_LITERAL = new FanElementType("CHAR_LITERAL");

    public static final IElementType NULL_KEYWORD = new FanElementType("null");
    public static final IElementType THIS_KEYWORD = new FanElementType("this");
    public static final IElementType SUPER_KEYWORD = new FanElementType("super");

    public static final IElementType FAN_SYS_TYPE = new FanElementType("SysType");


    public static final IElementType DOT = new FanElementType(".");
    public static final IElementType DYN_CALL = new FanElementType("->");
    public static final IElementType SAFE_DOT = new FanElementType("?.");
    public static final IElementType SAFE_DYN_CALL = new FanElementType("?->");


    public static final IElementType COLON_EQ = new FanElementType(":=");
    public static final IElementType SEMICOLON = new FanElementType(";");
    public static final IElementType COMMA = new FanElementType(",");
    public static final IElementType COLON = new FanElementType(":");
    public static final IElementType OR = new FanElementType("|");
    public static final IElementType LBRACE = new FanElementType("{");
    public static final IElementType RBRACE = new FanElementType("}");
    public static final IElementType LPAR = new FanElementType("(");
    public static final IElementType RPAR = new FanElementType(")");
    public static final IElementType LBRACKET = new FanElementType("[");
    public static final IElementType RBRACKET = new FanElementType("]");


    public static final IElementType EQ = new FanElementType("=");
    public static final IElementType PLUSEQ = new FanElementType("+=");
    public static final IElementType MINUSEQ = new FanElementType("-=");
    public static final IElementType MULTEQ = new FanElementType("*=");
    public static final IElementType DIVEQ = new FanElementType("/=");
    public static final IElementType PERCEQ = new FanElementType("%=");
    public static final IElementType LTLTEQ = new FanElementType("<<=");
    public static final IElementType GTGTEQ = new FanElementType(">>=");
    public static final IElementType ANDEQ = new FanElementType("&=");
    public static final IElementType OREQ = new FanElementType("|=");
    public static final IElementType XOREQ = new FanElementType("^=");

    public static final IElementType SHABENG = new FanElementType("#!");
    public static final IElementType SHARP = new FanElementType("#");
    public static final IElementType AT = new FanElementType("@");
    public static final IElementType QUEST = new FanElementType("?");
    public static final IElementType QUEST_COLON = new FanElementType("?:");


    public static final IElementType EQEQ = new FanElementType("==");
    public static final IElementType NE = new FanElementType("!=");
    public static final IElementType EQEQEQ = new FanElementType("===");
    public static final IElementType NEEQ = new FanElementType("!==");


    public static final IElementType IS_KEYWORD = new FanElementType("is");
    public static final IElementType AS_KEYWORD = new FanElementType("as");
    public static final IElementType ISNOT_KEYWORD = new FanElementType("isnot");
    public static final IElementType LT = new FanElementType("<");
    public static final IElementType GT = new FanElementType(">");
    public static final IElementType LE = new FanElementType("<=");
    public static final IElementType GE = new FanElementType(">=");
    public static final IElementType COMPARE = new FanElementType("<=>");

    public static final IElementType PLUS = new FanElementType("+");
    public static final IElementType MINUS = new FanElementType("-");
    public static final IElementType MULT = new FanElementType("*");
    public static final IElementType DIV = new FanElementType("/");
    public static final IElementType PERC = new FanElementType("%");
    public static final IElementType PLUSPLUS = new FanElementType("++");
    public static final IElementType MINUSMINUS = new FanElementType("--");
    public static final IElementType LTLT = new FanElementType("<<");
    public static final IElementType GTGT = new FanElementType(">>");
    public static final IElementType AND = new FanElementType("&");
    public static final IElementType XOR = new FanElementType("^");
    public static final IElementType EXCL = new FanElementType("!");
    public static final IElementType TILDE = new FanElementType("~");
    public static final IElementType ANDAND = new FanElementType("&&");
    public static final IElementType OROR = new FanElementType("||");


    public static final IElementType COLON_COLON = new FanElementType("::");

    public static final IElementType RANGE_SEP_INCL = new FanElementType("..");
    public static final IElementType RANGE_SEP_EXCL = new FanElementType("...");

    public static final TokenSet COMMENTS = TokenSet.create(new IElementType[]{C_STYLE_COMMENT, END_OF_LINE_COMMENT, FANDOC_LINE_COMMENT});

    public static final TokenSet FAN_KEYWORDS = TokenSet.create(new IElementType[]{ABSTRACT_KEYWORD, AS_KEYWORD, ASSERT_KEYWORD, BREAK_KEYWORD, CASE_KEYWORD, CATCH_KEYWORD, CLASS_KEYWORD, CONST_KEYWORD, CONTINUE_KEYWORD, DEFAULT_KEYWORD, DO_KEYWORD, ELSE_KEYWORD, ENUM_KEYWORD, FALSE_KEYWORD, FINAL_KEYWORD, FINALLY_KEYWORD, FOR_KEYWORD, FOREACH_KEYWORD, IF_KEYWORD, INTERNAL_KEYWORD, IS_KEYWORD, ISNOT_KEYWORD, MIXIN_KEYWORD, NATIVE_KEYWORD, NEW_KEYWORD, NULL_KEYWORD, ONCE_KEYWORD, OVERRIDE_KEYWORD, PRIVATE_KEYWORD, PROTECTED_KEYWORD, PUBLIC_KEYWORD, READONLY_KEYWORD, RETURN_KEYWORD, STATIC_KEYWORD, SUPER_KEYWORD, SWITCH_KEYWORD, THIS_KEYWORD, THROW_KEYWORD, TRUE_KEYWORD, TRY_KEYWORD, USING_KEYWORD, VIRTUAL_KEYWORD, VOLATILE_KEYWORD, WHILE_KEYWORD});


    public static final TokenSet IDENTIFIER_TOKENS_SET = TokenSet.create(new IElementType[]{IDENTIFIER, FAN_SYS_TYPE, POD_KEYWORD});


    public static final TokenSet PROTECTION = TokenSet.create(new IElementType[]{PRIVATE_KEYWORD, PROTECTED_KEYWORD, PUBLIC_KEYWORD, INTERNAL_KEYWORD});


    public static final TokenSet CLASS_MODIFIERS = TokenSet.orSet(new TokenSet[]{PROTECTION, TokenSet.create(new IElementType[]{ABSTRACT_KEYWORD, FINAL_KEYWORD, CONST_KEYWORD})});


    public static final TokenSet MIXIN_MODIFIERS = TokenSet.orSet(new TokenSet[]{PROTECTION, TokenSet.create(new IElementType[]{CONST_KEYWORD})});


    public static final TokenSet CTOR_MODIFIERS = PROTECTION;


    public static final TokenSet METHOD_MODIFIERS = TokenSet.orSet(new TokenSet[]{PROTECTION, TokenSet.create(new IElementType[]{ABSTRACT_KEYWORD, FINAL_KEYWORD, ONCE_KEYWORD, OVERRIDE_KEYWORD, STATIC_KEYWORD, VIRTUAL_KEYWORD, NATIVE_KEYWORD})});


    public static final TokenSet FIELD_MODIFIERS = TokenSet.orSet(new TokenSet[]{PROTECTION, TokenSet.create(new IElementType[]{READONLY_KEYWORD, STATIC_KEYWORD, CONST_KEYWORD, NATIVE_KEYWORD, OVERRIDE_KEYWORD, VIRTUAL_KEYWORD, VOLATILE_KEYWORD, ABSTRACT_KEYWORD, FINAL_KEYWORD})});


    public static final TokenSet ALL_SLOT_MODIFIERS = TokenSet.orSet(new TokenSet[]{METHOD_MODIFIERS, FIELD_MODIFIERS});


    public static final TokenSet ALL_MODIFIERS = TokenSet.orSet(new TokenSet[]{PROTECTION, TokenSet.create(new IElementType[]{ABSTRACT_KEYWORD, CONST_KEYWORD, FINAL_KEYWORD, ONCE_KEYWORD, NATIVE_KEYWORD, READONLY_KEYWORD, OVERRIDE_KEYWORD, STATIC_KEYWORD, VIRTUAL_KEYWORD})});


    public static final TokenSet EOL = TokenSet.create(new IElementType[]{NLS});
    public static final TokenSet SEPARATOR = TokenSet.create(new IElementType[]{SEMICOLON, NLS});
    public static final TokenSet EOS = TokenSet.create(new IElementType[]{SEMICOLON, RBRACE, NLS});
    public static final TokenSet SWITCH_BLOCK_TOKENS = TokenSet.create(new IElementType[]{RBRACE, CASE_KEYWORD, DEFAULT_KEYWORD});
    public static final TokenSet TRY_BLOCK_TOKENS = TokenSet.create(new IElementType[]{CATCH_KEYWORD, FINALLY_KEYWORD});

    public static final TokenSet ASSIGN_OP = TokenSet.create(new IElementType[]{EQ, MULTEQ, DIVEQ, PERCEQ, PLUSEQ, MINUSEQ, GTGTEQ, LTLTEQ, ANDEQ, XOREQ, OREQ});

    public static final TokenSet EQUALITY_OP = TokenSet.create(new IElementType[]{EQEQ, NE, EQEQEQ, NEEQ});
    public static final TokenSet TYPE_COMPARE = TokenSet.create(new IElementType[]{IS_KEYWORD, AS_KEYWORD, ISNOT_KEYWORD});
    public static final TokenSet RELATIONAL_OP = TokenSet.orSet(new TokenSet[]{TYPE_COMPARE, TokenSet.create(new IElementType[]{LT, LE, GT, GE, COMPARE})});
    public static final TokenSet COMPARISON_OP = TokenSet.orSet(new TokenSet[]{EQUALITY_OP, RELATIONAL_OP});

    public static final TokenSet BOOL_LITERALS = TokenSet.create(new IElementType[]{TRUE_KEYWORD, FALSE_KEYWORD});
    public static final TokenSet STRING_LITERALS = TokenSet.create(new IElementType[]{URI_LITERAL, STRING_LITERAL, DSL_STRING, CHAR_LITERAL});

    public static final TokenSet NUMERIC_LITERALS = TokenSet.create(new IElementType[]{INT_LITERAL, FLOAT_LITERAL, DECIMAL_LITERAL, DURATION_LITERAL});

    public static final TokenSet FAN_LITERALS = TokenSet.orSet(new TokenSet[]{BOOL_LITERALS, STRING_LITERALS, NUMERIC_LITERALS});
}