/*    */ package org.fandev.lang.fan.psi.impl.statements.typedefs;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.StubBasedPsiElement;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanInheritanceClause;
/*    */ import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanInheritanceClauseImpl
/*    */   extends FanBaseElementImpl<FanReferenceListStub>
/*    */   implements FanInheritanceClause, StubBasedPsiElement<FanReferenceListStub>
/*    */ {
/*    */   public FanInheritanceClauseImpl(ASTNode astNode) {
/* 21 */     super(astNode);
/*    */   }
/*    */   
/*    */   public FanInheritanceClauseImpl(FanReferenceListStub fanReferenceListStub) {
/* 25 */     super(fanReferenceListStub, (IStubElementType)FanElementTypes.INHERITANCE_CLAUSE);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     return "Inheritance clause";
/*    */   }
/*    */   
/*    */   public FanCodeReferenceElement[] getReferenceElements() {
/* 33 */     return (FanCodeReferenceElement[])findChildrenByClass(FanCodeReferenceElement.class);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/FanInheritanceClauseImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */