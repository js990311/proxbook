package com.proxbook.finder.domain.book.repository.document;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Document(indexName = "books")
public class BookDocument {
    @Id private String id;
    @Field(name="book_id") private Long bookId;
    private String title;
    private String publisher;
    private Integer publishYear;
    private String description;
    private String thumbnailUrl;
    private String bookInfoUrl;

    @Field("title.nori") private String titleNori;
    @Field("title.ngram") private String titleNgram;
}
