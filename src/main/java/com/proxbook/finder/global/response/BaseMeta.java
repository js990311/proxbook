package com.proxbook.finder.global.response;

import lombok.Data;
import lombok.Getter;

@Getter
public class BaseMeta {
    private Integer statusCode;
    private String message;

    public BaseMeta(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
