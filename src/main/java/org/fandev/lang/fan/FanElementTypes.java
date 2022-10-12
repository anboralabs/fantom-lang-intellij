/*     */ package org.fandev.lang.fan;
/*     */ 
/*     */

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.*;
import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.fandev.lang.fan.psi.stubs.elements.*;
import org.fandev.lang.fan.types.FanFileElementType;

import java.util.Arrays;
import java.util.Collection;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface FanElementTypes
/*     */ {
/*  30 */   public static final IFileElementType FILE = (IFileElementType)new FanFileElementType(FantomLanguage.INSTANCE);
/*     */ 
/*     */   
/*  33 */   public static final FanStubElementType<FanTypeDefinitionStub, FanClassDefinition> CLASS_DEFINITION = (FanStubElementType<FanTypeDefinitionStub, FanClassDefinition>)new FanClassDefinitionElementType();
/*  34 */   public static final IElementType CLASS_BODY = new FanElementType("CLASS_BODY");
/*  35 */   public static final IElementType CLASS_TYPE_ELEMENT = new FanElementType("CLASS_TYPE_ELEMENT");
/*     */ 
/*     */   
/*  38 */   public static final FanStubElementType<FanTypeDefinitionStub, FanMixinDefinition> MIXIN_DEFINITION = (FanStubElementType<FanTypeDefinitionStub, FanMixinDefinition>)new FanMixinDefinitionElementType();
/*  39 */   public static final IElementType MIXIN_BODY = new FanElementType("MIXIN_BODY");
/*     */ 
/*     */   
/*  42 */   public static final FanStubElementType<FanTypeDefinitionStub, FanEnumDefinition> ENUM_DEFINITION = (FanStubElementType<FanTypeDefinitionStub, FanEnumDefinition>)new FanEnumDefinitionElementType();
/*  43 */   public static final IElementType ENUM_BODY = new FanElementType("ENUM_BODY");
/*  44 */   public static final IElementType ENUM_VALUE = new FanElementType("ENUM_VALUE");
/*     */ 
/*     */   
/*  47 */   public static final FanStubElementType<FanTypeDefinitionStub, FanPodDefinition> POD_DEFINITION = (FanStubElementType<FanTypeDefinitionStub, FanPodDefinition>)new FanPodDefinitionElementType();
/*  48 */   public static final IElementType POD_BODY = new FanElementType("POD_BODY");
/*     */   
/*  50 */   public static final IElementType STATIC_BLOCK = (IElementType)new FanMethodElementType("static block");
/*     */   
/*  52 */   public static final IElementType CTOR_DEFINITION = (IElementType)new FanConstructorElementType("CTOR_DEFINITION");
/*  53 */   public static final IElementType CTOR_CHAIN = new FanElementType("CTOR_CHAIN");
/*     */   
/*  55 */   public static final IElementType METHOD_DEFINITION = (IElementType)new FanMethodElementType("METHOD_DEFINITION");
/*  56 */   public static final IElementType PARAM_DEFAULT = new FanElementType("Parameter default value");
/*     */   
/*  58 */   public static final IElementType METHOD_BODY = new FanElementType("method block");
/*     */   
/*  60 */   public static final IElementType FIELD_DEFINITION = (IElementType)new FanFieldElementType("FIELD_DEFINITION");
/*  61 */   public static final IElementType FIELD_DEFAULT = new FanElementType("Field default value");
/*  62 */   public static final IElementType GETTER_SETTER_FIELD_DEFINITION = new FanElementType("GETTER_SETTER_FIELD_DEFINITION");
/*  63 */   public static final IElementType GETTER_FIELD_DEFINITION = new FanElementType("GETTER_FIELD_DEFINITION");
/*  64 */   public static final IElementType SETTER_FIELD_DEFINITION = new FanElementType("SETTER_FIELD_DEFINITION");
/*     */   
/*  66 */   public static final FanStubElementType<FanReferenceListStub, FanInheritanceClause> INHERITANCE_CLAUSE = (FanStubElementType<FanReferenceListStub, FanInheritanceClause>)new FanInheritanceClauseElementType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static final IElementType NAME_ELEMENT = new FanElementType("name id");
/*     */   
/*  74 */   public static final IElementType REFERENCE_ELEMENT = new FanElementType("REFERENCE_EXPRESSION");
/*     */   
/*  76 */   public static final IElementType MODIFIERS = new FanElementType("MODIFIERS");
/*     */   
/*  78 */   public static final IElementType BUILT_IN_TYPE = new FanElementType("BUILT_IN_TYPE");
/*     */   
/*  80 */   public static final IElementType NONE = new FanElementType("NO_TOKEN");
/*     */   
/*  82 */   public static final IElementType WRONGWAY = new FanElementType("WRONG_WAY");
/*     */   
/*  84 */   public static final IElementType LCURLY = new FanElementType("{");
/*     */   
/*  86 */   public static final IElementType TYPE = new FanElementType("Full Type wrapper");
/*  87 */   public static final IElementType NULLABLE_TYPE = new FanElementType("mark for ?");
/*     */   
/*  89 */   public static final IElementType LIST_TYPE = new FanElementType("LIST_TYPE");
/*     */   
/*  91 */   public static final IElementType MAP_TYPE = new FanElementType("MAP_TYPE");
/*     */   
/*  93 */   public static final IElementType FUNC_TYPE = new FanElementType("FUNC_TYPE");
/*  94 */   public static final IElementType FORMALS = new FanElementType("FORMALS");
/*  95 */   public static final IElementType FORMAL = new FanElementType("FORMAL");
/*     */   
/*  97 */   public static final IElementType TYPE_PARAMETER_LIST = new FanElementType("TYPE_PARAMETER_LIST");
/*     */   
/*  99 */   public static final IElementType TYPE_PARAMETER = new FanElementType("TYPE_PARAMETER");
/*     */   
/* 101 */   public static final IElementType ARGUMENT_LIST = new FanElementType("args");
/* 102 */   public static final IElementType ARGUMENT_EXPR = new FanElementType("arg exp");
/*     */   
/* 104 */   public static final IElementType FACET = new FanElementType("facet declaration");
/* 105 */   public static final IElementType FACET_VALUE = new FanElementType("facet value");
/*     */   
/* 107 */   public static final IElementType EXPRESSION = new FanElementType("expression");
/* 108 */   public static final IElementType LOCAL_DEF_STATEMENT = new FanElementType("local def");
/* 109 */   public static final IElementType IT_ADD_STATEMENT = new FanElementType("it add");
/*     */   
/* 111 */   public static final IElementType FFI_NAME = new FanElementType("FFI name");
/* 112 */   public static final IElementType POD_REFERENCE = new FanElementType("Pod or Package Reference");
/* 113 */   public static final IElementType USING_AS_NAME = new FanElementType("Using as name");
/* 114 */   public static final IElementType USING_STATEMENT = new FanElementType("Using statement");
/* 115 */   public static final IElementType CONTROL_FLOW = new FanElementType("Control Flow");
/* 116 */   public static final IElementType FOR_STATEMENT = new FanElementType("For statement");
/* 117 */   public static final IElementType FOR_INIT_EXPR = new FanElementType("For init expression");
/* 118 */   public static final IElementType FOR_INIT_LOCAL_DEF = new FanElementType("For init local def");
/* 119 */   public static final IElementType FOR_CONDITION = new FanElementType("For condition");
/* 120 */   public static final IElementType FOR_REPEAT = new FanElementType("For repeat");
/* 121 */   public static final IElementType FOR_BLOCK = new FanElementType("For block");
/* 122 */   public static final IElementType IF_STATEMENT = new FanElementType("If statement");
/* 123 */   public static final IElementType CONDITION_EXPR = new FanElementType("Condition expression");
/* 124 */   public static final IElementType COND_TRUE_BLOCK = new FanElementType("If block");
/* 125 */   public static final IElementType COND_FALSE_BLOCK = new FanElementType("Else block");
/* 126 */   public static final IElementType RETURN_STATEMENT = new FanElementType("return");
/* 127 */   public static final IElementType THROW_STATEMENT = new FanElementType("throw");
/* 128 */   public static final IElementType SWITCH_STATEMENT = new FanElementType("Switch");
/* 129 */   public static final IElementType SWITCH_VALUE = new FanElementType("Switch value");
/* 130 */   public static final IElementType SWITCH_CASE = new FanElementType("Switch case");
/* 131 */   public static final IElementType SWITCH_CASE_VALUE = new FanElementType("Switch case value");
/* 132 */   public static final IElementType SWITCH_CASE_STATEMENT = new FanElementType("Switch case statement");
/* 133 */   public static final IElementType WHILE_STATEMENT = new FanElementType("while statement");
/* 134 */   public static final IElementType WHILE_CONDITION = new FanElementType("while condition");
/* 135 */   public static final IElementType WHILE_BLOCK = new FanElementType("while block");
/* 136 */   public static final IElementType TRY_STATEMENT = new FanElementType("try statement");
/* 137 */   public static final IElementType TRY_BLOCK = new FanElementType("try block");
/* 138 */   public static final IElementType CATCH_STATEMENT = new FanElementType("catch statement");
/* 139 */   public static final IElementType CATCH_DEFINITION = new FanElementType("catch definition");
/* 140 */   public static final IElementType CATCH_BLOCK = new FanElementType("catch block");
/* 141 */   public static final IElementType FINALLY_STATEMENT = new FanElementType("finally statement");
/* 142 */   public static final IElementType FINALLY_BLOCK = new FanElementType("finally block");
/*     */   
/* 144 */   public static final IElementType ASSIGN_EXPRESSION = new FanElementType("assign expr");
/* 145 */   public static final IElementType ASSIGN_LEFT_EXPR = new FanElementType("assign left expr");
/* 146 */   public static final IElementType ASSIGN_RIGHT_EXPR = new FanElementType("assign right expr");
/* 147 */   public static final IElementType COND_EXPR = new FanElementType("assign expr");
/* 148 */   public static final IElementType LOGICAL_OR_EXPR = new FanElementType("logical OR expr");
/* 149 */   public static final IElementType LOGICAL_AND_EXPR = new FanElementType("logical AND expr");
/* 150 */   public static final IElementType EQUALITY_EXPR = new FanElementType("equality expr");
/* 151 */   public static final IElementType RELATIONAL_EXPR = new FanElementType("relational expr");
/* 152 */   public static final IElementType ELVIS_EXPR = new FanElementType("elvis expr");
/* 153 */   public static final IElementType RANGE_EXPR = new FanElementType("range expr");
/* 154 */   public static final IElementType BIT_OR_EXPR = new FanElementType("bit or expr");
/* 155 */   public static final IElementType BIT_AND_EXPR = new FanElementType("bit and expr");
/* 156 */   public static final IElementType SHIFT_EXPR = new FanElementType("shift expr");
/* 157 */   public static final IElementType ADD_EXPR = new FanElementType("add expr");
/* 158 */   public static final IElementType MULT_EXPR = new FanElementType("mult expr");
/* 159 */   public static final IElementType PAREN_EXPR = new FanElementType("parent expr");
/* 160 */   public static final IElementType CAST_EXPR = new FanElementType("cast expr");
/* 161 */   public static final IElementType GROUPED_EXPR = new FanElementType("grouped expr");
/* 162 */   public static final IElementType UNARY_EXPR = new FanElementType("unary expr");
/* 163 */   public static final IElementType PREFIX_EXPR = new FanElementType("prefix expr");
/* 164 */   public static final IElementType POSTFIX_EXPR = new FanElementType("postfix expr");
/* 165 */   public static final IElementType TERM_EXPR = new FanElementType("term expr");
/* 166 */   public static final IElementType TERM_CHAIN_EXPR = new FanElementType("termchain expr");
/* 167 */   public static final IElementType LITERAL = new FanElementType("literal");
/* 168 */   public static final IElementType THIS_REFERENCE_EXPRESSION = new FanElementType("this");
/* 169 */   public static final IElementType SUPER_REFERENCE_EXPRESSION = new FanElementType("super");
/* 170 */   public static final IElementType SIMPLE_LITERAL = new FanElementType("simple literal");
/* 171 */   public static final IElementType LIST_LITERAL = new FanElementType("list literal");
/* 172 */   public static final IElementType LIST_ITEM = new FanElementType("list item");
/* 173 */   public static final IElementType MAP_LITERAL = new FanElementType("map literal");
/* 174 */   public static final IElementType MAP_ITEM = new FanElementType("map item");
/* 175 */   public static final IElementType MAP_ITEM_KEY = new FanElementType("map item key");
/* 176 */   public static final IElementType MAP_ITEM_VALUE = new FanElementType("map item value");
/* 177 */   public static final IElementType ID_EXPR = new FanElementType("idExpr");
/* 178 */   public static final IElementType CLOSURE_EXPR = new FanElementType("closure");
/* 179 */   public static final IElementType CLOSURE_BODY = new FanElementType("closure body");
/* 180 */   public static final IElementType IT_BODY = new FanElementType("it body");
/* 181 */   public static final IElementType WITH_BLOCK_EXPR = new FanElementType("withBlock expr");
/* 182 */   public static final IElementType INDEX_EXPR = new FanElementType("index expr");
/*     */   
/* 184 */   public static final Collection<IElementType> BLOCK_ELEMENTS = Arrays.asList(new IElementType[] { METHOD_BODY, CLOSURE_BODY, FOR_BLOCK, WHILE_BLOCK, TRY_BLOCK, CATCH_BLOCK, FINALLY_BLOCK, STATIC_BLOCK });
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/FanElementTypes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */