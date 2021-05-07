package com.nacol.TheRoadToGeek.week_01_classloader;

public class Base256Decoder implements Decoder {

    @Override
    public byte[] decode(byte[] bytes) {
        //考虑目前场景不需要再次使用加密的字节数组，所以直接将其替换原数组
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }
}
