package com.proxbook.finder.api.book.service;

import com.proxbook.finder.api.book.dto.KakaoBookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.ObjectUtils;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoBookService {
    private static final String KAKAO_BOOK_API_URL = "https://dapi.kakao.com/v3/search/book.json";

    private final RestTemplate restTemplate;

    @Value("${kakao.api.key}")
    private String kakaoRestApiKey;

    public KakaoBookDto requestBookApi(String isbn){
        URI uri = buildBookApiUri(isbn);
        HttpHeaders headers = new HttpHeaders();
        headers.set(
                HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey
        );
        log.warn("headers");
        log.warn(headers.toString());

        HttpEntity httpEntity = new HttpEntity(headers);
        log.warn(httpEntity.toString());

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoBookDto.class).getBody();
    }

    public URI buildBookApiUri(String isbn){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(KAKAO_BOOK_API_URL);
        builder.queryParam("query", isbn);
        builder.queryParam("target", "isbn");

        URI uri = builder.build().encode().toUri();

        log.info("kakao book api uri", uri.toString());

        return uri;
    }

}
