/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.util.io.StringRef;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
/*    */ import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanFieldImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanFieldStub;
/*    */ import org.fandev.lang.fan.psi.stubs.FanSlotStub;
/*    */ import org.fandev.lang.fan.psi.stubs.impl.FanFieldStubImpl;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class FanFieldElementType
/*    */   extends FanSlotElementType<FanField, FanFieldStub>
/*    */ {
/*    */   public FanFieldElementType(@NotNull String debugName) {
/* 19 */     super(debugName);
/*    */   }
/*    */   
/*    */   public FanField createPsi(FanFieldStub stub) {
/* 23 */     return (FanField)new FanFieldImpl(stub, (IStubElementType)FanElementTypes.FIELD_DEFINITION);
/*    */   }
/*    */   
/*    */   protected FanFieldStub createStubImpl(StubElement element, StringRef name, String[] facets) {
/* 27 */     return (FanFieldStub)new FanFieldStubImpl(element, name, facets);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanFieldElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */