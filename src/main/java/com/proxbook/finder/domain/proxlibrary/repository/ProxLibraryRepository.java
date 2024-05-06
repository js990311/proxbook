package com.proxbook.finder.domain.proxlibrary.repository;

import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibraryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxLibraryRepository extends JpaRepository<ProxLibrary, ProxLibraryId> {
}
