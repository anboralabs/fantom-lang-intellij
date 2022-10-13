package org.fandev.lang.fan.psi.impl.statements.arguments;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.arguments.FanArgument;
import org.fandev.lang.fan.psi.api.statements.arguments.FanArgumentList;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.jetbrains.annotations.NotNull;


public class FanArgumentListImpl
        extends FanBaseElementImpl
        implements FanArgumentList {
    public FanArgumentListImpl(ASTNode astNode) {
        super(astNode);
    }

    public String toString() {
        return "Arguments";
    }

    public FanArgument[] getArguments() {
        return (FanArgument[]) findChildrenByClass(FanArgument.class);
    }

    public int indexOf(@NotNull FanArgument arg) {
        FanArgument[] arguments = getArguments();
        for (int index = 0; index < arguments.length; index++) {
            if (arguments[index].equals(arg)) return index;
        }
        return -1;
    }
}