package com.proxbook.finder.domain.book.dto;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryPageDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BookLibraryPageDto {
    private BookDto book;
    private LibraryPageDto libraries;

    public BookLibraryPageDto(BookDto bookDto, Page<LibraryDto> libraries) {
        this.book = bookDto;
        this.libraries = new LibraryPageDto(libraries);
    }
}
