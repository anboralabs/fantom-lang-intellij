package org.fandev.icons;


import com.intellij.openapi.util.IconLoader;

import javax.swing.*;


public interface Icons {
    public static final Icon POD = IconLoader.getIcon("/icons/structure/pod.png");
    public static final Icon CLASS = IconLoader.getIcon("/icons/structure/class.png");
    public static final Icon ABSTRACT_CLASS = IconLoader.getIcon("/icons/structure/abstract-class.png");
    public static final Icon MIXIN = IconLoader.getIcon("/icons/structure/mixin.png");
    public static final Icon ENUM = IconLoader.getIcon("/icons/structure/enum.png");
    public static final Icon METHOD = IconLoader.getIcon("/icons/structure/method.png");
    public static final Icon FIELD = IconLoader.getIcon("/icons/structure/field.png");
    public static final Icon FAN_16 = IconLoader.getIcon("/icons/fantom-16x16.png");
    public static final Icon FAN_24 = IconLoader.getIcon("/icons/fantom-24x24.png");
    public static final Icon FAN_MODULE_OPEN = IconLoader.getIcon("/icons/modules/fan_module_opened.png");
    public static final Icon FAN_MODULE_CLOSE = IconLoader.getIcon("/icons/modules/fan_module_closed.png");
}