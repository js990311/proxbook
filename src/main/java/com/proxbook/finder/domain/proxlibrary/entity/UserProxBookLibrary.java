package com.proxbook.finder.domain.proxlibrary.entity;

import com.proxbook.finder.domain.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("book")
public class UserProxBookLibrary extends UserProx{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable=false, updatable=false)
    private Book book;

    @Column(name = "book_id")
    private String bookId;

    public UserProxBookLibrary(Book book) {
        super();
        this.book = book;
        this.bookId = book.getId();
    }
}
