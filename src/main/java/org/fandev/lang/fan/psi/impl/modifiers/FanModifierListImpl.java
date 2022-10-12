/*    */ package org.fandev.lang.fan.psi.impl.modifiers;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiAnnotation;
/*    */ import com.intellij.util.IncorrectOperationException;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.psi.api.modifiers.FanModifierList;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class FanModifierListImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanModifierList
/*    */ {
/*    */   public FanModifierListImpl(ASTNode astNode) {
/* 19 */     super(astNode);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasModifierProperty(String modifier) {
/* 25 */     if (modifier.equals("public")) {
/* 26 */       return (findChildByType(FanTokenTypes.PRIVATE_KEYWORD) == null && findChildByType(FanTokenTypes.PROTECTED_KEYWORD) == null && findChildByType(FanTokenTypes.INTERNAL_KEYWORD) == null);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 31 */     return hasExplicitModifier(modifier);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasExplicitModifier(String name) {
/* 36 */     if (name.equals("public")) return (findChildByType(FanTokenTypes.PUBLIC_KEYWORD) != null); 
/* 37 */     if (name.equals("abstract")) return (findChildByType(FanTokenTypes.ABSTRACT_KEYWORD) != null); 
/* 38 */     if (name.equals("native")) return (findChildByType(FanTokenTypes.NATIVE_KEYWORD) != null); 
/* 39 */     return hasOtherModifiers(name);
/*    */   }
/*    */   
/*    */   private boolean hasOtherModifiers(String name) {
/* 43 */     if (name.equals("private")) return (findChildByType(FanTokenTypes.PRIVATE_KEYWORD) != null); 
/* 44 */     if (name.equals("protected")) return (findChildByType(FanTokenTypes.PROTECTED_KEYWORD) != null); 
/* 45 */     if (name.equals("packageLocal")) return (findChildByType(FanTokenTypes.INTERNAL_KEYWORD) != null); 
/* 46 */     if (name.equals("static")) return (findChildByType(FanTokenTypes.STATIC_KEYWORD) != null); 
/* 47 */     if (name.equals("final")) return (findChildByType(FanTokenTypes.FINAL_KEYWORD) != null); 
/* 48 */     return (name.equals("volatile") && findChildByType(FanTokenTypes.VOLATILE_KEYWORD) != null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setModifierProperty(String name, boolean value) throws IncorrectOperationException {}
/*    */ 
/*    */   
/*    */   public void checkSetModifierProperty(String name, boolean value) throws IncorrectOperationException {}
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PsiAnnotation[] getAnnotations() {
/* 61 */     return PsiAnnotation.EMPTY_ARRAY;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public PsiAnnotation findAnnotation(@NotNull String qualifiedName) {
/* 66 */     return null;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiAnnotation[] getApplicableAnnotations() {
/* 71 */     return new PsiAnnotation[0];
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiAnnotation addAnnotation(@NotNull String qualifiedName) {
/* 76 */     return null;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 80 */     return "Modifiers";
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/modifiers/FanModifierListImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */