package com.nacol.TheRoadToGeek.week_01_classloader;

import lombok.SneakyThrows;


public class NacolClassLoader extends ClassLoader{

    private String filePath;

    public NacolClassLoader(String filePath) {
        this.filePath = filePath;
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) {
        //获取加密的字节数组（暂用工具类，比较死）
        byte[] bytes = FileUtil.getFileBytes(filePath);
        //获取解析器 （未来解析器可改为策略模式，思考如何获取对应策略）
        Decoder decoder = new Base256Decoder();
        //解析为原字节数组
        decoder.decode(bytes);
        return defineClass(name, bytes, 0, bytes.length);
    }


}
