/*    */ package org.fandev.lang.fan.psi.impl.types;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiType;
/*    */ import org.fandev.lang.fan.psi.api.types.FanClassTypeElement;
/*    */ import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.fandev.lang.fan.psi.impl.FanClassReferenceType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanClassTypeElementImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanClassTypeElement
/*    */ {
/*    */   public FanClassTypeElementImpl(ASTNode astNode) {
/* 19 */     super(astNode);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 23 */     return "Class Type element";
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public FanCodeReferenceElement getReferenceElement() {
/* 28 */     return (FanCodeReferenceElement)findChildByClass(FanCodeReferenceElement.class);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiType getType() {
/* 33 */     return (PsiType)new FanClassReferenceType(getReferenceElement());
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/types/FanClassTypeElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */