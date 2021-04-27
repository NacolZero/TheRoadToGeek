package com.nacol.TheRoadToGeek.classloader;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

public class NacolClassLoader extends ClassLoader{

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?>clazz = new NacolClassLoader().findClass("com.nacol.TheRoadToGeek.classloader.Hello");
        clazz.getDeclaredMethod("hello",null)
                .invoke(clazz.newInstance(), null);
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) {
        String base64 = "yv66vgAAADQAHwoABgARCQASABMIABQKABUAFgcAFwcAGAEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQArTGNvbS9uYWNvbC9UaGVSb2FkVG9HZWVrL2NsYXNzbG9hZGVyL0hlbGxvOwEABWhlbGxvAQAKU291cmNlRmlsZQEACkhlbGxvLmphdmEMAAcACAcAGQwAGgAbAQATSGVsbG8sIGNsYXNzTG9hZGVyIQcAHAwAHQAeAQApY29tL25hY29sL1RoZVJvYWRUb0dlZWsvY2xhc3Nsb2FkZXIvSGVsbG8BABBqYXZhL2xhbmcvT2JqZWN0AQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABQAGAAAAAAACAAEABwAIAAEACQAAAC8AAQABAAAABSq3AAGxAAAAAgAKAAAABgABAAAAAwALAAAADAABAAAABQAMAA0AAAABAA4ACAABAAkAAAA3AAIAAQAAAAmyAAISA7YABLEAAAACAAoAAAAKAAIAAAAGAAgABwALAAAADAABAAAACQAMAA0AAAABAA8AAAACABA=";
        byte[] bytes = decode(base64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] decode(String base64S) {
        return Base64.getDecoder().decode(base64S);
    }

}
