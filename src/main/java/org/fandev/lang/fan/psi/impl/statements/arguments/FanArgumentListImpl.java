/*    */ package org.fandev.lang.fan.psi.impl.statements.arguments;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import org.fandev.lang.fan.psi.api.statements.arguments.FanArgument;
/*    */ import org.fandev.lang.fan.psi.api.statements.arguments.FanArgumentList;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanArgumentListImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanArgumentList
/*    */ {
/*    */   public FanArgumentListImpl(ASTNode astNode) {
/* 17 */     super(astNode);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return "Arguments";
/*    */   }
/*    */   
/*    */   public FanArgument[] getArguments() {
/* 26 */     return (FanArgument[])findChildrenByClass(FanArgument.class);
/*    */   }
/*    */   
/*    */   public int indexOf(@NotNull FanArgument arg) {
/* 30 */     FanArgument[] arguments = getArguments();
/* 31 */     for (int index = 0; index < arguments.length; index++) {
/* 32 */       if (arguments[index].equals(arg)) return index; 
/*    */     } 
/* 34 */     return -1;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/arguments/FanArgumentListImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */