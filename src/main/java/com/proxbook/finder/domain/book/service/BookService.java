package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.entity.Book;

import java.util.List;

public interface BookService {
    public List<BookDto> findBookByTitle(String title);
    public BookDto findBookById(String id);
}
