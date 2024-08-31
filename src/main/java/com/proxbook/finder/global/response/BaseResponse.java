package com.proxbook.finder.global.response;

import lombok.Getter;

@Getter
public class BaseResponse <T>{
    private BaseMeta meta;
    private T contents;

    public BaseResponse(T contents){
        this.contents = contents;
        this.meta = new BaseMeta();
    }

    public BaseResponse(T contents, BaseMeta meta) {
        this.contents = contents;
        this.meta = meta;
    }
}
