package com.proxbook.finder.global.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class BaseListMeta extends BaseMeta{
    private Long totalCount;
    private boolean isEnd;
    private Integer pageAbleCount;
    private String nextPageIndex;

    public BaseListMeta(Integer statusCode, String message, Long totalCount, boolean isEnd, Integer pageAbleCount, String nextPageIndex) {
        super(statusCode, message);
        this.totalCount = totalCount;
        this.isEnd = isEnd;
        this.pageAbleCount = pageAbleCount;
        this.nextPageIndex = nextPageIndex;
    }
}
