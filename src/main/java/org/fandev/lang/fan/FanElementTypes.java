package org.fandev.lang.fan;


import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.*;
import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.fandev.lang.fan.psi.stubs.elements.*;
import org.fandev.lang.fan.types.FanFileElementType;

import java.util.Arrays;
import java.util.Collection;


public interface FanElementTypes {
    public static final IFileElementType FILE = (IFileElementType) new FanFileElementType(FantomLanguage.INSTANCE);


    public static final FanStubElementType<FanTypeDefinitionStub, FanClassDefinition> CLASS_DEFINITION = (FanStubElementType<FanTypeDefinitionStub, FanClassDefinition>) new FanClassDefinitionElementType();
    public static final IElementType CLASS_BODY = new FanElementType("CLASS_BODY");
    public static final IElementType CLASS_TYPE_ELEMENT = new FanElementType("CLASS_TYPE_ELEMENT");


    public static final FanStubElementType<FanTypeDefinitionStub, FanMixinDefinition> MIXIN_DEFINITION = (FanStubElementType<FanTypeDefinitionStub, FanMixinDefinition>) new FanMixinDefinitionElementType();
    public static final IElementType MIXIN_BODY = new FanElementType("MIXIN_BODY");


    public static final FanStubElementType<FanTypeDefinitionStub, FanEnumDefinition> ENUM_DEFINITION = (FanStubElementType<FanTypeDefinitionStub, FanEnumDefinition>) new FanEnumDefinitionElementType();
    public static final IElementType ENUM_BODY = new FanElementType("ENUM_BODY");
    public static final IElementType ENUM_VALUE = new FanElementType("ENUM_VALUE");


    public static final FanStubElementType<FanTypeDefinitionStub, FanPodDefinition> POD_DEFINITION = (FanStubElementType<FanTypeDefinitionStub, FanPodDefinition>) new FanPodDefinitionElementType();
    public static final IElementType POD_BODY = new FanElementType("POD_BODY");

    public static final IElementType STATIC_BLOCK = (IElementType) new FanMethodElementType("static block");

    public static final IElementType CTOR_DEFINITION = (IElementType) new FanConstructorElementType("CTOR_DEFINITION");
    public static final IElementType CTOR_CHAIN = new FanElementType("CTOR_CHAIN");

    public static final IElementType METHOD_DEFINITION = (IElementType) new FanMethodElementType("METHOD_DEFINITION");
    public static final IElementType PARAM_DEFAULT = new FanElementType("Parameter default value");

    public static final IElementType METHOD_BODY = new FanElementType("method block");

    public static final IElementType FIELD_DEFINITION = (IElementType) new FanFieldElementType("FIELD_DEFINITION");
    public static final IElementType FIELD_DEFAULT = new FanElementType("Field default value");
    public static final IElementType GETTER_SETTER_FIELD_DEFINITION = new FanElementType("GETTER_SETTER_FIELD_DEFINITION");
    public static final IElementType GETTER_FIELD_DEFINITION = new FanElementType("GETTER_FIELD_DEFINITION");
    public static final IElementType SETTER_FIELD_DEFINITION = new FanElementType("SETTER_FIELD_DEFINITION");

    public static final FanStubElementType<FanReferenceListStub, FanInheritanceClause> INHERITANCE_CLAUSE = (FanStubElementType<FanReferenceListStub, FanInheritanceClause>) new FanInheritanceClauseElementType();


    public static final IElementType NAME_ELEMENT = new FanElementType("name id");

    public static final IElementType REFERENCE_ELEMENT = new FanElementType("REFERENCE_EXPRESSION");

    public static final IElementType MODIFIERS = new FanElementType("MODIFIERS");

    public static final IElementType BUILT_IN_TYPE = new FanElementType("BUILT_IN_TYPE");

    public static final IElementType NONE = new FanElementType("NO_TOKEN");

    public static final IElementType WRONGWAY = new FanElementType("WRONG_WAY");

    public static final IElementType LCURLY = new FanElementType("{");

    public static final IElementType TYPE = new FanElementType("Full Type wrapper");
    public static final IElementType NULLABLE_TYPE = new FanElementType("mark for ?");

    public static final IElementType LIST_TYPE = new FanElementType("LIST_TYPE");

    public static final IElementType MAP_TYPE = new FanElementType("MAP_TYPE");

    public static final IElementType FUNC_TYPE = new FanElementType("FUNC_TYPE");
    public static final IElementType FORMALS = new FanElementType("FORMALS");
    public static final IElementType FORMAL = new FanElementType("FORMAL");

    public static final IElementType TYPE_PARAMETER_LIST = new FanElementType("TYPE_PARAMETER_LIST");

    public static final IElementType TYPE_PARAMETER = new FanElementType("TYPE_PARAMETER");

    public static final IElementType ARGUMENT_LIST = new FanElementType("args");
    public static final IElementType ARGUMENT_EXPR = new FanElementType("arg exp");

    public static final IElementType FACET = new FanElementType("facet declaration");
    public static final IElementType FACET_VALUE = new FanElementType("facet value");

    public static final IElementType EXPRESSION = new FanElementType("expression");
    public static final IElementType LOCAL_DEF_STATEMENT = new FanElementType("local def");
    public static final IElementType IT_ADD_STATEMENT = new FanElementType("it add");

