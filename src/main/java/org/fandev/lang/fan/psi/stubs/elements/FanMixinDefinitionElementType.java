/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanMixinDefinition;
/*    */ import org.fandev.lang.fan.psi.impl.statements.typedefs.FanMixinDefinitionImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*    */ 
/*    */ 
/*    */ public class FanMixinDefinitionElementType
/*    */   extends FanTypeDefinitionElementType<FanMixinDefinition>
/*    */ {
/*    */   public FanMixinDefinitionElementType() {
/* 14 */     super("mixin definition");
/*    */   }
/*    */   
/*    */   public FanMixinDefinition createPsi(FanTypeDefinitionStub stub) {
/* 18 */     return (FanMixinDefinition)new FanMixinDefinitionImpl(stub);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanMixinDefinitionElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */