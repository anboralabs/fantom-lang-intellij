package org.fandev.lang.fan.psi.stubs.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;


public class FanShortClassNameIndex
        extends StringStubIndexExtension<FanTypeDefinition> {
    public static final StubIndexKey<String, FanTypeDefinition> KEY = StubIndexKey.createIndexKey("fan.class.shortName");


    public StubIndexKey<String, FanTypeDefinition> getKey() {
        return KEY;
    }
}