package org.fandev.lang.fan.psi.stubs.elements;

import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.FanStubElementType;
import org.fandev.lang.fan.psi.api.modifiers.FanFacet;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
import org.fandev.lang.fan.psi.stubs.FanSlotStub;
import org.fandev.lang.fan.psi.stubs.index.FanFacetNameSlotIndex;
import org.fandev.lang.fan.psi.stubs.index.FanSlotNameIndex;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public abstract class FanSlotElementType<T extends FanSlot, S extends FanSlotStub<T>>
        extends FanStubElementType<S, T> {
    protected FanSlotElementType(@NotNull String debugName) {
        super(debugName);
    }

    public S createStub(T t, StubElement element) {
        FanFacet[] facets = t.getFacets();
        String[] facetNames = new String[facets.length];
        for (int i = 0; i < facets.length; i++) {
            facetNames[i] = facets[i].getName();
        }
        return createStubImpl(element, StringRef.fromString(t.getName()), facetNames);
    }

    public void serialize(S stub, StubOutputStream stream) throws IOException {
        stream.writeName(stub.getName());
        String[] facets = stub.getFacetNames();
        stream.writeByte(facets.length);
        for (String s : facets) {
            stream.writeName(s);
        }
    }

    public S deserialize(StubInputStream stream, StubElement element) throws IOException {
        StringRef name = stream.readName();
        byte b = stream.readByte();
        String[] facets = new String[b];
        for (int i = 0; i < b; i++) {
            facets[i] = stream.readName().toString();
        }
        return createStubImpl(element, name, facets);
    }


    public void indexStub(S stub, IndexSink sink) {
        String name = stub.getName();
        if (name != null) {
            sink.occurrence(FanSlotNameIndex.KEY, name);
        }
        for (String facet : stub.getFacetNames()) {
            if (facet != null)
                sink.occurrence(FanFacetNameSlotIndex.KEY, facet);
        }
    }

    protected abstract S createStubImpl(StubElement paramStubElement, StringRef paramStringRef, String[] paramArrayOfString);
}
