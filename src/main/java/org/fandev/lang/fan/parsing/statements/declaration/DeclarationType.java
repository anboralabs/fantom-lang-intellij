/*    */ package org.fandev.lang.fan.parsing.statements.declaration;
/*    */ 
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum DeclarationType
/*    */ {
/* 12 */   CLASS(FanTokenTypes.CLASS_KEYWORD, FanTokenTypes.CLASS_MODIFIERS),
/* 13 */   MIXIN(FanTokenTypes.MIXIN_KEYWORD, FanTokenTypes.MIXIN_MODIFIERS),
/* 14 */   ENUM(FanTokenTypes.ENUM_KEYWORD, FanTokenTypes.PROTECTION),
/* 15 */   CONSTRUCTOR(FanTokenTypes.NEW_KEYWORD, FanTokenTypes.PROTECTION),
/* 16 */   INNER_SET(FanTokenTypes.PROTECTION),
/* 17 */   METHOD(FanTokenTypes.METHOD_MODIFIERS),
/* 18 */   FIELD(FanTokenTypes.FIELD_MODIFIERS);
/*    */   
/*    */   private final IElementType keyword;
/*    */   private final TokenSet modifiersSet;
/*    */   
/*    */   DeclarationType(IElementType keyword, TokenSet modifiersSet) {
/* 24 */     this.keyword = keyword;
/* 25 */     this.modifiersSet = modifiersSet;
/*    */   }
/*    */   
/*    */   DeclarationType(TokenSet modifiersSet) {
/* 29 */     this.keyword = null;
/* 30 */     this.modifiersSet = modifiersSet;
/*    */   }
/*    */   
/*    */   public IElementType getKeyword() {
/* 34 */     return this.keyword;
/*    */   }
/*    */   
/*    */   public TokenSet getModifiersSet() {
/* 38 */     return this.modifiersSet;
/*    */   }
/*    */   
/*    */   public String errorMessage() {
/* 42 */     StringBuilder builder = new StringBuilder("Modifiers ( ");
/* 43 */     this.modifiersSet.getTypes();
/* 44 */     boolean first = true;
/* 45 */     for (IElementType type : this.modifiersSet.getTypes()) {
/* 46 */       if (!first) {
/* 47 */         builder.append(", ");
/*    */       }
/* 49 */       builder.append(type.toString());
/* 50 */       first = false;
/*    */     } 
/* 52 */     builder.append(" )");
/* 53 */     if (getKeyword() != null) {
/* 54 */       builder.append(" or keyword ").append(getKeyword());
/*    */     }
/* 56 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/declaration/DeclarationType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */