package com.proxbook.finder.service;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.service.BookService;
import com.proxbook.finder.domain.book.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookSearchServiceImpl implements BookSearchService{
    private final BookService bookService;


    @Override
    public List<BookDto> searchBooksByTitle(String title) {
        List<BookDto> books = bookService.findBookByTitle(title).stream().map(BookDto::new).toList();
        return books;
    }

    @Override
    public BookDto searchBookById(String id) {
        Book book = bookService.findBookById(id);
        BookDto bookDto = new BookDto(book);
        return bookDto;
    }
}
