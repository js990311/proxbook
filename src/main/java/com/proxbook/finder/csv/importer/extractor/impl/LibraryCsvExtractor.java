package com.proxbook.finder.csv.importer.extractor.impl;

import com.proxbook.finder.csv.importer.CsvEntityType;
import com.proxbook.finder.csv.importer.extractor.AbstractCsvExtractor;
import org.springframework.stereotype.Component;

@Component
public class LibraryCsvExtractor extends AbstractCsvExtractor {
    @Override
    public boolean isSupport(CsvEntityType type) {
        return type == CsvEntityType.Library;
    }

    @Override
    public boolean isValid(String[] row) {
        return isLong(row[0])       // 도서관 코드
                && isBlank(row[1]) // 도서관명
                && isBlank(row[2]) // 도서관주소
                && isBlank(row[3]) // 위도
                && isDouble(row[4]) // 경도
                && isBlank(row[11]) // 홈페이지
        ;
    }
}
