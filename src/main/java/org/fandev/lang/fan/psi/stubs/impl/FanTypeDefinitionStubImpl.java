package org.fandev.lang.fan.psi.stubs.impl;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;


public class FanTypeDefinitionStubImpl
        extends StubBase<FanTypeDefinition>
        implements FanTypeDefinitionStub {
    private final StringRef myPodName;
    private final StringRef myName;

    public FanTypeDefinitionStubImpl(StubElement parent, IStubElementType elementType, StringRef name, StringRef podName) {
        super(parent, elementType);
        this.myName = name;
        this.myPodName = podName;
    }

    public String getName() {
        return StringRef.toString(this.myName);
    }

    public String getPodName() {
        return StringRef.toString(this.myPodName);
    }
}