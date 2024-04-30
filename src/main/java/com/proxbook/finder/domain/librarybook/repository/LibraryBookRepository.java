package com.proxbook.finder.domain.librarybook.repository;

import com.proxbook.finder.domain.librarybook.entity.LibraryBook;
import com.proxbook.finder.domain.librarybook.entity.LibraryBookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, LibraryBookId> {
}
