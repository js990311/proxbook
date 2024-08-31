package com.proxbook.finder.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Schema(description = "메타데이터 제공을 위한 스키마")
@Getter
public class BaseMeta {
    @Schema(description = "http status code와 동일", example = "200")
    private final Integer statusCode;
    @Schema(description = "http message와 동일(현재까지는)")
    private final String message;

    public BaseMeta(){
        statusCode = 200;
        message = "OK";
    }

    public BaseMeta(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public BaseMeta(HttpStatus status){
        this.statusCode = status.value();
        this.message = status.getReasonPhrase();
    }
}
