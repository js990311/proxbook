package com.proxbook.finder.domain.library.opensearch;

import com.proxbook.finder.domain.library.opensearch.document.LibraryDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryDocumentRepository extends ElasticsearchRepository<LibraryDocument, String> {
    public Page<LibraryDocument> findByName(String name, Pageable pageable);
    public Page<LibraryDocument> findByNameOrAddress(String name, String address, Pageable pageable);
    public Page<LibraryDocument> findByAddress(String address, Pageable pageable);
}
