package com.proxbook.finder.csv.importer.file.service;

import com.proxbook.finder.csv.importer.CsvEntityType;
import com.proxbook.finder.csv.importer.api.dto.CsvExtractResultDto;
import com.proxbook.finder.csv.importer.extractor.CsvExtractResult;
import com.proxbook.finder.csv.importer.extractor.CsvExtractorManager;
import com.proxbook.finder.csv.importer.file.dto.CsvFilesDto;
import com.proxbook.finder.csv.importer.file.dto.ResourceDto;
import com.proxbook.finder.csv.importer.file.entity.CsvFiles;
import com.proxbook.finder.csv.importer.file.repository.CsvFilesRepository;
import com.proxbook.finder.csv.importer.file.system.FileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.Optional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CsvFilesService {
    private final CsvFilesRepository csvFilesRepository;
    private final FileSystemAccessObject fileSystemAO;
    private final CsvExtractorManager extractorManager;

    @Transactional
    public CsvFilesDto saveFile(CsvEntityType type, MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        CsvFiles files = new CsvFiles(type, originalFilename);
        fileSystemAO.save(files.getStorePath(), file);
        csvFilesRepository.save(files);
        return CsvFilesDto.from(files);
    }

    public ResourceDto loadByFilename(String fileId){
        Optional<CsvFiles> opt = csvFilesRepository.findByFileId(fileId);
        if(opt.isEmpty()){
            throw new NoSuchElementException();
        }
        CsvFiles files = opt.get();
        Resource resource = fileSystemAO.load(files.getStorePath());
        return new ResourceDto(resource, files.getOriginalFileName());
    }

    @Transactional
    public CsvExtractResultDto extractCsv(String fileId){
        /* 불러오기 */
        Optional<CsvFiles> opt = csvFilesRepository.findByFileId(fileId);
        if(opt.isEmpty()){
            throw new NoSuchElementException();
        }
        CsvFiles file = opt.get();
        Resource resource = fileSystemAO.load(file.getStorePath());

        CsvExtractResult extract = extractorManager.extract(file.getType(), resource).orElseThrow(RuntimeException::new);
        return new CsvExtractResultDto(extract.getValidRecords().size(), extract.getInValidRecords().size());
    }

}
