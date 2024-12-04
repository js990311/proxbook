package com.proxbook.finder.csv.importer.file.dto;

import com.proxbook.finder.csv.importer.file.entity.CsvFiles;
import lombok.Getter;

@Getter
public class CsvFilesDto {
    private String originalFileName;
    private String storePath;

    public CsvFilesDto(String originalFileName, String storePath) {
        this.originalFileName = originalFileName;
        this.storePath = storePath;
    }

    public static CsvFilesDto from(CsvFiles files){
        return new CsvFilesDto(files.getOriginalFileName(), files.getStorePath());
    }

}
