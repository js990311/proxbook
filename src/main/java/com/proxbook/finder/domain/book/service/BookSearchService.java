package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.dto.BookDto;

import java.util.List;

public interface BookSearchService {
    public List<BookDto> readBookByTitle(String title);
}