    public static final IElementType FFI_NAME = new FanElementType("FFI name");
    public static final IElementType POD_REFERENCE = new FanElementType("Pod or Package Reference");
    public static final IElementType USING_AS_NAME = new FanElementType("Using as name");
    public static final IElementType USING_STATEMENT = new FanElementType("Using statement");
    public static final IElementType CONTROL_FLOW = new FanElementType("Control Flow");
    public static final IElementType FOR_STATEMENT = new FanElementType("For statement");
    public static final IElementType FOR_INIT_EXPR = new FanElementType("For init expression");
    public static final IElementType FOR_INIT_LOCAL_DEF = new FanElementType("For init local def");
    public static final IElementType FOR_CONDITION = new FanElementType("For condition");
    public static final IElementType FOR_REPEAT = new FanElementType("For repeat");
    public static final IElementType FOR_BLOCK = new FanElementType("For block");
    public static final IElementType IF_STATEMENT = new FanElementType("If statement");
    public static final IElementType CONDITION_EXPR = new FanElementType("Condition expression");
    public static final IElementType COND_TRUE_BLOCK = new FanElementType("If block");
    public static final IElementType COND_FALSE_BLOCK = new FanElementType("Else block");
    public static final IElementType RETURN_STATEMENT = new FanElementType("return");
    public static final IElementType THROW_STATEMENT = new FanElementType("throw");
    public static final IElementType SWITCH_STATEMENT = new FanElementType("Switch");
    public static final IElementType SWITCH_VALUE = new FanElementType("Switch value");
    public static final IElementType SWITCH_CASE = new FanElementType("Switch case");
    public static final IElementType SWITCH_CASE_VALUE = new FanElementType("Switch case value");
    public static final IElementType SWITCH_CASE_STATEMENT = new FanElementType("Switch case statement");
    public static final IElementType WHILE_STATEMENT = new FanElementType("while statement");
    public static final IElementType WHILE_CONDITION = new FanElementType("while condition");
    public static final IElementType WHILE_BLOCK = new FanElementType("while block");
    public static final IElementType TRY_STATEMENT = new FanElementType("try statement");
    public static final IElementType TRY_BLOCK = new FanElementType("try block");
    public static final IElementType CATCH_STATEMENT = new FanElementType("catch statement");
    public static final IElementType CATCH_DEFINITION = new FanElementType("catch definition");
    public static final IElementType CATCH_BLOCK = new FanElementType("catch block");
    public static final IElementType FINALLY_STATEMENT = new FanElementType("finally statement");
    public static final IElementType FINALLY_BLOCK = new FanElementType("finally block");

    public static final IElementType ASSIGN_EXPRESSION = new FanElementType("assign expr");
    public static final IElementType ASSIGN_LEFT_EXPR = new FanElementType("assign left expr");
    public static final IElementType ASSIGN_RIGHT_EXPR = new FanElementType("assign right expr");
    public static final IElementType COND_EXPR = new FanElementType("assign expr");
    public static final IElementType LOGICAL_OR_EXPR = new FanElementType("logical OR expr");
    public static final IElementType LOGICAL_AND_EXPR = new FanElementType("logical AND expr");
    public static final IElementType EQUALITY_EXPR = new FanElementType("equality expr");
    public static final IElementType RELATIONAL_EXPR = new FanElementType("relational expr");
    public static final IElementType ELVIS_EXPR = new FanElementType("elvis expr");
    public static final IElementType RANGE_EXPR = new FanElementType("range expr");
    public static final IElementType BIT_OR_EXPR = new FanElementType("bit or expr");
    public static final IElementType BIT_AND_EXPR = new FanElementType("bit and expr");
    public static final IElementType SHIFT_EXPR = new FanElementType("shift expr");
    public static final IElementType ADD_EXPR = new FanElementType("add expr");
    public static final IElementType MULT_EXPR = new FanElementType("mult expr");
    public static final IElementType PAREN_EXPR = new FanElementType("parent expr");
    public static final IElementType CAST_EXPR = new FanElementType("cast expr");
    public static final IElementType GROUPED_EXPR = new FanElementType("grouped expr");
    public static final IElementType UNARY_EXPR = new FanElementType("unary expr");
    public static final IElementType PREFIX_EXPR = new FanElementType("prefix expr");
    public static final IElementType POSTFIX_EXPR = new FanElementType("postfix expr");
    public static final IElementType TERM_EXPR = new FanElementType("term expr");
    public static final IElementType TERM_CHAIN_EXPR = new FanElementType("termchain expr");
    public static final IElementType LITERAL = new FanElementType("literal");
    public static final IElementType THIS_REFERENCE_EXPRESSION = new FanElementType("this");
    public static final IElementType SUPER_REFERENCE_EXPRESSION = new FanElementType("super");
    public static final IElementType SIMPLE_LITERAL = new FanElementType("simple literal");
    public static final IElementType LIST_LITERAL = new FanElementType("list literal");
    public static final IElementType LIST_ITEM = new FanElementType("list item");
    public static final IElementType MAP_LITERAL = new FanElementType("map literal");
    public static final IElementType MAP_ITEM = new FanElementType("map item");
    public static final IElementType MAP_ITEM_KEY = new FanElementType("map item key");
    public static final IElementType MAP_ITEM_VALUE = new FanElementType("map item value");
    public static final IElementType ID_EXPR = new FanElementType("idExpr");
    public static final IElementType CLOSURE_EXPR = new FanElementType("closure");
    public static final IElementType CLOSURE_BODY = new FanElementType("closure body");
    public static final IElementType IT_BODY = new FanElementType("it body");
    public static final IElementType WITH_BLOCK_EXPR = new FanElementType("withBlock expr");
    public static final IElementType INDEX_EXPR = new FanElementType("index expr");

    public static final Collection<IElementType> BLOCK_ELEMENTS = Arrays.asList(new IElementType[]{METHOD_BODY, CLOSURE_BODY, FOR_BLOCK, WHILE_BLOCK, TRY_BLOCK, CATCH_BLOCK, FINALLY_BLOCK, STATIC_BLOCK});
}
