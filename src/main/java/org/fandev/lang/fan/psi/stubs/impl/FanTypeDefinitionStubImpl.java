/*    */ package org.fandev.lang.fan.psi.stubs.impl;
/*    */ 
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubBase;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.util.io.StringRef;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanTypeDefinitionStubImpl
/*    */   extends StubBase<FanTypeDefinition>
/*    */   implements FanTypeDefinitionStub
/*    */ {
/*    */   private final StringRef myPodName;
/*    */   private final StringRef myName;
/*    */   
/*    */   public FanTypeDefinitionStubImpl(StubElement parent, IStubElementType elementType, StringRef name, StringRef podName) {
/* 21 */     super(parent, elementType);
/* 22 */     this.myName = name;
/* 23 */     this.myPodName = podName;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 27 */     return StringRef.toString(this.myName);
/*    */   }
/*    */   
/*    */   public String getPodName() {
/* 31 */     return StringRef.toString(this.myPodName);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/impl/FanTypeDefinitionStubImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */