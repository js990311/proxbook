package com.proxbook.finder.domain.proxlibrary.entity;

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
public class UserProxLibrary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_prox_library_id")
    private Long id;

    @OneToMany(mappedBy = "userProxLibraryId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProxLibrary> proxLibraries;

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
