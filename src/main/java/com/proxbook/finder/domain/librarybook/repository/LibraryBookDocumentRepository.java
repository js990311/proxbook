package com.proxbook.finder.domain.librarybook.repository;


import com.proxbook.finder.domain.librarybook.repository.document.LibraryBookDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryBookDocumentRepository extends ElasticsearchRepository<LibraryBookDocument, String> {
    List<LibraryBookDocument> findByTitleAndLibraryId(String title, long libraryId);
    Page<LibraryBookDocument> findByTitleAndLibraryId(String title, long libraryId, Pageable pageable);

    List<LibraryBookDocument> findByLibraryId(long libraryId);
    Page<LibraryBookDocument> findByLibraryId(long libraryId, Pageable pageable);
}
