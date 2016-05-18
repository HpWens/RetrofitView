package com.upyun.library.utils;

import com.upyun.library.common.UpConfig;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

public class UpYunUtils {

    /**
     * 计算policy
     *
     * @param paramMap
     * @return
     */
    public static String getPolicy(Map<String, Object> paramMap) {

        JSONObject obj = new JSONObject(paramMap);
        return Base64Coder.encodeString(obj.toString());
    }

    public static String getSignature(String policy,
                                      String secretKey) {
        return md5(policy + "&" + secretKey);
    }

    public static String getSignature(Map<String, Object> paramMap,
                                      String secretKey) {
        Object[] keys = paramMap.keySet().toArray();
        Arrays.sort(keys);

        StringBuffer tmp = new StringBuffer("");
        for (Object key : keys) {
            tmp.append(key).append(paramMap.get(key));
        }
        tmp.append(secretKey);
        return md5(tmp.toString().getBytes());
    }

    public static String md5(byte[] bytes) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest不支持MD5Util", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is unsupported", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest不支持MD5Util", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static String md5Hex(File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[UpConfig.BLOCK_SIZE];
            int length;
            while ((length = in.read(buffer)) > 0) {
                messageDigest.update(buffer, 0, length);
            }
            byte[] hash = messageDigest.digest();
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) hex.append("0");
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found", e);
        } catch (IOException e) {
            throw new RuntimeException("file get md5 failed", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest不支持MD5Util", e);
        }
    }

    /**
     * 计算分块数目
     *
     * @param file
     * @param blockSize
     * @return
     */
    public static int getBlockNum(File file, int blockSize) {
        return (int) Math.ceil(file.length() / (double) blockSize);
    }
}
