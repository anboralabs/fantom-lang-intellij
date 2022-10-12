/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
/*    */ import org.fandev.lang.fan.parsing.statements.Block;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotDefinition
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder, DeclarationType type, boolean isBuiltInType) {
/* 30 */     ParserUtils.removeNls(builder);
/*    */ 
/*    */     
/* 33 */     PsiBuilder.Marker rb = builder.mark();
/*    */ 
/*    */     
/* 36 */     Facet.parse(builder);
/*    */ 
/*    */     
/* 39 */     Set<IElementType> modifiers = new HashSet<IElementType>();
/* 40 */     while (FanTokenTypes.ALL_SLOT_MODIFIERS.contains(builder.getTokenType())) {
/* 41 */       modifiers.add(builder.getTokenType());
/*    */       
/* 43 */       ParserUtils.advanceNoNls(builder);
/* 44 */       if (type == DeclarationType.MIXIN && FanTokenTypes.ONCE_KEYWORD == builder.getTokenType()) {
/* 45 */         rb.error(FanBundle.message("mixins.cannot.declare.once.methods", new Object[0]));
/* 46 */         rb = builder.mark();
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 51 */     if (FanTokenTypes.LBRACE.equals(builder.getTokenType())) {
/*    */       
/* 53 */       rb.rollbackTo();
/* 54 */       PsiBuilder.Marker staticInitMark = builder.mark();
/* 55 */       if (!FanTokenTypes.STATIC_KEYWORD.equals(builder.getTokenType())) {
/* 56 */         staticInitMark.error(FanBundle.message("expecting.keyword.static", new Object[0]));
/* 57 */         return false;
/*    */       } 
/* 59 */       PsiBuilder.Marker idMark = builder.mark();
/* 60 */       ParserUtils.advanceNoNls(builder);
/* 61 */       idMark.done(FanElementTypes.NAME_ELEMENT);
/* 62 */       if (!FanTokenTypes.LBRACE.equals(builder.getTokenType())) {
/* 63 */         staticInitMark.error(FanBundle.message("expecting.static", new Object[0]));
/* 64 */         return false;
/*    */       } 
/* 66 */       Block.parse(builder, FanElementTypes.METHOD_BODY);
/* 67 */       staticInitMark.done(FanElementTypes.METHOD_DEFINITION);
/* 68 */       return true;
/* 69 */     }  if (FanTokenTypes.NEW_KEYWORD.equals(builder.getTokenType())) {
/*    */       
/* 71 */       if (type == DeclarationType.MIXIN) {
/* 72 */         rb.error(FanBundle.message("mixins.cannot.declare.constructors", new Object[0]));
/* 73 */         return false;
/* 74 */       }  if (type == DeclarationType.ENUM && 
/* 75 */         !modifiers.contains(FanTokenTypes.PRIVATE_KEYWORD)) {
/* 76 */         rb.error(FanBundle.message("enums.must.have.private.constructors", new Object[0]));
/* 77 */         return false;
/*    */       } 
/*    */       
/* 80 */       rb.rollbackTo();
/* 81 */       return ConstructorDefinition.parse(builder, isBuiltInType);
/* 82 */     }  if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.LPAR, new IElementType[] { FanTokenTypes.LBRACE, FanTokenTypes.SEMICOLON, FanTokenTypes.NLS, FanTokenTypes.COLON_EQ })) {
/*    */       
/* 84 */       rb.rollbackTo();
/* 85 */       return MethodDefinition.parse(builder, isBuiltInType);
/*    */     } 
/* 87 */     rb.rollbackTo();
/* 88 */     return FieldDefinition.parse(builder);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/members/SlotDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */