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

    public BookDto(String isbn, String title, String publisher, Integer publishYear, String description, String thumbnailUrl, String bookInfoUrl) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.bookInfoUrl = bookInfoUrl;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String isbn;
        private String title;
        private String publisher;
        private Integer publishYear;
        private String description;
        private String thumbnailUrl;
        private String bookInfoUrl;

        public BookDto build(){
            return new BookDto(isbn, title, publisher, publishYear, description, thumbnailUrl, bookInfoUrl);
        }

        public Builder setBook(Book book){
            this
                    .setIsbn(book.getId())
                    .setTitle(book.getTitle())
                    .setPublisher(book.getPublisher())
                    .setPublishYear(book.getPublishYear())
                    .setDescription(book.getDescription())
                    .setThumbnailUrl(book.getThumbnailUrl())
                    .setBookInfoUrl(book.getBookInfoUrl());
            return this;
        }

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setPublishYear(Integer publishYear) {
            this.publishYear = publishYear;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder setBookInfoUrl(String bookInfoUrl) {
            this.bookInfoUrl = bookInfoUrl;
            return this;
        }
    }
}
