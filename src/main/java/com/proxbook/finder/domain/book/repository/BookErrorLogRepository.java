package com.proxbook.finder.domain.book.repository;

import com.proxbook.finder.domain.book.entity.BookErrorLog;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookErrorLogRepository extends JpaRepository<BookErrorLog, Long> {
    @Query("SELECT be FROM BookErrorLog be join fetch be.book")
    List<BookErrorLog> findAllWithBookBy();

    @Query("SELECT be FROM BookErrorLog be join fetch be.book where be.id=:id")
    BookErrorLog findWithBookById(Long id);

    List<BookErrorLog> findByBookId(Long bookId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from book_error_logs where book_id = :bookId", nativeQuery = true)
    void deleteByBookId(Long bookId);
}
