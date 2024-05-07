package com.proxbook.finder.domain.proxlibrary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user_prox_book_library")
public class UserProx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_prox_book_library_id")
    private Long id;

    @OneToMany(mappedBy = "userProxBookLibraryId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProxLibrary> proxLibraries;

    @Column(name = "dtype", insertable=false, updatable=false)
    private String dtype;

    public void addProxLibraries(List<ProxLibrary> proxLibraryList){
        // this.proxLibraries = proxLibraryList;
        if(this.proxLibraries == null)
            this.proxLibraries = new ArrayList<>();
        for(ProxLibrary proxLibrary : proxLibraryList){
            proxLibrary.setUserProx(this);
            proxLibraries.add(proxLibrary);
        }
    }
}
