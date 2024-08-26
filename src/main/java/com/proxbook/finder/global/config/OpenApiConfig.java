package com.proxbook.finder.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cuopenApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("ProxBook")
                                .description("내 근처 도서관의 소장도서를 찾아주는 서비스입니다.")
                );
    }
}
