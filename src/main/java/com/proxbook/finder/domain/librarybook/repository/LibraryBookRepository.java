package com.proxbook.finder.domain.librarybook.repository;

import com.proxbook.finder.domain.librarybook.entity.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {

    @Query("SELECT lb.libraryId FROM LibraryBook lb where lb.bookId = :bookId")
    public List<Long> findLibraryIdByBookId(Long bookId);
}
