package com.proxbook.finder.domain.proxlibrary.service.utils;

public interface ShortenUrlService {
    public String encodeUrl(Long id);
    public Long decodeUrl(String url);
}
