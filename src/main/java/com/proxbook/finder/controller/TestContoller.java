package com.proxbook.finder.controller;

import com.proxbook.finder.api.book.dto.KakaoBookDto;
import com.proxbook.finder.api.book.service.KakaoBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestContoller {
    private final KakaoBookService kakaoBookService;

    @GetMapping("/kakaoBook")
    public KakaoBookDto kakaoBookDto(){
        KakaoBookDto kakaoBookDto = kakaoBookService.requestBookApi("9788950957391");
        return kakaoBookDto;
    }
}
