package com.proxbook.finder.domain.reports.book.entity;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.reports.base.entity.BaseReports;
import com.proxbook.finder.domain.reports.base.entity.ReportsType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 책에 대한 유저의 오류 신고 등
 */
@NoArgsConstructor
@Entity
@Getter
public class BookReports extends BaseReports {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "book_id", updatable = false, insertable = false)
    private Long bookId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public BookReports(Book book,String title, String message) {
        super(ReportsType.BOOK, title, message);
        connectBook(book);
    }

    private void connectBook(Book book){
        this.book = book;
    }
}
