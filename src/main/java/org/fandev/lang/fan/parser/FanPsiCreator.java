/*    */ package org.fandev.lang.fan.parser;
/*    */

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.impl.FanIdentifierImpl;
import org.fandev.lang.fan.psi.impl.modifiers.FanModifierListImpl;
import org.fandev.lang.fan.psi.impl.statements.FanDefaultValueImpl;
import org.fandev.lang.fan.psi.impl.statements.FanVariableImpl;
import org.fandev.lang.fan.psi.impl.statements.arguments.FanArgumentImpl;
import org.fandev.lang.fan.psi.impl.statements.arguments.FanArgumentListImpl;
import org.fandev.lang.fan.psi.impl.statements.blocks.FanPsiCodeBlockImpl;
import org.fandev.lang.fan.psi.impl.statements.expressions.*;
import org.fandev.lang.fan.psi.impl.statements.params.FanFormalImpl;
import org.fandev.lang.fan.psi.impl.statements.params.FanFormalsImpl;
import org.fandev.lang.fan.psi.impl.statements.params.FanParameterImpl;
import org.fandev.lang.fan.psi.impl.statements.params.FanParameterListImpl;
import org.fandev.lang.fan.psi.impl.statements.typedefs.*;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanConstructorImpl;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanEnumValueImpl;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanFieldImpl;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanMethodImpl;
import org.fandev.lang.fan.psi.impl.types.*;
/*    */
/*    */

/*    */
/*    */ public class FanPsiCreator {
/*    */   public static PsiElement createElement(ASTNode node) {
/* 32 */     IElementType elem = node.getElementType();
/*    */     
/* 34 */     if (elem == FanElementTypes.CLASS_DEFINITION) return (PsiElement)new FanClassDefinitionImpl(node); 
/* 35 */     if (elem == FanElementTypes.MIXIN_DEFINITION) return (PsiElement)new FanMixinDefinitionImpl(node);
/* 36 */     if (elem == FanElementTypes.ENUM_DEFINITION) return (PsiElement)new FanEnumDefinitionImpl(node); 
/* 37 */     if (elem == FanElementTypes.POD_DEFINITION) return (PsiElement)new FanPodDefinitionImpl(node);
/*    */     
/* 39 */     if (elem == FanElementTypes.CLASS_BODY || elem == FanElementTypes.ENUM_BODY || elem == FanElementTypes.MIXIN_BODY) return (PsiElement)new FanTypeDefinitionBodyImpl(node);
/*    */     
/* 41 */     if (elem == FanElementTypes.STATIC_BLOCK) return (PsiElement)new FanMethodImpl(node); 
/* 42 */     if (elem == FanElementTypes.CTOR_DEFINITION) return (PsiElement)new FanConstructorImpl(node); 
/* 43 */     if (elem == FanElementTypes.METHOD_DEFINITION) return (PsiElement)new FanMethodImpl(node);
/*    */     
/* 45 */     if (FanElementTypes.BLOCK_ELEMENTS.contains(elem)) return (PsiElement)new FanPsiCodeBlockImpl(node);
/*    */     
/* 47 */     if (elem == FanElementTypes.FIELD_DEFINITION) return (PsiElement)new FanFieldImpl(node); 
/* 48 */     if (elem == FanElementTypes.ENUM_VALUE) return (PsiElement)new FanEnumValueImpl(node);
/*    */     
/* 50 */     if (elem == FanElementTypes.ID_EXPR) return (PsiElement)new FanReferenceExpressionImpl(node);
/*    */     
/* 52 */     if (elem == FanElementTypes.REFERENCE_ELEMENT) return (PsiElement)new FanCodeReferenceElementImpl(node);
/*    */     
/* 54 */     if (elem == FanElementTypes.CLASS_TYPE_ELEMENT) return (PsiElement)new FanClassTypeElementImpl(node);
/*    */     
/* 56 */     if (elem == FanElementTypes.LIST_TYPE) return (PsiElement)new FanListTypeElementImpl(node);
/*    */     
/* 58 */     if (elem == FanElementTypes.FUNC_TYPE) return (PsiElement)new FanFuncTypeElementImpl(node);
/*    */     
/* 60 */     if (elem == FanElementTypes.MAP_TYPE) return (PsiElement)new FanMapTypeElementImpl(node);
/*    */     
/* 62 */     if (elem == FanElementTypes.INHERITANCE_CLAUSE) return (PsiElement)new FanInheritanceClauseImpl(node);
/*    */     
/* 64 */     if (elem == FanElementTypes.MODIFIERS) return (PsiElement)new FanModifierListImpl(node);
/*    */     
/* 66 */     if (elem == FanElementTypes.TYPE_PARAMETER_LIST) return (PsiElement)new FanParameterListImpl(node);
/*    */     
/* 68 */     if (elem == FanElementTypes.TYPE_PARAMETER) return (PsiElement)new FanParameterImpl(node);
/*    */     
/* 70 */     if (elem == FanElementTypes.LOCAL_DEF_STATEMENT) return (PsiElement)new FanVariableImpl(node);
/*    */     
/* 72 */     if (elem == FanElementTypes.THIS_REFERENCE_EXPRESSION) return (PsiElement)new FanThisReferenceExpressionImpl(node);
/*    */     
/* 74 */     if (elem == FanElementTypes.SUPER_REFERENCE_EXPRESSION) return (PsiElement)new FanSuperReferenceExpressionImpl(node);
/*    */     
/* 76 */     if (elem == FanElementTypes.POD_REFERENCE) return (PsiElement)new PodReferenceExpressionImpl(node);
/*    */     
/* 78 */     if (elem == FanElementTypes.CLOSURE_EXPR) return (PsiElement)new FanClosureExpressionImpl(node);
/*    */     
/* 80 */     if (elem == FanElementTypes.FORMALS) return (PsiElement)new FanFormalsImpl(node);
/*    */     
/* 82 */     if (elem == FanElementTypes.FORMAL) return (PsiElement)new FanFormalImpl(node);
/*    */     
/* 84 */     if (elem == FanElementTypes.PARAM_DEFAULT) return (PsiElement)new FanDefaultValueImpl(node);
/*    */     
/* 86 */     if (elem == FanElementTypes.INDEX_EXPR) return (PsiElement)new FanIndexExpressionImpl(node);
/*    */     
/* 88 */     if (elem == FanElementTypes.ARGUMENT_LIST) return (PsiElement)new FanArgumentListImpl(node);
/*    */     
/* 90 */     if (elem == FanElementTypes.ARGUMENT_EXPR) return (PsiElement)new FanArgumentImpl(node);
/*    */     
/* 92 */     if (elem == FanElementTypes.NAME_ELEMENT) return (PsiElement)new FanIdentifierImpl(node);
/*    */     
/* 94 */     return (PsiElement)new ASTWrapperPsiElement(node);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parser/FanPsiCreator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */