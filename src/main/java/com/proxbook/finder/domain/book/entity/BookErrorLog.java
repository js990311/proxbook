package com.proxbook.finder.domain.book.entity;

import com.proxbook.finder.domain.book.repository.BookErrorLogRepository;
import com.proxbook.finder.domain.common.entity.BaseTimeEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    private Book book;

    public BookErrorLog(Long bookId) {
        this.bookId = bookId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Long bookId;
        public BookErrorLog build(){
            return new BookErrorLog(bookId);
        }

        public Builder setBookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder setBook(Book book) {
            this.bookId = book.getId();
            return this;
        }
    }
}
