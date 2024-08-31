package com.proxbook.finder.domain.book.dto;

import com.proxbook.finder.global.dto.PageDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BookPageDto extends PageDto<BookDto> {
    public BookPageDto(Page<BookDto> page){
        super(page);
    }
}
