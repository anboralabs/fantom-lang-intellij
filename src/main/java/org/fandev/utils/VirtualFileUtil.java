/*     */ package org.fandev.utils;
/*     */ 
/*     */

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VirtualFileUtil
/*     */ {
/*     */   @NonNls
/*     */   public static final char VFS_PATH_SEPARATOR = '/';
/*     */   
/*     */   public static String constructUrl(@NotNull VirtualFile dir, String name) {
/*  33 */     return dir.getUrl() + '/' + name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static VirtualFile[] getFiles(@NotNull List<String> urls) {
/*  43 */     VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
/*  44 */     List<VirtualFile> files = new ArrayList<VirtualFile>();
/*  45 */     for (String url : urls) {
/*  46 */       if (TextUtil.isEmpty(url)) {
/*     */         continue;
/*     */       }
/*  49 */       VirtualFile file = virtualFileManager.findFileByUrl(url);
/*  50 */       if (file != null) {
/*  51 */         files.add(file);
/*     */       }
/*     */     } 
/*  54 */     return files.<VirtualFile>toArray(new VirtualFile[files.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String getFileName(@NotNull String url) {
/*  63 */     int index = url.lastIndexOf('/');
/*  64 */     return (index < 0) ? url : url.substring(index + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getParentDir(@Nullable String url) {
/*  73 */     if (url == null) {
/*  74 */       return null;
/*     */     }
/*  76 */     int index = url.lastIndexOf('/');
/*  77 */     return (index < 0) ? null : url.substring(0, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getExtension(@NotNull String fileName) {
/*  86 */     int index = fileName.lastIndexOf('.');
/*  87 */     return (index < 0) ? null : fileName.substring(index + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String removeExtension(@NotNull String fileName) {
/*  96 */     int i = fileName.length() - 1;
/*  97 */     for (; i >= 0; i--) {
/*  98 */       if (fileName.charAt(i) == '.') {
/*  99 */         return fileName.substring(0, i);
/*     */       }
/*     */     } 
/* 102 */     return fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String constructLocalUrl(@NotNull String path) {
/* 112 */     return VirtualFileManager.constructUrl("file", FileUtil.toSystemIndependentName(path));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String getNameWithoutExtension(@NotNull String fileName) {
/* 117 */     int index = fileName.lastIndexOf('.');
/* 118 */     return (index < 0) ? fileName : fileName.substring(0, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int compareVirtualFiles(@NotNull VirtualFile file1, @NotNull VirtualFile file2) {
/* 129 */     String path1 = file1.getPath();
/* 130 */     String path2 = file2.getPath();
/* 131 */     return path1.compareToIgnoreCase(path2);
/*     */   }
/*     */   
/*     */   public static boolean isValid(@Nullable VirtualFile file) {
/* 135 */     return (file != null && file.isValid());
/*     */   }
/*     */ 
/*     */   
/*     */   public static String buildUrl(@NotNull String rootUrl, @NotNull String relativePath) {
/* 140 */     return rootUrl + (rootUrl.endsWith(String.valueOf('/')) ? "" : Character.valueOf('/')) + FileUtil.toSystemIndependentName(relativePath);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String buildSystemIndependentPath(@NotNull String rootPath, @NotNull String relativePath) {
/* 150 */     String rootPathIN = FileUtil.toSystemIndependentName(rootPath);
/* 151 */     return rootPathIN + (rootPathIN.endsWith(String.valueOf('/')) ? "" : Character.valueOf('/')) + FileUtil.toSystemIndependentName(relativePath);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String pathToURL(@Nullable String path) {
/* 164 */     if (TextUtil.isEmpty(path)) {
/* 165 */       return null;
/*     */     }
/* 167 */     assert path != null;
/* 168 */     return constructLocalUrl(path);
/*     */   }
/*     */   
/*     */   public static VirtualFile findFileByLocalPath(@NotNull String generateScriptPath) {
/* 172 */     return VirtualFileManager.getInstance().findFileByUrl(constructLocalUrl(generateScriptPath));
/*     */   }
/*     */   
/*     */   public static VirtualFile refreshAndFindFileByLocalPath(@NotNull String generateScriptPath) {
/* 176 */     return VirtualFileManager.getInstance().refreshAndFindFileByUrl(constructLocalUrl(generateScriptPath));
/*     */   }
/*     */   
/*     */   public static boolean fileExists(@Nullable VirtualFile file) {
/* 180 */     return (file != null && file.exists());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getRelativePath(@NotNull String filePathOrUrl, @NotNull String rootPathOrUrl) {
/* 186 */     if (filePathOrUrl.length() < rootPathOrUrl.length()) {
/* 187 */       return null;
/*     */     }
/* 189 */     String path = filePathOrUrl.substring(rootPathOrUrl.length());
/* 190 */     if (path.length() > 0 && path.charAt(0) == '/') {
/* 191 */       path = path.substring(1);
/*     */     }
/* 193 */     return path;
/*     */   }
/*     */   
/*     */   public static class VirtualFilesComparator implements Comparator<VirtualFile> {
/*     */     public int compare(VirtualFile file1, VirtualFile file2) {
/* 198 */       return VirtualFileUtil.compareVirtualFiles(file1, file2);
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String convertToVFSPathAndNormalizeSlashes(@NotNull String path) {
/* 204 */     String newPath = FileUtil.toSystemIndependentName(path);
/* 205 */     if (newPath.length() != 0 && newPath.charAt(newPath.length() - 1) == '/') {
/* 206 */       return newPath.substring(0, newPath.length() - 1);
/*     */     }
/* 208 */     return newPath;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/utils/VirtualFileUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */