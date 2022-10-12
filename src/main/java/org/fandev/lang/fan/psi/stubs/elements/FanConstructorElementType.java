/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.util.io.StringRef;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
/*    */ import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanConstructorImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanConstructorStub;
/*    */ import org.fandev.lang.fan.psi.stubs.FanSlotStub;
/*    */ import org.fandev.lang.fan.psi.stubs.impl.FanConstructorStubImpl;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class FanConstructorElementType
/*    */   extends FanSlotElementType<FanConstructor, FanConstructorStub>
/*    */ {
/*    */   public FanConstructorElementType(@NotNull String debugName) {
/* 19 */     super(debugName);
/*    */   }
/*    */   
/*    */   public FanConstructor createPsi(FanConstructorStub stub) {
/* 23 */     return (FanConstructor)new FanConstructorImpl(stub, (IStubElementType)FanElementTypes.CTOR_DEFINITION);
/*    */   }
/*    */   
/*    */   protected FanConstructorStub createStubImpl(StubElement element, StringRef name, String[] facets) {
/* 27 */     return (FanConstructorStub)new FanConstructorStubImpl(element, name, facets);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanConstructorElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */