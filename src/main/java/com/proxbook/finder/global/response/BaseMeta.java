package com.proxbook.finder.global.response;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseMeta {
    private Integer statusCode;
    private String message;

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
