package com.proxbook.finder.domain.proxlibrary.repository;

import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProxLibraryRepository extends JpaRepository<ProxLibrary, Long> {
    @Query("SELECT pl FROM ProxLibrary pl join fetch pl.library where pl.userProxLibrary.id = :userProxLibraryId ORDER BY pl.distance")
    public List<ProxLibrary> findByUserProxLibraryIdOrderByDistance(Long userProxLibraryId);
}
