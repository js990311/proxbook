package com.proxbook.finder.application.service.utils;

public class Base62Encoder {
    private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Long BASE = (long) BASE62_CHARS.length();

    public static String encode(Long plain){
        StringBuilder sb = new StringBuilder();
        while(plain > 0){
            long remainder = plain % BASE;
            sb.append(BASE62_CHARS.charAt((int) remainder));
            plain /= 62;
        }
        return sb.reverse().toString();
    }

    public static long decode(String code){
        long ret = 0;
        for(int i=0;i<code.length();i++){
            char c = code.charAt(i);
            int value = BASE62_CHARS.indexOf(c);
            ret = ret * BASE + value;
        }
        return ret;
    }
}
