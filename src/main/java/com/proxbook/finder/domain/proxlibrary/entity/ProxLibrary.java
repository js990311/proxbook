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

    public ProxLibrary(Long userProxLibrary, Library library, Double distance) {
        this.userProxLibraryId = userProxLibrary;
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
