package com.proxbook.finder.domain.book.opensearch.document;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Document(indexName = "books")
public class BookDocument {
    @Id private String id;
    @Field(name="book_id") private Long bookId;
    @Field
    private String title;
    @Field
    private String publisher;
    @Field
    private Integer publishYear;
    @Field
    private String description;
    @Field
    private String thumbnailUrl;
    @Field
    private String bookInfoUrl;
}
