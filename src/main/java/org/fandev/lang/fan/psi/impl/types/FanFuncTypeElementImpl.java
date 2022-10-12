/*    */ package org.fandev.lang.fan.psi.impl.types;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiType;
/*    */ import org.fandev.lang.fan.psi.api.statements.params.FanFormals;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
/*    */ import org.fandev.lang.fan.psi.api.types.FanTypeElement;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.fandev.lang.fan.psi.impl.FanFuncType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanFuncTypeElementImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanFuncTypeElement
/*    */ {
/*    */   public FanFuncTypeElementImpl(ASTNode astNode) {
/* 22 */     super(astNode);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiType getType() {
/* 27 */     return (PsiType)new FanFuncType(this);
/*    */   }
/*    */   
/*    */   public FanFormals getFormals() {
/* 31 */     return (FanFormals)findChildByClass(FanFormals.class);
/*    */   }
/*    */   
/*    */   public FanTypeElement getReturnType() {
/* 35 */     FanTypeElement returnType = (FanTypeElement)findChildByClass(FanTypeElement.class);
/* 36 */     if (returnType == null);
/*    */ 
/*    */     
/* 39 */     return returnType;
/*    */   }
/*    */   
/*    */   public FanTypeDefinition getFuncType() {
/* 43 */     return getFanTypeByName("Func");
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/types/FanFuncTypeElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */