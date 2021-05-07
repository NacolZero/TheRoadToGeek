package com.nacol.TheRoadToGeek.week_01_classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Run {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        //init data
        final String filePath = "F:\\coder\\_project_nacol\\TheRoadToGeek\\target\\classes\\com\\nacol\\TheRoadToGeek\\classloader\\Hello.xlass";
        final String className = "Hello";
        final String methodName = "hello";

        //init ClassLoader
        NacolClassLoader classLoader = new NacolClassLoader(filePath);
        //get class
        Class<?>clazz = classLoader.findClass(className);
        //get method
        Method method = clazz.getDeclaredMethod(methodName,null);
        //execute method
        method.invoke(clazz.getDeclaredConstructor().newInstance(), null);
    }
}
