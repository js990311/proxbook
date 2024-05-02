package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.api.book.dto.KakaoBookDto;
import com.proxbook.finder.api.book.service.KakaoBookService;
import com.proxbook.finder.domain.book.dto.KakaoUpdateBookDto;
import com.proxbook.finder.domain.book.dto.UpdateBookDto;
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
    private final KakaoBookService kakaoBookService;

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
            KakaoBookDto kakaoBookDto = kakaoBookService.requestBookApi(book.getId());
            UpdateBookDto updateBookDto = new KakaoUpdateBookDto(kakaoBookDto);
            return Book.updateBookInfo(book, updateBookDto);
        }catch (RuntimeException e){
            return book;
        }
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
//        for(Book book : books){
//            if(needUpdate(book))
//                updateBook(book);
//        }
        return books;
    }

    @Override
    public Book findBookById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(needUpdate(book))
            book = updateBook(book);
        return book;
    }
}
