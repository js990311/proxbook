package com.proxbook.finder.csv.importer.batch;

import com.proxbook.finder.csv.importer.CsvEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CsvBatchInsertRepositoryManager {
    private final List<CsvBatchInsertRepository> batchInsertRepositories;

    @Transactional
    public void batchInsert(CsvEntityType type, List<String[]> rows){
        for(CsvBatchInsertRepository batchInsertRepository : batchInsertRepositories){
            if(batchInsertRepository.isSupport(type)){
                batchInsertRepository.batchInsert(rows);
                return;
            }
        }
        throw new RuntimeException();
    }
}
