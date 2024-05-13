package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.entity.Book;

import java.util.List;

public interface BookService {
    public List<BookDto> readBookByTitle(String title);
    public BookDto readBookByBookId(Long bookId);
}
