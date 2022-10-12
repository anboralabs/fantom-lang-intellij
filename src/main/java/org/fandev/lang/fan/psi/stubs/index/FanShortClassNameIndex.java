/*    */ package org.fandev.lang.fan.psi.stubs.index;
/*    */ 
/*    */ import com.intellij.psi.stubs.StringStubIndexExtension;
/*    */ import com.intellij.psi.stubs.StubIndexKey;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
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
/*    */ 
/*    */ 
/*    */ public class FanShortClassNameIndex
/*    */   extends StringStubIndexExtension<FanTypeDefinition>
/*    */ {
/* 28 */   public static final StubIndexKey<String, FanTypeDefinition> KEY = StubIndexKey.createIndexKey("fan.class.shortName");
/*    */ 
/*    */   
/*    */   public StubIndexKey<String, FanTypeDefinition> getKey() {
/* 32 */     return KEY;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/index/FanShortClassNameIndex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */