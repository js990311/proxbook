package com.proxbook.finder.global.response;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class BaseListResponse <T>{
    private List<T> contents;
    private BaseListMeta meta;

    BaseListResponse(List<T> contents, BaseListMeta meta) {
        this.contents = contents;
        this.meta = meta;
    }

    public static class Builder<T>{
        private List<T> contents;
        private Integer statusCode;
        private String message;
        private Long totalCount;
        private boolean isEnd;
        private Integer pageAbleCount;
        private String nextPageIndex;

        public BaseListResponse<T> build(){
            return new BaseListResponse<>(contents, buildMeta());
        }

        private BaseListMeta buildMeta(){
            return new BaseListMeta(statusCode, message, totalCount, isEnd, pageAbleCount, nextPageIndex);
        }

        public Builder<T> contents(Page<T> contents) {
            this.contents = contents.getContent();
            return this
                    .end(contents.isLast())
                    .nextPageIndex(contents.isLast() ? null : String.valueOf(contents.getNumber()+1))
                    .totalCount(contents.getTotalElements())
                    .pageAbleCount(contents.getTotalPages());
        }

        public Builder<T> contents(List<T> contents) {
            this.contents = contents;
            return this;
        }

        public Builder<T> statusCode(Integer statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> totalCount(Long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder<T> end(boolean end) {
            isEnd = end;
            return this;
        }

        public Builder<T> pageAbleCount(Integer pageAbleCount) {
            this.pageAbleCount = pageAbleCount;
            return this;
        }

        public Builder<T> nextPageIndex(String nextPageIndex) {
            this.nextPageIndex = nextPageIndex;
            return this;
        }
    }
}
