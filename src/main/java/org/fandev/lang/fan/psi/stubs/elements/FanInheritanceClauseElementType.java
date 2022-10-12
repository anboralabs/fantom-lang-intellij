/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.IndexSink;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.psi.stubs.StubInputStream;
/*    */ import com.intellij.psi.stubs.StubOutputStream;
/*    */ import com.intellij.util.Function;
/*    */ import com.intellij.util.containers.ContainerUtil;
/*    */ import java.io.IOException;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanStubElementType;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanInheritanceClause;
/*    */ import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
/*    */ import org.fandev.lang.fan.psi.impl.statements.typedefs.FanInheritanceClauseImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;
/*    */ import org.fandev.lang.fan.psi.stubs.impl.FanReferenceListStubImpl;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanInheritanceClauseElementType
/*    */   extends FanStubElementType<FanReferenceListStub, FanInheritanceClause>
/*    */ {
/*    */   public FanInheritanceClauseElementType() {
/* 27 */     super("INHERITANCE_CLAUSE");
/*    */   }
/*    */   
/*    */   public FanInheritanceClause createPsi(FanReferenceListStub stub) {
/* 31 */     return (FanInheritanceClause)new FanInheritanceClauseImpl(stub);
/*    */   }
/*    */   
/*    */   public FanReferenceListStub createStub(FanInheritanceClause psi, StubElement parentStub) {
/* 35 */     FanCodeReferenceElement[] elements = psi.getReferenceElements();
/* 36 */     String[] refNames = (String[])ContainerUtil.map(elements, new Function<FanCodeReferenceElement, String>() {
/*    */           @Nullable
/*    */           public String fun(FanCodeReferenceElement element) {
/* 39 */             return element.getReferenceName();
/*    */           }
/*    */         },  new String[elements.length]);
/*    */     
/* 43 */     return (FanReferenceListStub)new FanReferenceListStubImpl(parentStub, (IStubElementType)FanElementTypes.INHERITANCE_CLAUSE, refNames);
/*    */   }
/*    */   
/*    */   public void serialize(FanReferenceListStub stub, StubOutputStream dataStream) throws IOException {
/* 47 */     String[] names = stub.getBaseClasses();
/* 48 */     dataStream.writeByte(names.length);
/* 49 */     for (String s : names) {
/* 50 */       dataStream.writeName(s);
/*    */     }
/*    */   }
/*    */   
/*    */   public FanReferenceListStub deserialize(StubInputStream dataStream, StubElement parentStub) throws IOException {
/* 55 */     byte b = dataStream.readByte();
/* 56 */     String[] names = new String[b];
/* 57 */     for (int i = 0; i < b; i++) {
/* 58 */       names[i] = dataStream.readName().toString();
/*    */     }
/* 60 */     return (FanReferenceListStub)new FanReferenceListStubImpl(parentStub, (IStubElementType)FanElementTypes.INHERITANCE_CLAUSE, names);
/*    */   }
/*    */   
/*    */   public void indexStub(FanReferenceListStub stub, IndexSink sink) {}
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanInheritanceClauseElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */