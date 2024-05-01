package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> findBookByTitle(String title);
    public Book findBookById(String id);
}
