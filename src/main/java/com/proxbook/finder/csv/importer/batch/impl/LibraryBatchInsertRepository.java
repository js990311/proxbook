package com.proxbook.finder.csv.importer.batch.impl;

import com.proxbook.finder.csv.importer.CsvEntityType;
import com.proxbook.finder.csv.importer.batch.CsvBatchInsertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LibraryBatchInsertRepository implements CsvBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BATCH_INSERT = "INSERT INTO libraries (library_id, name, address, latitude, longitude, webpage) VALUES (?,?,?,?,?,?)";

    @Override
    public void batchInsert(List<String[]> rows) {
        jdbcTemplate.batchUpdate(BATCH_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String[] row = rows.get(i);
                ps.setLong(1, Long.parseLong(row[0])); // 도서관 코드
                ps.setString(2, row[1]);  // 도서관명
                ps.setString(3, row[2]); // 도서관 주소
                ps.setDouble(4, Double.parseDouble(row[3])); // latitude
                ps.setDouble(5, Double.parseDouble(row[4])); // longitude
                ps.setString(6, row[11]); // 홈페이지
            }

            @Override
            public int getBatchSize() {
                return rows.size();
            }
        });

    }

    @Override
    public boolean isSupport(CsvEntityType type) {
        return type == CsvEntityType.Library;
    }
}
