package org.fandev.utils;


import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.fandev.lang.fan.FanBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.StringTokenizer;


public class OSUtil {
    private static final String UNIX_PATH_NAME = "PATH";
    private static final String WINDOWS_PATH_NAME = "Path";
    private static final String SVN_DEFAULT_UNIX_PATH = "/usr/bin";
    private static final String SVN_DEFAULT_MAC_PATH = "/usr/local/bin";
    private static final String SVN_DEFAULT_WIN_PATH = "c:";
    private static final Logger LOG = Logger.getInstance(OSUtil.class.getName());

    @Nullable
    public static String getPATHenvVariableName() {
        if (SystemInfo.isWindows) {
            return "Path";
        }
        if (SystemInfo.isUnix) {
            return "PATH";
        }
        LOG.error(FanBundle.message("os.not.supported", new Object[0]));
        return null;
    }


    public static String appendToPATHenvVariable(@Nullable String path, @NotNull String additionalPath) {
        String pathValue;
        if (TextUtil.isEmpty(path)) {
            pathValue = additionalPath;
        } else {
            pathValue = path + File.pathSeparatorChar + additionalPath;
        }
        return FileUtil.toSystemDependentName(pathValue);
    }

    @NotNull
    public static String getDefaultSVNPath() {
        if (SystemInfo.isWindows) {
            return "c:";
        }
        if (SystemInfo.isMac) {
            return "/usr/local/bin";
        }
        if (SystemInfo.isUnix) {
            return "/usr/bin";
        }
        LOG.error(FanBundle.message("os.not.supported", new Object[0]));
        return "";
    }

    public static String getIdeaSystemPath() {
        return System.getenv().get(getPATHenvVariableName());
    }


    @Nullable
    public static String findExecutableByName(@NotNull String exeName) {
        String path = getIdeaSystemPath();
        VirtualFileManager manager = VirtualFileManager.getInstance();
        if (path != null) {
            StringTokenizer st = new StringTokenizer(path, File.pathSeparator);


            while (st.hasMoreTokens()) {
                String s = VirtualFileUtil.convertToVFSPathAndNormalizeSlashes(st.nextToken());
                String possible_path = s + '/' + exeName;
                if (manager.findFileByUrl(VirtualFileUtil.constructLocalUrl(possible_path)) != null) {
                    return possible_path;
                }
            }
        }
        return null;
    }
}
