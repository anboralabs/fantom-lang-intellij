/*    */ package org.fandev.lang.fan;
/*    */ 
/*    */

import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import org.jetbrains.annotations.NotNull;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FanStubElementType<S extends StubElement<?>, T extends PsiElement>
/*    */   extends IStubElementType<S, T>
/*    */ {
/*    */   public FanStubElementType(@NotNull String debugName) {
/* 15 */     super(debugName, FantomLanguage.INSTANCE);
/*    */   }
/*    */   
/*    */   public String getExternalId() {
/* 19 */     return "fan." + toString();
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/FanStubElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */