package com.proxbook.finder.domain.book.repository;

import com.proxbook.finder.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,String> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE concat('%', :title, '%')")
    public List<Book> findByTitle(String title);
}
