package org.fandev.utils;


public class TextUtil {
    public static final String EMPTY_STRING = "";

    public static boolean isEmpty(String s) {
        return (s == null || "".equals(s));
    }

    public static String getAsNotNull(String str) {
        return (str != null) ? str : "";
    }
}
