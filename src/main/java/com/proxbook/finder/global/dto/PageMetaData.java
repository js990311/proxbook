package com.proxbook.finder.global.dto;

import lombok.Getter;

@Getter
public class PageMetaData {
    private Long totalCount;
    private boolean isEnd;
    private Integer pageAbleCount;
    private String nextPageIndex;

    public PageMetaData(Long totalCount, boolean isEnd, Integer pageAbleCount, Integer nextPageIndex) {
        this.totalCount = totalCount;
        this.isEnd = isEnd;
        this.pageAbleCount = pageAbleCount;
        this.nextPageIndex = nextPageIndex.toString();
    }
}
