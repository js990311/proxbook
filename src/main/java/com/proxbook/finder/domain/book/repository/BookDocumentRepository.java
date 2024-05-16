package com.proxbook.finder.domain.book.repository;

import com.proxbook.finder.domain.book.repository.document.BookDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookDocumentRepository extends ElasticsearchRepository<BookDocument, String> {
    public List<BookDocument> findByTitle(String title);
}
