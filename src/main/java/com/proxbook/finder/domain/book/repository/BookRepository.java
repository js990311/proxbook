package com.proxbook.finder.domain.book.repository;

import com.proxbook.finder.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,String> {
}
