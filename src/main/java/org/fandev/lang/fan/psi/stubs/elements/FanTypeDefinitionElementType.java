package org.fandev.lang.fan.psi.stubs.elements;

import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.FanStubElementType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.fandev.lang.fan.psi.stubs.impl.FanTypeDefinitionStubImpl;
import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public abstract class FanTypeDefinitionElementType<TypeDef extends FanTypeDefinition>
        extends FanStubElementType<FanTypeDefinitionStub, TypeDef> {
    public FanTypeDefinitionElementType(@NotNull String debugName) {
        super(debugName);
    }

    public FanTypeDefinitionStub createStub(TypeDef psi, StubElement parentStub) {
        return new FanTypeDefinitionStubImpl(parentStub, this, StringRef.fromString(psi.getName()), StringRef.fromString(psi.getPodName()));
    }


    public void serialize(FanTypeDefinitionStub stub, StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
        dataStream.writeName(stub.getPodName());
    }

    public FanTypeDefinitionStub deserialize(StubInputStream dataStream, StubElement parentStub) throws IOException {
        StringRef name = dataStream.readName();
        StringRef podName = dataStream.readName();
        return new FanTypeDefinitionStubImpl(parentStub, this, name, podName);
    }

    public void indexStub(FanTypeDefinitionStub stub, IndexSink sink) {
        String shortName = stub.getName();
        if (shortName != null)
            sink.occurrence(FanShortClassNameIndex.KEY, shortName);
    }
}
