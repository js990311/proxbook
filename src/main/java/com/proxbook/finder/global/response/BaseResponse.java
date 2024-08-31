package com.proxbook.finder.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponse <T>{
    private BaseMeta meta;
    private T contents;

    public BaseResponse(T contents){
        this.contents = contents;
        this.meta = new BaseMeta();
    }

    public BaseResponse(BaseMeta meta, T contents) {
        this.contents = contents;
        this.meta = meta;
    }

    public BaseResponse(HttpStatus status, T contents){
        this.contents = contents;
        this.meta = new BaseMeta(status);
    }
}
