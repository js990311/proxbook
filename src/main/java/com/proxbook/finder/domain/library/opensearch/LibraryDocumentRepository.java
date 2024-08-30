package com.proxbook.finder.domain.library.opensearch;

import com.proxbook.finder.domain.library.opensearch.document.LibraryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryDocumentRepository extends ElasticsearchRepository<LibraryDocument, String> {
    public List<LibraryDocument> findByName(String name);
    public List<LibraryDocument> findByNameOrAddress(String name, String address);
    public List<LibraryDocument> findByAddress(String address);
}
