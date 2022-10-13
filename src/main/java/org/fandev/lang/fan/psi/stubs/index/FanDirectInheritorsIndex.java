package org.fandev.lang.fan.psi.stubs.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanReferenceList;


public class FanDirectInheritorsIndex
        extends StringStubIndexExtension<FanReferenceList> {
    public static final StubIndexKey<String, FanReferenceList> KEY = StubIndexKey.createIndexKey("fan.class.super");

    public StubIndexKey<String, FanReferenceList> getKey() {
        return KEY;
    }
}
