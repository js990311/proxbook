package com.proxbook.finder.myes.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MyElasticSearchQueryRepositoryImpl implements MyElasticSearchQueryRepository{
    private ElasticsearchOperations elasticsearchOperations;

    public void ObjectToJson(){
        ObjectMapper objectMapper = new ObjectMapper();

    }

}
