package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookUpdateService, BookService{
    private final BookRepository bookRepository;

    @Override
    public boolean needUpdate(Book book) {
        if(book.getPublishYear() == null)
            return true;
        else
            return false;
    }

    @Override
    public Book updateBook(Book book) {
        return book;
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        for(Book book : books){
            if(needUpdate(book))
                updateBook(book);
        }
        return bookRepository.findByTitle(title);
    }

    @Override
    public Book findBookById(String id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
