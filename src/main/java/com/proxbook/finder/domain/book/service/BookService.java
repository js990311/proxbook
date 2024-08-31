package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.dto.BookLibraryPageDto;
import com.proxbook.finder.domain.book.dto.BookPageDto;
import com.proxbook.finder.domain.book.dto.UpdateBookDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.exception.BookNotFoundException;
import com.proxbook.finder.domain.book.repository.BookRepository;
import com.proxbook.finder.domain.book.opensearch.repository.BookSearchRepository;
import com.proxbook.finder.domain.book.service.update.BookUpdateSourceService;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryPageDto;
import com.proxbook.finder.domain.library.opensearch.LibrarySearchRepository;
import com.proxbook.finder.domain.reports.book.service.BookReportsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookService implements BookUpdateService{
    private final BookRepository bookRepository;
    private final BookSearchRepository bookSearchRepository;
    private final BookUpdateSourceService bookUpdateSourceService;
    private final BookReportsService bookReportsService;
    private final LibrarySearchRepository librarySearchRepository;

    private static String updateBookExceptionFormat = "UPDATE BOOK ERROR : %d";

    @Override
    public boolean needUpdate(Book book) {
        if(book.getPublishYear() == null)
            return true;
        else
            return false;
    }

    @Override
    public Book updateBook(Book book) {
        try {
            UpdateBookDto updateBookDto = bookUpdateSourceService.getBookSourceByIsbn(book.getIsbn());
            book.updateBookInfo(updateBookDto);
            return book.updateBookInfo(updateBookDto);
        }catch (RuntimeException e){
            log.error("update Book Exception : ",e);
            bookReportsService.registSystemBookReports(book, updateBookExceptionFormat);
            return book;
        }
    }

    public BookPageDto readBookByTitle(String title, Integer page) {
        Page<BookDto> books = bookSearchRepository.findBookBtTitle(title, page, 20);
        return new BookPageDto(books);
    }

    public BookDto readBookByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));
        if(needUpdate(book))
            book = updateBook(book);
        return convertBookDto(book);
    }

    public BookLibraryPageDto readLibraryByBookId(Long id, Integer page) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        PageRequest pageRequest = PageRequest.of(
                page, 20
        );
        Page<LibraryDto> libraries = librarySearchRepository.findLibrariesByBookId(id, pageRequest);
        return new BookLibraryPageDto(convertBookDto(book), libraries);
    }

    private BookDto convertBookDto(Book book){
        return BookDto.from(book);
    }

}
