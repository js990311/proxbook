package com.proxbook.finder.csv.importer.file.dto;

import lombok.Getter;
import org.springframework.core.io.Resource;

@Getter
public class ResourceDto {
    private Resource resource;
    private String filename;
    private String header;

    public ResourceDto(Resource resource, String filename){
        this.resource = resource;
        this.filename = filename;
        this.header = "attachment;" + "filename=\"" + filename + "\"";
    }
}
