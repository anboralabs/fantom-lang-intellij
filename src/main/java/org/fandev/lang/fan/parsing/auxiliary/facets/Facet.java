/*    */ package org.fandev.lang.fan.parsing.auxiliary.facets;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.expression.Expression;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Facet
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 19 */     ParserUtils.removeNls(builder);
/* 20 */     while (FanTokenTypes.AT.equals(builder.getTokenType())) {
/* 21 */       PsiBuilder.Marker facetMarker = builder.mark();
/* 22 */       builder.advanceLexer();
/*    */       
/* 24 */       if (!ParserUtils.parseName(builder)) {
/* 25 */         facetMarker.drop();
/* 26 */         return false;
/*    */       } 
/* 28 */       if (FanTokenTypes.EQ.equals(builder.getTokenType())) {
/* 29 */         ParserUtils.advanceNoNls(builder);
/* 30 */         Expression.parseExpr(builder, FanTokenTypes.EOS, FanElementTypes.FACET_VALUE);
/*    */       } 
/* 32 */       facetMarker.done(FanElementTypes.FACET);
/* 33 */       ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/*    */     } 
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/auxiliary/facets/Facet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */