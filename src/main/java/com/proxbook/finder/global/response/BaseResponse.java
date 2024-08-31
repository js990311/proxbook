package com.proxbook.finder.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Schema(description = "API response의 통일성을 위해 작성했는데 제거해도 상관없는 듯")
@Getter
public class BaseResponse <T>{
    @Schema(description = "메타데이터")
    private final BaseMeta meta;
    @Schema(description = "응답의 본문")
    private final T contents;

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
