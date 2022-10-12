/*    */ package org.fandev.lang.fan.types;
/*    */ 
/*    */

import com.intellij.lang.Language;
import com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.annotations.NonNls;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanFileElementType
/*    */   extends IStubFileElementType
/*    */ {
/*    */   public static final int VERSION = 1;
/*    */   
/*    */   public FanFileElementType(Language language) {
/* 16 */     super(language);
/*    */   }
/*    */   
/*    */   public FanFileElementType(@NonNls String s, Language language) {
/* 20 */     super(s, language);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getExternalId() {
/* 25 */     return getLanguage() + ":" + toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStubVersion() {
/* 30 */     return 1;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/types/FanFileElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */