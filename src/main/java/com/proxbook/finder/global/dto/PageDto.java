package com.proxbook.finder.global.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageDto <T>{
    private PageMetaData pageMetaData;
    private List<T> datas;

    public PageDto(Page<T> page) {
        this.datas = page.getContent();
        this.pageMetaData = new PageMetaData(
                page.getTotalElements(),
                page.isLast(),
                page.getTotalPages(),
                page.getNumber()+1
        );
    }
}
