package com.proxbook.finder.api.book.service;

import com.proxbook.finder.api.book.dto.KakaoBookDto;
import com.proxbook.finder.domain.book.dto.KakaoUpdateBookDto;
import com.proxbook.finder.domain.book.dto.UpdateBookDto;
import com.proxbook.finder.domain.book.service.update.BookUpdateSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoBookService implements BookUpdateSourceService {
    private static final String KAKAO_BOOK_API_URL = "https://dapi.kakao.com/v3/search/book.json";

    private final RestTemplate restTemplate;


    @Override
    public UpdateBookDto getBookSourceByIsbn(String isbn) {
        KakaoBookDto kakaoBookDto = requestBookApi(isbn);
        UpdateBookDto updateBookDto = new KakaoUpdateBookDto(kakaoBookDto);
        return updateBookDto;
    }

    public KakaoBookDto requestBookApi(String isbn){
        URI uri = buildBookApiUri(isbn);
        return restTemplate.getForObject(uri, KakaoBookDto.class);
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
