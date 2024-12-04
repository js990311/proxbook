package com.proxbook.finder.global.config;

import com.proxbook.finder.csv.importer.file.system.FileSystemAccessObject;
import com.proxbook.finder.csv.importer.file.system.impl.LocalFileSystemAccessObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileSystemAccessConfig {
    @Bean
    public FileSystemAccessObject fileSystemAccessObject(){
        return new LocalFileSystemAccessObject();
    }
}
