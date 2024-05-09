package com.proxbook.finder.application.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Base62ShortenUrlService implements ShortenUrlService{
    private final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final Long BASE = (long) BASE62_CHARS.length();

    @Value("${shorten.hostname}")
    private String KAKAO_REST_API_KEY;



    @Override
    public String encodeUrl(Long id) {
        StringBuilder sb = new StringBuilder();
        while(id > 0){
            long remainder = id % BASE;
            sb.append(BASE62_CHARS.charAt((int) remainder));
            id /= 62;
        }
        return sb.reverse().toString();
    }

    @Override
    public Long decodeUrl(String url) {
        long ret = 0;
        for(int i=0;i<url.length();i++){
            char c = url.charAt(i);
            int value = BASE62_CHARS.indexOf(c);
            ret = ret * BASE + value;
        }
        return ret;
    }
}
