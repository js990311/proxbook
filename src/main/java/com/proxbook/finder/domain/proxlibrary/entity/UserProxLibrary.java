package com.proxbook.finder.domain.proxlibrary.entity;

import com.proxbook.finder.domain.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 유저의 현재 위치에서 가장 까가운 도서관 목록 모음
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "user_prox_library")
public class UserProxLibrary{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_prox_library_id")
    private Long id;

    @OneToMany(mappedBy = "userProxLibrary", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProxLibrary> proxLibraries;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable=false, updatable=false)
    private Book book;

    @Column(name = "book_id")
    private String bookId;

    public UserProxLibrary(Book book) {
        this.book = book;
        this.bookId = book.getId();
    }

    public void addProxLibraries(List<ProxLibrary> proxLibraryList){
        // this.proxLibraries = proxLibraryList;
        if(this.proxLibraries == null)
            this.proxLibraries = new ArrayList<>();
        for(ProxLibrary proxLibrary : proxLibraryList){
            proxLibrary.setUserProxLibrary(this);
            proxLibraries.add(proxLibrary);
        }
    }

}
