package com.proxbook.finder.csv.importer.batch;

import com.proxbook.finder.csv.importer.CsvEntityType;

import java.util.List;

public interface CsvBatchInsertRepository {
    void batchInsert(List<String[]> rows);
    boolean isSupport(CsvEntityType type);
}
