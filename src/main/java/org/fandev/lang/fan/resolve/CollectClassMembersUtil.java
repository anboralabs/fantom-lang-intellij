package org.fandev.lang.fan.resolve;


import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.*;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.psi.util.*;
import com.intellij.util.containers.HashMap;
import com.intellij.util.containers.HashSet;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CollectClassMembersUtil {
    private static final Key<CachedValue<Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>>>> CACHED_MEMBERS = Key.create("CACHED_CLASS_MEMBERS");

    private static final Key<CachedValue<Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>>>> CACHED_MEMBERS_INCLUDING_SYNTHETIC = Key.create("CACHED_MEMBERS_INCLUDING_SYNTHETIC");

    public static Map<String, List<CandidateInfo>> getAllMethods(PsiClass aClass, boolean includeSynthetic) {
        Key<CachedValue<Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>>>> key = includeSynthetic ? CACHED_MEMBERS_INCLUDING_SYNTHETIC : CACHED_MEMBERS;

        CachedValue<Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>>> cachedValue = aClass.getUserData(key);
        if (cachedValue == null) {
            cachedValue = buildCache(aClass, includeSynthetic);
        }

        Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>> value = cachedValue.getValue();
        assert value != null;
        return value.getSecond();
    }

    public static Map<String, CandidateInfo> getAllFields(PsiClass aClass) {
        CachedValue<Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>>> cachedValue = aClass.getUserData(CACHED_MEMBERS);
        if (cachedValue == null) {
            cachedValue = buildCache(aClass, false);
        }

        Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>> value = cachedValue.getValue();
        assert value != null;
        return value.getFirst();
    }

    private static CachedValue<Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>>> buildCache(final PsiClass aClass, final boolean includeSynthetic) {
        final CachedValuesManager manager = CachedValuesManager.getManager(aClass.getProject());
        return manager.createCachedValue((CachedValueProvider<Pair<Map<String, CandidateInfo>, Map<String, List<CandidateInfo>>>>) () -> {
            HashMap hashMap1 = new HashMap();
            HashMap hashMap2 = new HashMap();

            CollectClassMembersUtil.processClass(aClass, (Map<String, CandidateInfo>) hashMap1, (Map<String, List<CandidateInfo>>) hashMap2, (Set<PsiClass>) new HashSet(), PsiSubstitutor.EMPTY, includeSynthetic);
            return new CachedValueProvider.Result(new Pair(hashMap1, hashMap2), new Object[]{PsiModificationTracker.MODIFICATION_COUNT});
        }, false);
    }

    private static void processClass(PsiClass aClass, Map<String, CandidateInfo> allFields, Map<String, List<CandidateInfo>> allMethods, Set<PsiClass> visitedClasses, PsiSubstitutor substitutor, boolean includeSynthetic) {
        if (visitedClasses.contains(aClass))
            return;
        visitedClasses.add(aClass);

        for (PsiField field : aClass.getFields()) {
            String name = field.getName();
            if (!allFields.containsKey(name)) {
                allFields.put(name, new CandidateInfo(field, substitutor));
            }
        }

        for (PsiMethod method : (includeSynthetic || !(aClass instanceof FanTypeDefinition)) ? aClass.getMethods() : ((FanTypeDefinition) aClass).getFanMethods()) {
            addMethod(allMethods, method, substitutor);
        }

        for (PsiClassType superType : aClass.getSuperTypes()) {
            PsiClass superClass = superType.resolve();
            if (superClass != null) {
                PsiSubstitutor superSubstitutor = TypeConversionUtil.getSuperClassSubstitutor(superClass, aClass, substitutor);
                processClass(superClass, allFields, allMethods, visitedClasses, superSubstitutor, includeSynthetic);
            }
        }
    }

    private static void addMethod(Map<String, List<CandidateInfo>> allMethods, PsiMethod method, PsiSubstitutor substitutor) {
        String name = method.getName();
        List<CandidateInfo> methods = allMethods.get(name);
        if (methods == null) {
            methods = new ArrayList<>();
            allMethods.put(name, methods);
            methods.add(new CandidateInfo(method, substitutor));
        } else {
            methods.add(new CandidateInfo(method, substitutor));
        }
    }
}