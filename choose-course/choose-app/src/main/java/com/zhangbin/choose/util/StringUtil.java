package com.zhangbin.choose.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类
 */
public class StringUtil {

    /**
     * 加密字符串
     *
     * @param input 需要加密的字符串
     * @return 加密之后的字符串
     */
    public static String md5Encode(String input) {
        return DigestUtils.md5Hex(input.getBytes());
    }

    /**
     * 加密字符串
     *
     * @param input 需要加密的字符串
     * @return 加密之后的字符串
     */
    public static String sha256Encode(String input) {
        return DigestUtils.sha256Hex(input.getBytes());
    }


}
