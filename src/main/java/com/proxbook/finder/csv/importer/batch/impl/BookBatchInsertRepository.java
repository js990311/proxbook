package com.proxbook.finder.csv.importer.batch.impl;

import com.proxbook.finder.csv.importer.CsvEntityType;
import com.proxbook.finder.csv.importer.batch.CsvBatchInsertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class BookBatchInsertRepository implements CsvBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BATCH_INSERT = "INSERT INTO temp_library_books (id, title, library_id, book_id) VALUES (?,?,?,?)";
    private final String CREATE_TEMP_LIBRARY_BOOKS = """
            CREATE TABLE IF NOT EXISTS temp_library_books (
                id VARCHAR(255),
                book_id BIGINT,
                title VARCHAR(255),
                library_id BIGINT,
                PRIMARY KEY (id, library_id)    
            );
        """;

    private final String DROP_TEMP_LIBRARY_BOOKS = "DROP TABLE IF EXISTS temp_library_books";

    @Transactional
    @Override
    public void batchInsert(List<String[]> rows) {
        try{
            log.debug("create table");
            jdbcTemplate.execute(CREATE_TEMP_LIBRARY_BOOKS);

            log.debug("batch datas");
            jdbcTemplate.batchUpdate(BATCH_INSERT, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    String[] row = rows.get(i);
                    ps.setString(1, row[0]);
                    ps.setString(2, row[6]);
                    ps.setLong(3, Long.parseLong(row[7]));
                    ps.setLong(4, Long.parseLong(row[23]));
                }

                @Override
                public int getBatchSize() {
                    return rows.size();
                }
            });
        }finally {
            log.debug("drop table");
            jdbcTemplate.execute(DROP_TEMP_LIBRARY_BOOKS);
        }
    }

    @Override
    public boolean isSupport(CsvEntityType type) {
        return type == CsvEntityType.Book;
    }
}
