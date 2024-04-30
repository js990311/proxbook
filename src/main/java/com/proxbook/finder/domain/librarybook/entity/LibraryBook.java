package com.proxbook.finder.domain.librarybook.entity;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.library.entity.Library;
import jakarta.persistence.*;

@Entity
@Table(name = "library_books")
@IdClass(value = LibraryBookId.class)
public class LibraryBook {
    @Id
    @Column(name = "book_id")
    private String bookId;

    @Id
    @Column(name = "library_id")
    private String libraryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book books;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", referencedColumnName = "library_id")
    private Library libraries;
}
