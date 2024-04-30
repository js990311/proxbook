package com.proxbook.finder.domain.library.repository;

import com.proxbook.finder.domain.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {
}
