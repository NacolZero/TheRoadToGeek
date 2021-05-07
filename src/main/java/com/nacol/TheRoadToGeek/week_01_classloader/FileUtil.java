package com.nacol.TheRoadToGeek.week_01_classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static byte[] getFileBytes(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
