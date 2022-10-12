/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReferenceElement
/*    */ {
/*    */   public static boolean parseReferenceElement(PsiBuilder builder) {
/* 14 */     PsiBuilder.Marker refelMark = builder.mark();
/* 15 */     boolean res = parse(builder);
/* 16 */     if (res && FanTokenTypes.COLON_COLON == builder.getTokenType()) {
/*    */       
/* 18 */       builder.advanceLexer();
/* 19 */       res = parse(builder);
/*    */     } 
/* 21 */     if (res) {
/* 22 */       refelMark.done(FanElementTypes.REFERENCE_ELEMENT);
/*    */     } else {
/* 24 */       refelMark.drop();
/*    */     } 
/* 26 */     return res;
/*    */   }
/*    */   
/*    */   public static boolean parse(PsiBuilder builder) {
/* 30 */     if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 31 */       builder.advanceLexer();
/* 32 */       return true;
/*    */     } 
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/ReferenceElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */