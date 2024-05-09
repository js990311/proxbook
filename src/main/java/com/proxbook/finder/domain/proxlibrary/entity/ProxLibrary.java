package com.proxbook.finder.domain.proxlibrary.entity;

import com.proxbook.finder.domain.library.entity.Library;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "prox_libraries")
public class ProxLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prox_library_id")
    private Long id;

    /**
     * 사용자와 도서관간 거리
     */
    @Column(name = "distance")
    private Double distance;

    /**
     * 도서관 목록
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;

    /**
     * 사용자 테이블
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_prox_library_id")
    private UserProxLibrary userProxLibrary;

    public ProxLibrary(Double distance, Library library, UserProxLibrary userProxLibrary) {
        this.distance = distance;
        this.library = library;
        this.userProxLibrary = userProxLibrary;
    }

    /**
     * ProxLibrary 생성과정에서는 UserProxLibrary가 있을 수 없으므로 생성 후 주입한다.
     * 무결성을 위해 entity 패키지 내부에서만 접근 가능
     * @param userProxLibrary
     */
    void setUserProxLibrary(UserProxLibrary userProxLibrary) {
        this.userProxLibrary = userProxLibrary;
    }

    /**
     * Builder for ProxLibrary
     */
    public static class Builder{
        private Double distance;
        private Library library;
        private UserProxLibrary userProxLibrary;

        public ProxLibrary build(){
            return new ProxLibrary(
                    distance,
                    library,
                    userProxLibrary
            );
        }

        public Builder setDistance(Double distance) {
            this.distance = distance;
            return this;
        }

        public Builder setLibrary(Library library) {
            this.library = library;
            return this;
        }

        public Builder setUserProxLibrary(UserProxLibrary userProxLibrary) {
            this.userProxLibrary = userProxLibrary;
            return this;
        }
    }
}
