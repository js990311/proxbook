package com.proxbook.finder.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

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
