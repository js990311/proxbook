package com.proxbook.finder.domain.library.dto;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.dto.BookPageDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.library.entity.Library;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class LibraryBookPageDto {
    private LibraryDto library;
    private BookPageDto books;

    public LibraryBookPageDto(LibraryDto library, BookPageDto books) {
        this.library = library;
        this.books = books;
    }

    public LibraryBookPageDto(LibraryDto library, Page<BookDto> books){
        this.library = library;
        this.books = new BookPageDto(books);
    }
}
