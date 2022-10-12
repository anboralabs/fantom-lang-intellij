package org.fandev.lang.fan;


import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ResourceBundle;


public class FanBundle {
    private static Reference<ResourceBundle> ourBundle;
    @NonNls
    public static final String BUNDLE = "org.fandev.lang.fan.FanBundle";

    public static String message(@NonNls @PropertyKey(resourceBundle = "org.fandev.lang.fan.FanBundle") String key, Object... params) {
        return CommonBundle.message(getBundle(), key, params);
    }

    private static ResourceBundle getBundle() {
        ResourceBundle bundle = null;
        if (ourBundle != null) bundle = ourBundle.get();
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("org.fandev.lang.fan.FanBundle");
            ourBundle = new SoftReference<ResourceBundle>(bundle);
        }
        return bundle;
    }
}