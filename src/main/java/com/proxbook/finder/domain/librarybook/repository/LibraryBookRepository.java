package com.proxbook.finder.domain.librarybook.repository;

import com.proxbook.finder.domain.librarybook.entity.LibraryBook;
import com.proxbook.finder.domain.librarybook.entity.LibraryBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, LibraryBookId> {

    @Query("SELECT lb.libraryId FROM LibraryBook lb where lb.bookId = :bookId")
    public List<String> findLibraryIdByBookId(String bookId);
}
