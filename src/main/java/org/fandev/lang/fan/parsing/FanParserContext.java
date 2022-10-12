/*    */ package org.fandev.lang.fan.parsing;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.openapi.util.Key;
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
/*    */ public class FanParserContext
/*    */ {
/* 27 */   private static final Key<FanParserContext> KEY = new Key("FanParser");
/*    */   
/*    */   public static FanParserContext get(PsiBuilder builder) {
/* 30 */     FanParserContext context = (FanParserContext)builder.getUserData(KEY);
/* 31 */     if (context == null) {
/* 32 */       context = new FanParserContext();
/* 33 */       builder.putUserData(KEY, context);
/*    */     } 
/* 35 */     return context;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/FanParserContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */