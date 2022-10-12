/*    */ package org.fandev.lang.fan.parsing.auxiliary.modifiers;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Modifier
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder, DeclarationType stmtType) {
/* 13 */     if (stmtType.getModifiersSet().contains(builder.getTokenType())) {
/* 14 */       ParserUtils.advanceNoNls(builder);
/* 15 */       return true;
/*    */     } 
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/auxiliary/modifiers/Modifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */