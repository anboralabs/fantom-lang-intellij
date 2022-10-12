/*    */ package org.fandev.lang.fan.parsing.topLevel;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs.TypeDefinition;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CompilationUnit
/*    */ {
/*    */   public static void parse(PsiBuilder builder) {
/* 29 */     if (FanTokenTypes.SHABENG == builder.getTokenType()) {
/* 30 */       PsiBuilder.Marker shBeng = builder.mark();
/* 31 */       if (!ParserUtils.advanceTo(builder, FanTokenTypes.EOL)) {
/* 32 */         shBeng.error(FanBundle.message("separator.expected", new Object[0]));
/*    */         return;
/*    */       } 
/* 35 */       shBeng.done(FanTokenTypes.SHABENG);
/*    */     } 
/*    */     
/* 38 */     ParserUtils.removeNls(builder);
/*    */     
/* 40 */     while (FanTokenTypes.USING_KEYWORD == builder.getTokenType()) {
/* 41 */       parseUsing(builder);
/*    */     }
/*    */     
/* 44 */     while (!builder.eof()) {
/* 45 */       if (!TypeDefinition.parse(builder)) {
/* 46 */         ParserUtils.cleanAfterErrorInBlock(builder);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void parseUsing(PsiBuilder builder) {
/* 52 */     PsiBuilder.Marker usingStatement = builder.mark();
/* 53 */     PsiBuilder.Marker usingKeyword = builder.mark();
/* 54 */     builder.advanceLexer();
/* 55 */     usingKeyword.done(FanTokenTypes.USING_KEYWORD);
/* 56 */     if (FanTokenTypes.LBRACKET.equals(builder.getTokenType())) {
/*    */       
/* 58 */       builder.advanceLexer();
/* 59 */       PsiBuilder.Marker ffiMark = builder.mark();
/* 60 */       if (ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]))) {
/* 61 */         ffiMark.done(FanElementTypes.FFI_NAME);
/*    */       } else {
/* 63 */         ffiMark.drop();
/* 64 */       }  ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
/*    */     } 
/*    */ 
/*    */     
/* 68 */     PsiBuilder.Marker podIdExpr = builder.mark();
/* 69 */     PsiBuilder.Marker podRefMark = builder.mark();
/*    */     while (true) {
/* 71 */       ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]));
/* 72 */       if (!ParserUtils.getToken(builder, FanTokenTypes.DOT)) {
/* 73 */         podRefMark.done(FanElementTypes.POD_REFERENCE);
/*    */         
/* 75 */         if (ParserUtils.getToken(builder, FanTokenTypes.COLON_COLON)) {
/* 76 */           PsiBuilder.Marker usingType = builder.mark();
/* 77 */           PsiBuilder.Marker m = builder.mark();
/* 78 */           ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]));
/* 79 */           m.done(FanElementTypes.NAME_ELEMENT);
/* 80 */           usingType.done(FanElementTypes.ID_EXPR);
/*    */         } 
/* 82 */         podIdExpr.done(FanElementTypes.ID_EXPR);
/*    */         
/* 84 */         if (ParserUtils.getToken(builder, FanTokenTypes.AS_KEYWORD)) {
/* 85 */           PsiBuilder.Marker m = builder.mark();
/* 86 */           ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]));
/* 87 */           m.done(FanElementTypes.USING_AS_NAME);
/*    */         } 
/* 89 */         usingStatement.done(FanElementTypes.USING_STATEMENT);
/*    */         
/* 91 */         if (!FanTokenTypes.EOL.contains(builder.getTokenType())) {
/* 92 */           builder.error(FanBundle.message("separator.expected", new Object[0]));
/* 93 */           ParserUtils.advanceTo(builder, FanTokenTypes.EOL);
/*    */         } else {
/* 95 */           builder.advanceLexer();
/*    */         } 
/* 97 */         ParserUtils.removeNls(builder);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/topLevel/CompilationUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */