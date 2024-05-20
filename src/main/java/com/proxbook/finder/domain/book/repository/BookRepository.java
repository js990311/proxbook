package com.proxbook.finder.domain.book.repository;

import com.proxbook.finder.domain.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE concat('%', :title, '%')")
    public List<Book> findByTitle(String title);

    @Query("select b FROM Book b where b.id IN (SELECT lb.bookId FROM LibraryBook lb WHERE lb.libraryId = :libraryId)")
    public List<Book> findLibraryBooksByLibraryId(Long libraryId);

    @Query(value = "select b FROM Book b where b.id IN (SELECT lb.bookId FROM LibraryBook lb WHERE lb.libraryId = :libraryId)",
    countQuery = "select count(b) FROM Book b where b.id IN (SELECT lb.bookId FROM LibraryBook lb WHERE lb.libraryId = :libraryId)")
    public Page<Book> findLibraryBooksByLibraryId(Long libraryId, Pageable pageable);

}
