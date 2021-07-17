package com.nacol.TheRoadToGeek.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.*;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/17
 * @Description 加密工具
 */
public class EncryptionUtils {

    private static final List<String > SELECTION_CODES = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

    public static String argsToMd5(Object[] args) {
        StringBuffer plainText = new StringBuffer("");
        List<Object> argList = new ArrayList<>(Arrays.asList(args));
        ListIterator<Object> itr = argList.listIterator();
        while (itr.hasNext()) {
            Object obj = itr.next();
            try {
                plainText.append(JSONObject.toJSONString(obj));
            } catch (Exception e) {

            }
        }
        return stringToMD5(plainText.toString());
    }

    /**
     * Md5 加密
     */
    public static String stringToMD5(String plainText) { 
        return DigestUtils.md5DigestAsHex(plainText.getBytes());
    }

    public static String StringToMD5WithSalt(String plainText) {
        //STEP 生成16位随随机字符
        StringBuffer saltSb = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            saltSb.append(SELECTION_CODES.get(new Random().nextInt(SELECTION_CODES.size())));
        }
        String salt = saltSb.toString();
        System.out.println("StringToMD5WithSalt : " + salt);

        //STEP
        plainText = md5Hex(plainText + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = plainText.charAt(i / 3 * 2);
            cs[i + 1] = salt.charAt(i / 3);
            cs[i + 2] = plainText.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        System.out.println("StringToMD5WithSalt : " + salt);
        return md5Hex(password + salt).equals(new String(cs1));
    }

    public static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

}
