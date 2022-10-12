/*    */ package org.fandev.lang.fan.psi.stubs.impl;
/*    */ 
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubBase;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.util.io.StringRef;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
/*    */ import org.fandev.lang.fan.psi.stubs.FanSlotStub;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FanSlotStubImpl<T extends FanSlot>
/*    */   extends StubBase<T>
/*    */   implements FanSlotStub<T>
/*    */ {
/*    */   protected final StringRef myName;
/*    */   protected final String[] facetNames;
/*    */   
/*    */   public FanSlotStubImpl(StubElement element, IStubElementType type, StringRef name, String[] facetNames) {
/* 35 */     super(element, type);
/* 36 */     this.myName = name;
/* 37 */     this.facetNames = facetNames;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 41 */     return this.myName.getString();
/*    */   }
/*    */   
/*    */   public String[] getFacetNames() {
/* 45 */     return this.facetNames;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/impl/FanSlotStubImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */