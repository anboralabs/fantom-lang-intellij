/*    */ package org.fandev.lang.fan.psi.impl.statements.typedefs.members;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.StubBasedPsiElement;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
/*    */ import org.fandev.lang.fan.psi.stubs.FanConstructorStub;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanConstructorImpl
/*    */   extends FanMethodBaseImpl<FanConstructorStub>
/*    */   implements FanConstructor, StubBasedPsiElement<FanConstructorStub>
/*    */ {
/*    */   public FanConstructorImpl(FanConstructorStub fanMethodStub, @NotNull IStubElementType iStubElementType) {
/* 20 */     super(fanMethodStub, iStubElementType);
/*    */   }
/*    */   
/*    */   public FanConstructorImpl(ASTNode astNode) {
/* 24 */     super(astNode);
/*    */   }
/*    */   
/*    */   public boolean isConstructor() {
/* 28 */     return true;
/*    */   }
/*    */
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/members/FanConstructorImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */