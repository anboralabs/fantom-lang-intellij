/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanClassDefinition;
/*    */ import org.fandev.lang.fan.psi.impl.statements.typedefs.FanClassDefinitionImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*    */ 
/*    */ public class FanClassDefinitionElementType
/*    */   extends FanTypeDefinitionElementType<FanClassDefinition>
/*    */ {
/*    */   public FanClassDefinitionElementType() {
/* 13 */     super("class definition");
/*    */   }
/*    */   
/*    */   public FanClassDefinition createPsi(FanTypeDefinitionStub stub) {
/* 17 */     return (FanClassDefinition)new FanClassDefinitionImpl(stub);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanClassDefinitionElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */