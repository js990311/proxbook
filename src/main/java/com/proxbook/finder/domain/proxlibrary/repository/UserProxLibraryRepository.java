package com.proxbook.finder.domain.proxlibrary.repository;

import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProxLibraryRepository extends JpaRepository<UserProxLibrary, Long> {
}
