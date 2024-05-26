package com.proxbook.finder.domain.book.repository;

import com.proxbook.finder.domain.book.entity.BookErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookErrorLogRepository extends JpaRepository<BookErrorLog, Long> {
    @Query("SELECT be FROM BookErrorLog be join fetch be.book")
    List<BookErrorLog> findAllWithBookBy();

    @Query("SELECT be FROM BookErrorLog be join fetch be.book where be.id=:id")
    BookErrorLog findWithBookById(Long id);
}
