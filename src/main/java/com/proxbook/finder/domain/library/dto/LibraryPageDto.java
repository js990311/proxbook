package com.proxbook.finder.domain.library.dto;

import com.proxbook.finder.global.dto.PageDto;
import com.proxbook.finder.global.dto.PageMetaData;
import org.springframework.data.domain.Page;

import java.util.List;

public class LibraryPageDto extends PageDto<LibraryDto> {

    public LibraryPageDto(Page<LibraryDto> page) {
        super(page);
    }
}
