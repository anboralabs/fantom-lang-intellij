/*    */ package org.fandev.lang.fan.psi.impl.types;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiType;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.api.types.FanClassTypeElement;
/*    */ import org.fandev.lang.fan.psi.api.types.FanListTypeElement;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.fandev.lang.fan.psi.impl.FanListReferenceType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanListTypeElementImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanListTypeElement
/*    */ {
/*    */   public FanListTypeElementImpl(ASTNode astNode) {
/* 21 */     super(astNode);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiType getType() {
/* 26 */     FanClassTypeElement fanTypeElem = getTypeElement();
/* 27 */     return (PsiType)new FanListReferenceType(this, fanTypeElem.getType());
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public FanClassTypeElement getTypeElement() {
/* 32 */     return (FanClassTypeElement)findChildByClass(FanClassTypeElement.class);
/*    */   }
/*    */   
/*    */   public FanTypeDefinition getListType() {
/* 36 */     return getFanTypeByName("List");
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/types/FanListTypeElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */