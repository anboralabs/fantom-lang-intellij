/*    */ package org.fandev.lang.fan.psi.stubs.impl;
/*    */ 
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.util.io.StringRef;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
/*    */ import org.fandev.lang.fan.psi.stubs.FanFieldStub;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanFieldStubImpl
/*    */   extends FanSlotStubImpl<FanField>
/*    */   implements FanFieldStub
/*    */ {
/*    */   public FanFieldStubImpl(StubElement parent, StringRef name, String[] facetNames) {
/* 17 */     super(parent, (IStubElementType)FanElementTypes.FIELD_DEFINITION, name, facetNames);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/impl/FanFieldStubImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */