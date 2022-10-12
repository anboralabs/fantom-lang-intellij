/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InheritanceClause
/*    */ {
/*    */   public static IElementType parse(PsiBuilder builder) {
/* 16 */     PsiBuilder.Marker sccMarker = builder.mark();
/*    */     
/* 18 */     if (!ParserUtils.getToken(builder, FanTokenTypes.COLON)) {
/* 19 */       sccMarker.rollbackTo();
/* 20 */       return FanElementTypes.NONE;
/*    */     } 
/*    */     
/*    */     do {
/* 24 */       ParserUtils.removeNls(builder);
/* 25 */       if (!ReferenceElement.parseReferenceElement(builder)) {
/* 26 */         sccMarker.rollbackTo();
/* 27 */         return FanElementTypes.WRONGWAY;
/*    */       } 
/* 29 */       ParserUtils.removeNls(builder);
/* 30 */     } while (ParserUtils.getToken(builder, FanTokenTypes.COMMA));
/*    */     
/* 32 */     sccMarker.done((IElementType)FanElementTypes.INHERITANCE_CLAUSE);
/* 33 */     return (IElementType)FanElementTypes.INHERITANCE_CLAUSE;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/InheritanceClause.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */