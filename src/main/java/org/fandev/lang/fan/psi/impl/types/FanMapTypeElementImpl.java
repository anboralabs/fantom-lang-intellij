/*    */ package org.fandev.lang.fan.psi.impl.types;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiType;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.api.types.FanMapTypeElement;
/*    */ import org.fandev.lang.fan.psi.api.types.FanTypeElement;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.fandev.lang.fan.psi.impl.FanMapType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanMapTypeElementImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanMapTypeElement
/*    */ {
/*    */   public FanMapTypeElementImpl(ASTNode astNode) {
/* 20 */     super(astNode);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiType getType() {
/* 25 */     FanTypeElement[] keyValueTypes = (FanTypeElement[])findChildrenByClass(FanTypeElement.class);
/* 26 */     if (keyValueTypes.length == 2) {
/* 27 */       return (PsiType)new FanMapType(this, keyValueTypes[0], keyValueTypes[1]);
/*    */     }
/* 29 */     return null;
/*    */   }
/*    */   
/*    */   public FanTypeDefinition getMapType() {
/* 33 */     return getFanTypeByName("Map");
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/types/FanMapTypeElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */