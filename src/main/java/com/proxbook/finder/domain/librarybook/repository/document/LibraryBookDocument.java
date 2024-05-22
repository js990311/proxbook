package com.proxbook.finder.domain.librarybook.repository.document;

import org.springframework.data.annotation.Id;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Document(indexName = "library_books", createIndex = false)
public class LibraryBookDocument {
    @Id private String id;
    @Field(name = "library_id") private Long libraryId;
    @Field(name = "book_id") private Long bookId;
    private String title;
}
