/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.util.io.StringRef;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
/*    */ import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanMethodImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanMethodStub;
/*    */ import org.fandev.lang.fan.psi.stubs.FanSlotStub;
/*    */ import org.fandev.lang.fan.psi.stubs.impl.FanMethodStubImpl;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class FanMethodElementType
/*    */   extends FanSlotElementType<FanMethod, FanMethodStub>
/*    */ {
/*    */   public FanMethodElementType(@NotNull String debugName) {
/* 19 */     super(debugName);
/*    */   }
/*    */   
/*    */   public FanMethod createPsi(FanMethodStub stub) {
/* 23 */     return (FanMethod)new FanMethodImpl(stub, (IStubElementType)FanElementTypes.METHOD_DEFINITION);
/*    */   }
/*    */   
/*    */   protected FanMethodStubImpl createStubImpl(StubElement element, StringRef name, String[] facets) {
/* 27 */     return new FanMethodStubImpl(element, name, facets);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanMethodElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */