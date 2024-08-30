package com.proxbook.finder.domain.book.dto;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.opensearch.document.BookDocument;
import com.proxbook.finder.domain.librarybook.repository.document.LibraryBookDocument;
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

    public static BookDto from(Book book){
        return builder()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setPublisher(book.getPublisher())
                .setPublishYear(book.getPublishYear())
                .setDescription(book.getDescription())
                .setThumbnailUrl(book.getThumbnailUrl())
                .setBookInfoUrl(book.getBookInfoUrl())
                .build();
    }

    public static BookDto from(BookDocument book){
        return builder()
                .setIsbn(book.getBookId())
                .setTitle(book.getTitle())
                .setPublisher(book.getPublisher())
                .setPublishYear(book.getPublishYear())
                .setDescription(book.getDescription())
                .setThumbnailUrl(book.getThumbnailUrl())
                .setBookInfoUrl(book.getBookInfoUrl())
                .build();
    }

    public static BookDto from(LibraryBookDocument book){
        return builder()
                .setIsbn(book.getBookId())
                .setTitle(book.getTitle())
                .build();
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

        public Builder setIsbn(Long isbn){
            this.isbn = isbn.toString();
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
