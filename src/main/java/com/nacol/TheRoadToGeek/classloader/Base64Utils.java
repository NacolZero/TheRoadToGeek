package com.nacol.TheRoadToGeek.classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64Utils {

    public static final String PATH = "F:\\coder\\_project_nacol\\TheRoadToGeek\\target\\classes\\com\\nacol\\TheRoadToGeek\\classloader\\Hello.class";

    public static String FileToBase64(String filePath) throws IOException {
        if (filePath == null) {
            return null;
        }
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
