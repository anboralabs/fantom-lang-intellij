/*    */ package org.fandev.lang.fan.parsing.auxiliary;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Separators
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 12 */     boolean result = false;
/* 13 */     while (!builder.eof() && FanTokenTypes.SEPARATOR.contains(builder.getTokenType())) {
/* 14 */       builder.advanceLexer();
/* 15 */       result = true;
/*    */     } 
/* 17 */     return result;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/auxiliary/Separators.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */