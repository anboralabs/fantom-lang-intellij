/*    */ package org.fandev.lang.fan.psi.stubs.index;
/*    */ 
/*    */ import com.intellij.psi.stubs.StringStubIndexExtension;
/*    */ import com.intellij.psi.stubs.StubIndexKey;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanReferenceList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanDirectInheritorsIndex
/*    */   extends StringStubIndexExtension<FanReferenceList>
/*    */ {
/* 14 */   public static final StubIndexKey<String, FanReferenceList> KEY = StubIndexKey.createIndexKey("fan.class.super");
/*    */   
/*    */   public StubIndexKey<String, FanReferenceList> getKey() {
/* 17 */     return KEY;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/index/FanDirectInheritorsIndex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */