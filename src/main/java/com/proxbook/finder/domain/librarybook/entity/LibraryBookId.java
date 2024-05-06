package com.proxbook.finder.domain.librarybook.entity;

import jakarta.persistence.Column;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class LibraryBookId implements Serializable {
    @Column(name = "book_id")
    private String bookId;

    @Column(name = "library_id")
    private String libraryId;

    public LibraryBookId(String bookId, String libraryId) {
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
