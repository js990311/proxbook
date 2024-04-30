package com.proxbook.finder.domain.librarybook.entity;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class LibraryBookId implements Serializable {
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "library_id")
    private Long libraryId;

    public LibraryBookId(Long bookId, Long libraryId) {
        this.bookId = bookId;
        this.libraryId = libraryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryBookId that = (LibraryBookId) o;
        return Objects.equals(bookId, that.bookId) && Objects.equals(libraryId, that.libraryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, libraryId);
    }
}
