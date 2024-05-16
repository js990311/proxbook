package com.proxbook.finder.domain.library.repository.document;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Elasticsearch의 삽입은 logstash에 의해 작동하므로, 모든 속성에 readOnlyProperty가 적용
 */
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
