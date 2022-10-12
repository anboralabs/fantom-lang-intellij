/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
/*    */ import org.fandev.lang.fan.psi.impl.statements.typedefs.FanEnumDefinitionImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanEnumDefinitionElementType
/*    */   extends FanTypeDefinitionElementType<FanEnumDefinition>
/*    */ {
/*    */   public FanEnumDefinitionElementType() {
/* 16 */     super("enum definition");
/*    */   }
/*    */   
/*    */   public FanEnumDefinition createPsi(FanTypeDefinitionStub stub) {
/* 20 */     return (FanEnumDefinition)new FanEnumDefinitionImpl(stub);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanEnumDefinitionElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */