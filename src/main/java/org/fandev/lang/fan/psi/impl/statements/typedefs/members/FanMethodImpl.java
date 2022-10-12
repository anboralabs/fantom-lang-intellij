/*    */ package org.fandev.lang.fan.psi.impl.statements.typedefs.members;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.StubBasedPsiElement;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
/*    */ import org.fandev.lang.fan.psi.stubs.FanMethodStub;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanMethodImpl
/*    */   extends FanMethodBaseImpl<FanMethodStub>
/*    */   implements FanMethod, StubBasedPsiElement<FanMethodStub>
/*    */ {
/*    */   public FanMethodImpl(FanMethodStub fanMethodStub, @NotNull IStubElementType iStubElementType) {
/* 19 */     super(fanMethodStub, iStubElementType);
/*    */   }
/*    */   
/*    */   public FanMethodImpl(ASTNode astNode) {
/* 23 */     super(astNode);
/*    */   }
/*    */   
/*    */   public boolean isConstructor() {
/* 27 */     return false;
/*    */   }
/*    */
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/members/FanMethodImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */