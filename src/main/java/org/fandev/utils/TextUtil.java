/*    */ package org.fandev.utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextUtil
/*    */ {
/*    */   public static final String EMPTY_STRING = "";
/*    */   
/*    */   public static boolean isEmpty(String s) {
/* 15 */     return (s == null || "".equals(s));
/*    */   }
/*    */   
/*    */   public static String getAsNotNull(String str) {
/* 19 */     return (str != null) ? str : "";
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/utils/TextUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */