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
    @Column(name = "user_prox_book_library_id")
    private Long userProxBookLibraryId;

    @Column(name = "distance")
    private Double distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", insertable = false, updatable = false)
    private Library library;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_prox_book_library_id", insertable = false, updatable = false)
    private UserProx userProx;


    void setUserProx(UserProx userProx){
        this.userProx = userProx;
        this.userProxBookLibraryId = userProx.getId();
    }

    public ProxLibrary(Long userProxBookLibraryId, Library library, Double distance) {
        this.userProxBookLibraryId = userProxBookLibraryId;
        this.library = library;
        this.libraryId = library.getId();
        this.distance = distance;
    }

    public ProxLibrary(Library library, Double distance) {
        this.library = library;
        this.libraryId = library.getId();
        this.distance = distance;
    }

}
