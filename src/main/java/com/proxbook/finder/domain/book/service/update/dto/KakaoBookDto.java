package com.proxbook.finder.domain.book.service.update.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoBookDto {

    @JsonProperty("documents")
    private List<KakaoBookDocumentDto> documents;

    @JsonProperty("meta")
    private KakaoMetaDto meta;
}
