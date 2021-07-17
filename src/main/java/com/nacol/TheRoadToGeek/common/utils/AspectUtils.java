package com.nacol.TheRoadToGeek.common.utils;


import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/17
 * @Description 切面工具
 */
public class AspectUtils {

    public static Method getMethod(JoinPoint joinPoint) throws Exception {
        if (joinPoint == null){
            return null;
        }

        if (joinPoint.getSignature() == null){
            return null;
        }

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        if (methodName == null){
            return null;
        }

        String toString = signature.toLongString();
        Class[] classes = getParamClassString(toString);

        Method method = joinPoint.getSignature().getDeclaringType().getMethod(methodName, classes);
        if (method == null){
            return null;
        }
        return method;
    }

    private static Class[] getParamClassString(String toString) throws Exception{
        String foo = StringUtils.substring(toString, StringUtils.indexOf(toString, "(") + 1, StringUtils.indexOf(toString, ")"));
        String[] clazzStrArray = StringUtils.split(foo, ",");
        List<Class> classes = new ArrayList<>();
        for(String str : clazzStrArray){
            classes.add(Class.forName(str));
        }
        Class[] classesArray = new Class[classes.size()];
        return classes.toArray(classesArray);
    }

}
