package com.proxbook.finder.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "페이지네이션을 사용하는 객체에 대한 메타데이터")
@Getter
public class PageMetaData {
    @Schema(description = "전체 데이터의 개수")
    private final Long totalCount;
    @Schema(description = "마지막 페이지인가?")
    private final boolean isLast;
    @Schema(description = "페이지의 총 개수")
    private final Integer pageAbleCount;
    @Schema(description = "다음 페이지에 대한 인덱스(다음 페이지는 이 값을 사용하여 검색할 것)")
    private final String nextPageIndex;

    public PageMetaData(Long totalCount, boolean isLast, Integer pageAbleCount, Integer nextPageIndex) {
        this.totalCount = totalCount;
        this.isLast = isLast;
        this.pageAbleCount = pageAbleCount;
        this.nextPageIndex = nextPageIndex.toString();
    }
}
