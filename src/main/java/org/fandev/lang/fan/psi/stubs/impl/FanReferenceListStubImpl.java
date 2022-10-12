/*    */ package org.fandev.lang.fan.psi.stubs.impl;
/*    */ 
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubBase;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanReferenceList;
/*    */ import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanReferenceListStubImpl
/*    */   extends StubBase<FanReferenceList>
/*    */   implements FanReferenceListStub
/*    */ {
/*    */   private final String[] myRefNames;
/*    */   
/*    */   public FanReferenceListStubImpl(StubElement parent, IStubElementType elementType, String[] refNames) {
/* 19 */     super(parent, elementType);
/* 20 */     this.myRefNames = refNames;
/*    */   }
/*    */   
/*    */   public String[] getBaseClasses() {
/* 24 */     return this.myRefNames;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/impl/FanReferenceListStubImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */