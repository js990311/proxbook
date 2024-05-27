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
    /**
     * 대체키 겸 shorten service에서 사용할 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_prox_library_id")
    private Long id;

    /**
     * 사용자 근처에 있는 도서관의 관계테이블
     */
    @OneToMany(mappedBy = "userProxLibrary", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProxLibrary> proxLibraries;

    /**
     * 책 (nullable)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    /**
     * 사용자의 위도
     */
    @Column
    private Double latitude;

    /**
     * 사용자의 경도
     */
    @Column
    private Double longitude;

    /**
     * 도서관과 사용자간 최대 거리
     */
    @Column(name = "distance_range")
    private Double range;

    public UserProxLibrary(Book book, Double latitude, Double longitude, Double range, List<ProxLibrary> proxLibraries) {
        this.book = book;
        this.latitude = latitude;
        this.longitude = longitude;
        this.range = range;
        this.proxLibraries = proxLibraries;
    }

    public static Builder builder(){
        return new Builder();
    }

    /**
     * Builder for UserProxLibrary
     */
    public static class Builder{
        private Book book;

        private List<ProxLibrary> proxLibraries;

        private Double latitude;

        private Double longitude;
        private Double range;

        /**
         * UserProxLibrary 생성 및 ProxLibrary와의 mapping도 진행
         * @return 생성된 UserProxLibrary
         */
        public UserProxLibrary build(){
            UserProxLibrary userProxLibrary = new UserProxLibrary(book, latitude, longitude, range, proxLibraries);
            // 빌더에서 양방향 연관관계를 설정
            for(ProxLibrary proxLibrary : proxLibraries){
                proxLibrary.setUserProxLibrary(userProxLibrary);
            }
            return userProxLibrary;
        }

        public Builder setProxLibraries(List<ProxLibrary> proxLibraries) {
            this.proxLibraries = proxLibraries;
            return this;
        }

        public Builder setBook(Book book) {
            this.book = book;
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
