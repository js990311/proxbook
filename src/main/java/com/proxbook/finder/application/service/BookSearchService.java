package com.proxbook.finder.application.service;

import com.proxbook.finder.domain.book.dto.BookDto;

import java.util.List;

public interface BookSearchService {
    public List<BookDto> searchBooksByTitle(String title);
    public BookDto searchBookById(String id);
}
