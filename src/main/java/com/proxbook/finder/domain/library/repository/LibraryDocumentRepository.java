package com.proxbook.finder.domain.library.repository;

import com.proxbook.finder.domain.library.repository.document.LibraryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryDocumentRepository extends ElasticsearchRepository<LibraryDocument, Long> {
    public List<LibraryDocument> findByName(String name);

    public List<LibraryDocument> findByNameOrAddress(String name, String address);
}
