/*    */ package org.fandev.utils;
/*    */ 
/*    */

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.fandev.lang.fan.FanBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.StringTokenizer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OSUtil
/*    */ {
/*    */   private static final String UNIX_PATH_NAME = "PATH";
/*    */   private static final String WINDOWS_PATH_NAME = "Path";
/*    */   private static final String SVN_DEFAULT_UNIX_PATH = "/usr/bin";
/*    */   private static final String SVN_DEFAULT_MAC_PATH = "/usr/local/bin";
/*    */   private static final String SVN_DEFAULT_WIN_PATH = "c:";
/* 29 */   private static final Logger LOG = Logger.getInstance(OSUtil.class.getName());
/*    */   
/*    */   @Nullable
/*    */   public static String getPATHenvVariableName() {
/* 33 */     if (SystemInfo.isWindows) {
/* 34 */       return "Path";
/*    */     }
/* 36 */     if (SystemInfo.isUnix) {
/* 37 */       return "PATH";
/*    */     }
/* 39 */     LOG.error(FanBundle.message("os.not.supported", new Object[0]));
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String appendToPATHenvVariable(@Nullable String path, @NotNull String additionalPath) {
/*    */     String pathValue;
/* 46 */     if (TextUtil.isEmpty(path)) {
/* 47 */       pathValue = additionalPath;
/*    */     } else {
/* 49 */       pathValue = path + File.pathSeparatorChar + additionalPath;
/*    */     } 
/* 51 */     return FileUtil.toSystemDependentName(pathValue);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static String getDefaultSVNPath() {
/* 56 */     if (SystemInfo.isWindows) {
/* 57 */       return "c:";
/*    */     }
/* 59 */     if (SystemInfo.isMac) {
/* 60 */       return "/usr/local/bin";
/*    */     }
/* 62 */     if (SystemInfo.isUnix) {
/* 63 */       return "/usr/bin";
/*    */     }
/* 65 */     LOG.error(FanBundle.message("os.not.supported", new Object[0]));
/* 66 */     return "";
/*    */   }
/*    */   
/*    */   public static String getIdeaSystemPath() {
/* 70 */     return System.getenv().get(getPATHenvVariableName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static String findExecutableByName(@NotNull String exeName) {
/* 80 */     String path = getIdeaSystemPath();
/* 81 */     VirtualFileManager manager = VirtualFileManager.getInstance();
/* 82 */     if (path != null) {
/* 83 */       StringTokenizer st = new StringTokenizer(path, File.pathSeparator);
/*    */ 
/*    */       
/* 86 */       while (st.hasMoreTokens()) {
/* 87 */         String s = VirtualFileUtil.convertToVFSPathAndNormalizeSlashes(st.nextToken());
/* 88 */         String possible_path = s + '/' + exeName;
/* 89 */         if (manager.findFileByUrl(VirtualFileUtil.constructLocalUrl(possible_path)) != null) {
/* 90 */           return possible_path;
/*    */         }
/*    */       } 
/*    */     } 
/* 94 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/utils/OSUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */