package com.proxbook.finder.domain.proxlibrary.entity;

import com.proxbook.finder.domain.library.entity.Library;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "prox_libraries")
@IdClass(value = ProxLibraryId.class)
public class ProxLibrary {
    @Id
    @Column(name = "library_id")
    private String libraryId;

    @Id
    @Column(name = "user_prox_library_id")
    private Long userProxLibraryId;

    @Column(name = "distance")
    private Double distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", insertable = false, updatable = false)
    private Library library;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_prox_library_id", insertable = false, updatable = false)
    private UserProxLibrary userProxLibrary;


    void setUserProxLibrary(UserProxLibrary userProxLibrary){
        this.userProxLibrary = userProxLibrary;
        this.userProxLibraryId = userProxLibrary.getId();
    }

    public ProxLibrary(Library library, Double distance) {
        this.library = library;
        this.libraryId = library.getId();
        this.distance = distance;
    }

    public ProxLibrary(String libraryId, Long userProxLibraryId, Double distance, Library library, UserProxLibrary userProxLibrary) {
        this.libraryId = libraryId;
        this.userProxLibraryId = userProxLibraryId;
        this.distance = distance;
        this.library = library;
        this.userProxLibrary = userProxLibrary;
    }

    public static class Builder{
        private String libraryId;
        private Long userProxLibraryId;
        private Double distance;
        private Library library;
        private UserProxLibrary userProxLibrary;

        public ProxLibrary build(){
            return new ProxLibrary(
                    libraryId,
                    userProxLibraryId,
                    distance,
                    library,
                    userProxLibrary
            );
        }

        public Builder setLibraryId(String libraryId) {
            this.libraryId = libraryId;
            return this;
        }

        public Builder setUserProxLibraryId(Long userProxLibraryId) {
            this.userProxLibraryId = userProxLibraryId;
            return this;
        }

        public Builder setDistance(Double distance) {
            this.distance = distance;
            return this;
        }

        public Builder setLibrary(Library library) {
            this.library = library;
            this.libraryId = library.getId();
            return this;
        }

        public Builder setUserProxLibrary(UserProxLibrary userProxLibrary) {
            this.userProxLibrary = userProxLibrary;
            this.userProxLibraryId = userProxLibrary.getId();
            return this;
        }
    }
}
