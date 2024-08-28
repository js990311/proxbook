package com.proxbook.finder.domain.reports.api.response.book;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.reports.api.response.BaseReportsResponse;
import com.proxbook.finder.domain.reports.base.entity.ReportsType;
import lombok.Data;
import lombok.Getter;

@Getter
public class BookReportsResponse extends BaseReportsResponse {
    private Long bookId;
    private String bookTitle;

    public BookReportsResponse(Long id, String title, String message, Book book){
        super(id,title,message,ReportsType.BOOK);
        this.bookId = book.getId();
        this.bookTitle = book.getTitle();
    }
}
