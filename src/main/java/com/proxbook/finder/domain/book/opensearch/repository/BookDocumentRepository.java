package com.proxbook.finder.domain.book.opensearch.repository;

import com.proxbook.finder.domain.book.opensearch.document.BookDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookDocumentRepository extends ElasticsearchRepository<BookDocument, String> {
    public List<BookDocument> findByTitle(String title);
    public Page<BookDocument> findByTitle(String title, Pageable pageable);



}
