package com.proxbook.finder.domain.reports.book.service;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.exception.BookNotFoundException;
import com.proxbook.finder.domain.book.repository.BookRepository;
import com.proxbook.finder.domain.reports.api.response.book.BookReportsResponse;
import com.proxbook.finder.domain.reports.book.entity.BookReports;
import com.proxbook.finder.domain.reports.book.repository.BookReportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookReportsService {
    private final BookReportsRepository bookReportsRepository;
    private final BookRepository bookRepository;
    private static String SYSTEM_REPORTED_TITLE = "SYSTEM_REPORT_BOOK_EXCEPTION";

    /**
     * Book에 대한 문의를 작성함
     * @param bookId 책에 대한 아이디
     * @param title 문의 제목
     * @param message 문의 메시지
     * @return
     */
    @Transactional
    public BookReportsResponse createBookReports(Long bookId, String title, String  message){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        BookReports bookReports = new BookReports(book, title, message);
        Long id = bookReportsRepository.save(bookReports).getId();
        return convertToResponse(book, bookReports, id);
    }

    private BookReportsResponse convertToResponse(Book book, BookReports bookReports, Long id){
        return new BookReportsResponse(id, bookReports.getTitle(), bookReports.getMessage(), book);
    }

    private BookReportsResponse convertToResponse(Book book, BookReports bookReports){
        return convertToResponse(book, bookReports, bookReports.getId());
    }

    /**
     * 시스템이 작성하는 Book Error
     */
    public void registSystemBookReports(Book book, String format) {
        BookReports bookReports = new BookReports(book, SYSTEM_REPORTED_TITLE, String.format(format, book.getId()));
        bookReportsRepository.save(bookReports);
    }
}
