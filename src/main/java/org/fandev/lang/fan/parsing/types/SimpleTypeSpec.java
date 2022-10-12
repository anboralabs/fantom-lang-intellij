/*    */ package org.fandev.lang.fan.parsing.types;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.ReferenceElement;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleTypeSpec
/*    */ {
/*    */   public static TypeType parseSimpleType(PsiBuilder builder, boolean forLiteral) {
/* 32 */     if (FanTokenTypes.FAN_SYS_TYPE == builder.getTokenType())
/* 33 */       return parseBuiltInType(builder, forLiteral); 
/* 34 */     if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 35 */       return parseClassOrInterfaceType(builder, forLiteral);
/*    */     }
/* 37 */     return TypeType.NONE;
/*    */   }
/*    */   
/*    */   public static TypeType parseBuiltInType(PsiBuilder builder, boolean forLiteral) {
/* 41 */     PsiBuilder.Marker builtInTypeMarker = builder.mark();
/*    */     
/* 43 */     if (!ReferenceElement.parseReferenceElement(builder)) {
/* 44 */       builtInTypeMarker.drop();
/* 45 */       return TypeType.NONE;
/*    */     } 
/*    */     
/* 48 */     PsiBuilder.Marker arrMarker = builtInTypeMarker.precede();
/* 49 */     builtInTypeMarker.done(FanElementTypes.CLASS_TYPE_ELEMENT);
/* 50 */     TypeType result = TypeSpec.endOfTypeParse(builder, arrMarker, forLiteral, TypeType.SIMPE);
/*    */     
/* 52 */     return result;
/*    */   }
/*    */   
/*    */   static TypeType parseClassOrInterfaceType(PsiBuilder builder, boolean forLiteral) {
/* 56 */     PsiBuilder.Marker arrMarker = builder.mark();
/* 57 */     PsiBuilder.Marker typeElementMarker = builder.mark();
/*    */     
/* 59 */     if (!ReferenceElement.parseReferenceElement(builder)) {
/* 60 */       typeElementMarker.drop();
/* 61 */       arrMarker.rollbackTo();
/* 62 */       return TypeType.NONE;
/*    */     } 
/* 64 */     typeElementMarker.done(FanElementTypes.CLASS_TYPE_ELEMENT);
/* 65 */     return TypeSpec.endOfTypeParse(builder, arrMarker, forLiteral, TypeType.SIMPE);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/types/SimpleTypeSpec.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */