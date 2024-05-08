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

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(name = "distance_range")
    private Double range;

    public UserProxLibrary(Book book, String bookId, Double latitude, Double longitude, Double range) {
        this.book = book;
        this.bookId = bookId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.range = range;
    }

    public void addProxLibraries(List<ProxLibrary> proxLibraryList){
        this.proxLibraries = proxLibraryList;
    }

    public static class Builder{
        private Book book;

        private String bookId;

        private Double latitude;

        private Double longitude;
        private Double range;

        public UserProxLibrary build(){
            return new UserProxLibrary(book, bookId, latitude,longitude,range);
        }

        public Builder setBook(Book book) {
            this.book = book;
            this.bookId = book.getId();
            return this;
        }

        public Builder setBookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setRange(Double range) {
            this.range = range;
            return this;
        }
    }
}
