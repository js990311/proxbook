package com.proxbook.finder.csv.importer.file.repository;

import com.proxbook.finder.csv.importer.file.entity.CsvFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CsvFilesRepository extends JpaRepository<CsvFiles, Long> {
    Optional<CsvFiles> findByFileId(@Param("fileId") String fileId);
}
