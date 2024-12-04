package com.proxbook.finder.csv.importer.file.service;

import com.proxbook.finder.csv.importer.file.repository.CsvFilesRepository;
import com.proxbook.finder.csv.importer.file.system.FileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CsvFilesService {
    private final CsvFilesRepository csvFilesRepository;
    private final FileSystemAccessObject fileSystemAO;
}
