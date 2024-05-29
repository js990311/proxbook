package com.proxbook.finder.domain.book.dto;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.entity.BookErrorLog;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class BookErrorLogDto {
    private Long id;
    private BookDto book;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookErrorLogDto(Long id, BookDto bookDto, String reason, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.book = bookDto;
        this.reason = reason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Long id;
        private BookDto book;
        private String reason;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public BookErrorLogDto build(){
            return new BookErrorLogDto(id, book, reason, createdAt, updatedAt);
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setBook(BookDto bookDto) {
            this.book = bookDto;
            return this;
        }

        public Builder setBook(Book book){
            this.book = BookDto.from(book);
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
    }
}
