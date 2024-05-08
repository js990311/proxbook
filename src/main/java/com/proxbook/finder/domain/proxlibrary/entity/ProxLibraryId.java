package com.proxbook.finder.domain.proxlibrary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class ProxLibraryId implements Serializable {
    @Column(name = "library_id")
    private String libraryId;

    @Id
    @Column(name = "user_prox_library_id")
    private Long userProxLibraryId;

    public ProxLibraryId(String libraryId, Long userProxLibraryId) {
        this.libraryId = libraryId;
        this.userProxLibraryId = userProxLibraryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProxLibraryId that = (ProxLibraryId) o;
        return Objects.equals(libraryId, that.libraryId) && Objects.equals(userProxLibraryId, that.userProxLibraryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryId, userProxLibraryId);
    }
}
