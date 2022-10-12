/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.IndexSink;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.psi.stubs.StubInputStream;
/*    */ import com.intellij.psi.stubs.StubOutputStream;
/*    */ import com.intellij.util.io.StringRef;
/*    */ import java.io.IOException;
/*    */ import org.fandev.lang.fan.FanStubElementType;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*    */ import org.fandev.lang.fan.psi.stubs.impl.FanTypeDefinitionStubImpl;
/*    */ import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FanTypeDefinitionElementType<TypeDef extends FanTypeDefinition>
/*    */   extends FanStubElementType<FanTypeDefinitionStub, TypeDef>
/*    */ {
/*    */   public FanTypeDefinitionElementType(@NotNull String debugName) {
/* 24 */     super(debugName);
/*    */   }
/*    */   
/*    */   public FanTypeDefinitionStub createStub(TypeDef psi, StubElement parentStub) {
/* 28 */     return (FanTypeDefinitionStub)new FanTypeDefinitionStubImpl(parentStub, (IStubElementType)this, StringRef.fromString(psi.getName()), StringRef.fromString(psi.getPodName()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void serialize(FanTypeDefinitionStub stub, StubOutputStream dataStream) throws IOException {
/* 33 */     dataStream.writeName(stub.getName());
/* 34 */     dataStream.writeName(stub.getPodName());
/*    */   }
/*    */   
/*    */   public FanTypeDefinitionStub deserialize(StubInputStream dataStream, StubElement parentStub) throws IOException {
/* 38 */     StringRef name = dataStream.readName();
/* 39 */     StringRef podName = dataStream.readName();
/* 40 */     return (FanTypeDefinitionStub)new FanTypeDefinitionStubImpl(parentStub, (IStubElementType)this, name, podName);
/*    */   }
/*    */   
/*    */   public void indexStub(FanTypeDefinitionStub stub, IndexSink sink) {
/* 44 */     String shortName = stub.getName();
/* 45 */     if (shortName != null)
/* 46 */       sink.occurrence(FanShortClassNameIndex.KEY, shortName); 
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanTypeDefinitionElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */