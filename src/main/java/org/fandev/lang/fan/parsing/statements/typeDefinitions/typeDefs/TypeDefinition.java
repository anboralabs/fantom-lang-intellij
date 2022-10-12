/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TypeDefinition
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/*    */     boolean res;
/* 17 */     PsiBuilder.Marker tdMarker = builder.mark();
/* 18 */     Facet.parse(builder);
/* 19 */     ParserUtils.removeNls(builder);
/* 20 */     if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.CLASS_KEYWORD, new IElementType[] { FanTokenTypes.LBRACE })) {
/* 21 */       res = ClassDefinition.parse(builder);
/* 22 */       tdMarker.done((IElementType)FanElementTypes.CLASS_DEFINITION);
/* 23 */     } else if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.POD_KEYWORD, new IElementType[] { FanTokenTypes.LBRACE })) {
/*    */       
/* 25 */       res = PodDefinition.parse(builder);
/* 26 */       tdMarker.done((IElementType)FanElementTypes.POD_DEFINITION);
/* 27 */     } else if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.ENUM_KEYWORD, new IElementType[] { FanTokenTypes.LBRACE })) {
/* 28 */       res = EnumDefinition.parse(builder);
/* 29 */       tdMarker.done((IElementType)FanElementTypes.ENUM_DEFINITION);
/* 30 */     } else if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.MIXIN_KEYWORD, new IElementType[] { FanTokenTypes.LBRACE })) {
/* 31 */       res = MixinDefinition.parse(builder);
/* 32 */       tdMarker.done((IElementType)FanElementTypes.MIXIN_DEFINITION);
/*    */     } else {
/* 34 */       res = false;
/* 35 */       tdMarker.error(FanBundle.message("typedef.expected", new Object[0]));
/*    */     } 
/* 37 */     ParserUtils.removeNls(builder);
/* 38 */     return res;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/typeDefs/TypeDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */