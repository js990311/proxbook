package com.proxbook.finder.application.service.utils;

public interface ShortenUrlService {
    public String encodeUrl(Long id);
    public Long decodeUrl(String url);
}
