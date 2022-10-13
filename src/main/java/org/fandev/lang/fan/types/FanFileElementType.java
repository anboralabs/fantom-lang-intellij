package org.fandev.lang.fan.types;


import com.intellij.lang.Language;
import com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.annotations.NonNls;


public class FanFileElementType
        extends IStubFileElementType {
    public static final int VERSION = 1;

    public FanFileElementType(Language language) {
        super(language);
    }

    public FanFileElementType(@NonNls String s, Language language) {
        super(s, language);
    }

    public String getExternalId() {
        return getLanguage() + ":" + toString();
    }

    public int getStubVersion() {
        return 1;
    }
}
