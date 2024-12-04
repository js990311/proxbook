package com.proxbook.finder.csv.importer.file.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class CsvFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "csv_files_id")
    private Long id;

    private String originalFileName;
    private String fileId;
    private String fileExtension;

    @Enumerated(EnumType.STRING)
    private CsvEntityType type;

    public String getStorePath(){
        return fileId + "." + fileExtension;
    }

    private static String extractExtension(String filename){
        int dotIdx = filename.lastIndexOf('.');
        return filename.substring(dotIdx+1);
    }

    private static String createFileId(){
        return UUID.randomUUID().toString();
    }

    public CsvFiles(CsvEntityType type, String originalFileName) {
        this.type = type;
        this.originalFileName = originalFileName;
        this.fileExtension = extractExtension(originalFileName);
        this.fileId = createFileId();
    }

}
