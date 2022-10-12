/*    */ package org.fandev.lang.fan.psi.stubs.elements;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.stubs.IndexSink;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.psi.stubs.StubInputStream;
/*    */ import com.intellij.psi.stubs.StubOutputStream;
/*    */ import com.intellij.util.io.StringRef;
/*    */ import java.io.IOException;
/*    */ import org.fandev.lang.fan.FanStubElementType;
/*    */ import org.fandev.lang.fan.psi.api.modifiers.FanFacet;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
/*    */ import org.fandev.lang.fan.psi.stubs.FanSlotStub;
/*    */ import org.fandev.lang.fan.psi.stubs.index.FanFacetNameSlotIndex;
/*    */ import org.fandev.lang.fan.psi.stubs.index.FanSlotNameIndex;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public abstract class FanSlotElementType<T extends FanSlot, S extends FanSlotStub<T>>
/*    */   extends FanStubElementType<S, T>
/*    */ {
/*    */   protected FanSlotElementType(@NotNull String debugName) {
/* 40 */     super(debugName);
/*    */   }
/*    */   
/*    */   public S createStub(T t, StubElement element) {
/* 44 */     FanFacet[] facets = t.getFacets();
/* 45 */     String[] facetNames = new String[facets.length];
/* 46 */     for (int i = 0; i < facets.length; i++) {
/* 47 */       facetNames[i] = facets[i].getName();
/*    */     }
/* 49 */     return createStubImpl(element, StringRef.fromString(t.getName()), facetNames);
/*    */   }
/*    */   
/*    */   public void serialize(S stub, StubOutputStream stream) throws IOException {
/* 53 */     stream.writeName(stub.getName());
/* 54 */     String[] facets = stub.getFacetNames();
/* 55 */     stream.writeByte(facets.length);
/* 56 */     for (String s : facets) {
/* 57 */       stream.writeName(s);
/*    */     }
/*    */   }
/*    */   
/*    */   public S deserialize(StubInputStream stream, StubElement element) throws IOException {
/* 62 */     StringRef name = stream.readName();
/* 63 */     byte b = stream.readByte();
/* 64 */     String[] facets = new String[b];
/* 65 */     for (int i = 0; i < b; i++) {
/* 66 */       facets[i] = stream.readName().toString();
/*    */     }
/* 68 */     return createStubImpl(element, name, facets);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void indexStub(S stub, IndexSink sink) {
/* 74 */     String name = stub.getName();
/* 75 */     if (name != null) {
/* 76 */       sink.occurrence(FanSlotNameIndex.KEY, name);
/*    */     }
/* 78 */     for (String facet : stub.getFacetNames()) {
/* 79 */       if (facet != null)
/* 80 */         sink.occurrence(FanFacetNameSlotIndex.KEY, facet); 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract S createStubImpl(StubElement paramStubElement, StringRef paramStringRef, String[] paramArrayOfString);
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/stubs/elements/FanSlotElementType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */