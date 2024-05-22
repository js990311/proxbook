package com.proxbook.finder.domain.library.repository.document;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Document(indexName = "libraries", createIndex = false)
public class LibraryDocument {
    @Id private String id;
    @Field(name="library_id") private Long libararyId;
    private String name;
    private String address;
    private String webpage;
    private Double latitude;
    private Double longitude;
}
