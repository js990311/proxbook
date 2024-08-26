package com.proxbook.finder.domain.book.dto;

import com.proxbook.finder.domain.book.service.update.dto.KakaoBookDocumentDto;
import com.proxbook.finder.domain.book.service.update.dto.KakaoBookDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KakaoUpdateBookDto extends UpdateBookDto{

    private final KakaoBookDto kakaoBookDto;

    public KakaoUpdateBookDto(KakaoBookDto kakaoBookDto) {
        this.kakaoBookDto = kakaoBookDto;
    }

    @Override
    public String getTitle() {
        KakaoBookDocumentDto book = kakaoBookDto.getDocuments().get(0);
        return book.getTitle();
    }

    @Override
    public String getPublisher() {
        KakaoBookDocumentDto book = kakaoBookDto.getDocuments().get(0);
        return book.getPublisher();
    }

    @Override
    public Integer getPublishYear() {
        KakaoBookDocumentDto book = kakaoBookDto.getDocuments().get(0);
        LocalDateTime dateTime = LocalDateTime.parse(book.getPublishDate(), DateTimeFormatter.ISO_DATE_TIME);
        return dateTime.getYear();
    }

    @Override
    public String getDescription() {
        KakaoBookDocumentDto book = kakaoBookDto.getDocuments().get(0);
        return book.getContents();
    }

    @Override
    public String getThumbnailUrl() {
        KakaoBookDocumentDto book = kakaoBookDto.getDocuments().get(0);
        return book.getThumbnail();
    }

    @Override
    public String getBookInfoUrl() {
        KakaoBookDocumentDto book = kakaoBookDto.getDocuments().get(0);
        return book.getKakaoBookUrl();
    }
}
