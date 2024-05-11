package com.proxbook.finder.domain.book.dto;

import com.proxbook.finder.domain.book.entity.Book;
import lombok.Getter;

@Getter
public class BookDto {
    private String isbn;
    private String title;
    private String publisher;
    private Integer publishYear;
    private String description;
    private String thumbnailUrl;
    private String bookInfoUrl;


    public BookDto(Book book){
        this.isbn = book.getId();
        this.title = book.getTitle();
        this.publisher = book.getPublisher();
        this.publishYear = book.getPublishYear();
        this.description = book.getDescription();
        this.thumbnailUrl = book.getThumbnailUrl();
        this.bookInfoUrl = book.getBookInfoUrl();
    }
}
