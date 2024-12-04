package com.proxbook.finder.csv.importer.api;

import com.proxbook.finder.csv.importer.CsvEntityType;
import com.proxbook.finder.csv.importer.api.dto.CsvExtractResultDto;
import com.proxbook.finder.csv.importer.file.dto.CsvFilesDto;
import com.proxbook.finder.csv.importer.file.dto.ResourceDto;
import com.proxbook.finder.csv.importer.file.service.CsvFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/csv-files")
@RequiredArgsConstructor
@RestController
public class CsvImporterController {
    private final CsvFilesService csvFilesService;

    @PostMapping("/upload")
    public CsvFilesDto uploadFile(
            @RequestParam("type") CsvEntityType type,
            @RequestParam("file") MultipartFile file){
        CsvFilesDto filesDto = csvFilesService.saveFile(type,file);
        return filesDto;
    }

    @GetMapping("/{fildId}")
    public ResponseEntity<Resource> downloadByFilename(@PathVariable("fildId") String fildId){
        ResourceDto resource = csvFilesService.loadByFilename(fildId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, resource.getHeader())
                .body(resource.getResource());
    }

    @GetMapping("/{fileId}/extract")
    public CsvExtractResultDto extractCsvFile(@PathVariable("fileId") String fileId){
        return csvFilesService.extractCsv(fileId);
    }

}
