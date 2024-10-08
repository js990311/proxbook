package com.proxbook.finder.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KakaoBookApiConfig {
    @Value("${kakao.api.key}")
    private String KAKAO_REST_API_KEY;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        StringBuilder sb = new StringBuilder();
        sb.append("KakaoAK ").append(KAKAO_REST_API_KEY);
        return builder
                .defaultHeader("Authorization", sb.toString())
                .build();
    }
}
