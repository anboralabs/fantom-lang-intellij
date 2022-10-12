/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanPodDefinition;
/*    */ import org.fandev.lang.fan.psi.impl.statements.typedefs.FanPodDefinitionImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanPodDefinitionElementType
/*    */   extends FanTypeDefinitionElementType<FanPodDefinition>
/*    */ {
/*    */   public FanPodDefinitionElementType() {
/* 17 */     super("pod definition");
/*    */   }
/*    */   
/*    */   public FanPodDefinition createPsi(FanTypeDefinitionStub stub) {
/* 21 */     return (FanPodDefinition)new FanPodDefinitionImpl(stub);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanPodDefinitionElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */