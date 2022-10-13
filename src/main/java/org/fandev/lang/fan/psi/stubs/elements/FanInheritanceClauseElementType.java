package org.fandev.lang.fan.psi.stubs.elements;

import com.intellij.psi.stubs.*;
import com.intellij.util.containers.ContainerUtil;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanStubElementType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanInheritanceClause;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.lang.fan.psi.impl.statements.typedefs.FanInheritanceClauseImpl;
import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;
import org.fandev.lang.fan.psi.stubs.impl.FanReferenceListStubImpl;

import java.io.IOException;


public class FanInheritanceClauseElementType
        extends FanStubElementType<FanReferenceListStub, FanInheritanceClause> {
    public FanInheritanceClauseElementType() {
        super("INHERITANCE_CLAUSE");
    }

    public FanInheritanceClause createPsi(FanReferenceListStub stub) {
        return new FanInheritanceClauseImpl(stub);
    }

    public FanReferenceListStub createStub(FanInheritanceClause psi, StubElement parentStub) {
        FanCodeReferenceElement[] elements = psi.getReferenceElements();
        String[] refNames = ContainerUtil.map(elements, element -> element.getReferenceName(), new String[elements.length]);

        return new FanReferenceListStubImpl(parentStub, FanElementTypes.INHERITANCE_CLAUSE, refNames);
    }

    public void serialize(FanReferenceListStub stub, StubOutputStream dataStream) throws IOException {
        String[] names = stub.getBaseClasses();
        dataStream.writeByte(names.length);
        for (String s : names) {
            dataStream.writeName(s);
        }
    }

    public FanReferenceListStub deserialize(StubInputStream dataStream, StubElement parentStub) throws IOException {
        byte b = dataStream.readByte();
        String[] names = new String[b];
        for (int i = 0; i < b; i++) {
            names[i] = dataStream.readName().toString();
        }
        return new FanReferenceListStubImpl(parentStub, FanElementTypes.INHERITANCE_CLAUSE, names);
    }

    public void indexStub(FanReferenceListStub stub, IndexSink sink) {
    }
}
