package com.proxbook.finder.domain.library.dto;

import com.proxbook.finder.domain.book.dto.BookDto;
import lombok.Getter;

import java.util.List;

@Getter
public class LibraryBookDto {
    private LibraryDto library;
    private List<BookDto> books;
    private int totalPage;
    private int nowPage;

    public LibraryBookDto(LibraryDto library, List<BookDto> books, int totalPage, int nowPage) {
        this.library = library;
        this.books = books;
        this.totalPage = totalPage;
        this.nowPage = nowPage;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private LibraryDto library;
        private List<BookDto> books;
        private int totalPage;
        private int nowPage;

        public LibraryBookDto build(){
            return new LibraryBookDto(library,books, totalPage, nowPage);
        }

        public Builder setLibrary(LibraryDto library) {
            this.library = library;
            return this;
        }

        public Builder setBooks(List<BookDto> books) {
            this.books = books;
            return this;
        }

        public Builder setTotalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public Builder setNowPage(int nowPage) {
            this.nowPage = nowPage;
            return this;
        }
    }
}
