/*    */ package org.fandev.lang.fan.parsing;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.lang.PsiParser;
/*    */ import com.intellij.openapi.diagnostic.Logger;
/*    */ import com.intellij.psi.text.BlockSupport;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.parsing.topLevel.CompilationUnit;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanParser
/*    */   implements PsiParser
/*    */ {
/* 18 */   private static final Logger logger = Logger.getInstance(FanParser.class.getName());
/*    */   
/*    */   @NotNull
/*    */   public ASTNode parse(IElementType root, PsiBuilder psiBuilder) {
/* 22 */     psiBuilder.setDebugMode(true);
/* 23 */     PsiBuilder.Marker rootMarker = psiBuilder.mark();
/*    */     
/* 25 */     CompilationUnit.parse(psiBuilder);
/*    */ 
/*    */     
/* 28 */     if (!psiBuilder.eof()) {
/* 29 */       PsiBuilder.Marker errorMark = psiBuilder.mark();
/* 30 */       while (!psiBuilder.eof()) {
/* 31 */         psiBuilder.advanceLexer();
/*    */       }
/* 33 */       errorMark.error(FanBundle.message("typedef.expected", new Object[0]));
/*    */     } 
/*    */     
/* 36 */     rootMarker.done(root);
/*    */     try {
/* 38 */       return psiBuilder.getTreeBuilt();
/* 39 */     } catch (BlockSupport.ReparsedSuccessfullyException e) {
/* 40 */       throw e;
/* 41 */     } catch (Throwable t) {
/* 42 */       StringBuilder sb = new StringBuilder();
/* 43 */       while (!psiBuilder.eof()) {
/* 44 */         sb.append(psiBuilder.getTokenText());
/* 45 */         psiBuilder.advanceLexer();
/*    */       } 
/* 47 */       logger.error("Parsing error, current offset is: " + psiBuilder.getCurrentOffset() + " Remaining text is: " + sb.toString(), t);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 52 */       throw new RuntimeException(t);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/FanParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */