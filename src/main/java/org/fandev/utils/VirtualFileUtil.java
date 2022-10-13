package org.fandev.utils;


import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class VirtualFileUtil {
    @NonNls
    public static final char VFS_PATH_SEPARATOR = '/';

    public static String constructUrl(@NotNull VirtualFile dir, String name) {
        return dir.getUrl() + '/' + name;
    }

    @NotNull
    public static VirtualFile[] getFiles(@NotNull List<String> urls) {
        VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
        List<VirtualFile> files = new ArrayList<VirtualFile>();
        for (String url : urls) {
            if (TextUtil.isEmpty(url)) {
                continue;
            }
            VirtualFile file = virtualFileManager.findFileByUrl(url);
            if (file != null) {
                files.add(file);
            }
        }
        return files.<VirtualFile>toArray(new VirtualFile[files.size()]);
    }

    @NotNull
    public static String getFileName(@NotNull String url) {
        int index = url.lastIndexOf('/');
        return (index < 0) ? url : url.substring(index + 1);
    }


    @Nullable
    public static String getParentDir(@Nullable String url) {
        if (url == null) {
            return null;
        }
        int index = url.lastIndexOf('/');
        return (index < 0) ? null : url.substring(0, index);
    }

    @Nullable
    public static String getExtension(@NotNull String fileName) {
        int index = fileName.lastIndexOf('.');
        return (index < 0) ? null : fileName.substring(index + 1);
    }

    @NotNull
    public static String removeExtension(@NotNull String fileName) {
        int i = fileName.length() - 1;
        for (; i >= 0; i--) {
            if (fileName.charAt(i) == '.') {
                return fileName.substring(0, i);
            }
        }
        return fileName;
    }

    @NotNull
    public static String constructLocalUrl(@NotNull String path) {
        return VirtualFileManager.constructUrl("file", FileUtil.toSystemIndependentName(path));
    }

    @NotNull
    public static String getNameWithoutExtension(@NotNull String fileName) {
        int index = fileName.lastIndexOf('.');
        return (index < 0) ? fileName : fileName.substring(0, index);
    }


    public static int compareVirtualFiles(@NotNull VirtualFile file1, @NotNull VirtualFile file2) {
        String path1 = file1.getPath();
        String path2 = file2.getPath();
        return path1.compareToIgnoreCase(path2);
    }

    public static boolean isValid(@Nullable VirtualFile file) {
        return (file != null && file.isValid());
    }

    public static String buildUrl(@NotNull String rootUrl, @NotNull String relativePath) {
        return rootUrl + (rootUrl.endsWith(String.valueOf('/')) ? "" : Character.valueOf('/')) + FileUtil.toSystemIndependentName(relativePath);
    }

    public static String buildSystemIndependentPath(@NotNull String rootPath, @NotNull String relativePath) {
        String rootPathIN = FileUtil.toSystemIndependentName(rootPath);
        return rootPathIN + (rootPathIN.endsWith(String.valueOf('/')) ? "" : Character.valueOf('/')) + FileUtil.toSystemIndependentName(relativePath);
    }

    @Nullable
    public static String pathToURL(@Nullable String path) {
        if (TextUtil.isEmpty(path)) {
            return null;
        }
        assert path != null;
        return constructLocalUrl(path);
    }

    public static VirtualFile findFileByLocalPath(@NotNull String generateScriptPath) {
        return VirtualFileManager.getInstance().findFileByUrl(constructLocalUrl(generateScriptPath));
    }

    public static VirtualFile refreshAndFindFileByLocalPath(@NotNull String generateScriptPath) {
        return VirtualFileManager.getInstance().refreshAndFindFileByUrl(constructLocalUrl(generateScriptPath));
    }

    public static boolean fileExists(@Nullable VirtualFile file) {
        return (file != null && file.exists());
    }


    @Nullable
    public static String getRelativePath(@NotNull String filePathOrUrl, @NotNull String rootPathOrUrl) {
        if (filePathOrUrl.length() < rootPathOrUrl.length()) {
            return null;
        }
        String path = filePathOrUrl.substring(rootPathOrUrl.length());
        if (path.length() > 0 && path.charAt(0) == '/') {
            path = path.substring(1);
        }
        return path;
    }

    public static class VirtualFilesComparator implements Comparator<VirtualFile> {
        public int compare(VirtualFile file1, VirtualFile file2) {
            return VirtualFileUtil.compareVirtualFiles(file1, file2);
        }
    }

    @NotNull
    public static String convertToVFSPathAndNormalizeSlashes(@NotNull String path) {
        String newPath = FileUtil.toSystemIndependentName(path);
        if (newPath.length() != 0 && newPath.charAt(newPath.length() - 1) == '/') {
            return newPath.substring(0, newPath.length() - 1);
        }
        return newPath;
    }
}
