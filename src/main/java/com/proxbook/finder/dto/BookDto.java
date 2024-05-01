package com.proxbook.finder.dto;

import com.proxbook.finder.domain.book.entity.Book;
import lombok.Getter;

@Getter
public class BookDto {
    private String isbn;
    private String title;
    private String publisher;
    private Integer publishYear;

    public BookDto(Book book){
        this.isbn = book.getId();
        this.title = book.getTitle();
        this.publisher = book.getPublisher();
        this.publishYear = book.getPublishYear();
    }
}
