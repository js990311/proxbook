package com.proxbook.finder.domain.book.entity;

import com.proxbook.finder.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "book_error_logs")
@Getter
@Entity
public class BookErrorLog extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "book_error_log_id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    private Book book;

    public BookErrorLog(Long bookId, String reason) {
        this.bookId = bookId;
        this.reason = reason;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Long bookId;
        private String reason;

        public BookErrorLog build(){
            return new BookErrorLog(bookId, reason);
        }

        public Builder setBookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder setBook(Book book) {
            this.bookId = book.getId();
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }
    }
}
