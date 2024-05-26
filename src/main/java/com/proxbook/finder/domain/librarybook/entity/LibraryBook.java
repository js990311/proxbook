package com.proxbook.finder.domain.librarybook.entity;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.common.entity.BaseTimeEntity;
import com.proxbook.finder.domain.library.entity.Library;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "library_books")
public class LibraryBook extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_book_id")
    public Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "library_id")
    private Long libraryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", updatable = false, insertable = false)
    private Book books;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", referencedColumnName = "library_id", updatable = false, insertable = false)
    private Library libraries;

}
