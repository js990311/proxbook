package com.proxbook.finder.domain.proxlibrary.repository;

import com.proxbook.finder.domain.proxlibrary.entity.UserProxBookLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProxBookLibraryRepository extends JpaRepository<UserProxBookLibrary, Long> {
}
