package com.proxbook.finder.domain.book.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoBookDocumentDto {

    @JsonProperty("authors")
    private List<String> authors;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("datetime")
    private String publishDate;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String kakaoBookUrl;
}
