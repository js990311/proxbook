package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.dto.UpdateBookDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.repository.BookRepository;
import com.proxbook.finder.domain.book.repository.BookSearchRepository;
import com.proxbook.finder.domain.book.service.update.BookUpdateSourceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookUpdateService, BookService{
    private final BookRepository bookRepository;
    private final BookSearchRepository bookSearchRepository;
    private final BookUpdateSourceService bookUpdateSourceService;
    private final BookErrorRegistService bookErrorRegistService;

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
            bookErrorRegistService.registBookError(book.getId(), "Book Update Data is Empty.");
            return book;
        }
    }

    @Override
    public List<BookDto> readBookByTitle(String title) {
        return bookSearchRepository.findBookBtTitle(title);
    }

    @Override
    public BookDto readBookByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
        if(needUpdate(book))
            book = updateBook(book);
        return convertBookDto(book);
    }

    private BookDto convertBookDto(Book book){
        return BookDto.from(book);
    }
}
