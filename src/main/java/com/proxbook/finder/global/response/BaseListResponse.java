package com.proxbook.finder.global.response;

import lombok.Data;

import java.util.List;

@Data
public class BaseListResponse <T>{
    private Integer counts;
    private List<T> contents;

    public BaseListResponse(List<T> list){
        this.counts = list.size();
        this.contents = list;
    }
}
