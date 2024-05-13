package com.proxbook.finder.domain.library.dto;

import com.proxbook.finder.domain.book.dto.BookDto;
import lombok.Getter;

import java.util.List;

@Getter
public class LibraryBookDto {
    private LibraryDto library;
    private List<BookDto> books;

    public LibraryBookDto(LibraryDto library, List<BookDto> books) {
        this.library = library;
        this.books = books;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private LibraryDto library;
        private List<BookDto> books;

        public LibraryBookDto build(){
            return new LibraryBookDto(library,books);
        }

        public Builder setLibrary(LibraryDto library) {
            this.library = library;
            return this;
        }

        public Builder setBooks(List<BookDto> books) {
            this.books = books;
            return this;
        }
    }
}
