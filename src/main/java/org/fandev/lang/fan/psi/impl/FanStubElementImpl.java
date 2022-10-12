/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.extapi.psi.StubBasedPsiElementBase;
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import org.fandev.lang.fan.psi.FanElement;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class FanStubElementImpl<T extends StubElement>
/*    */   extends StubBasedPsiElementBase<T>
/*    */   implements FanElement
/*    */ {
/*    */   public FanStubElementImpl(T t, @NotNull IStubElementType iStubElementType) {
/* 16 */     super(t, iStubElementType);
/*    */   }
/*    */   
/*    */   public FanStubElementImpl(ASTNode astNode) {
/* 20 */     super(astNode);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanStubElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */