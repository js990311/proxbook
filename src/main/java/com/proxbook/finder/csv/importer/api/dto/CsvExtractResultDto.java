package com.proxbook.finder.csv.importer.api.dto;

import lombok.Getter;

@Getter
public class CsvExtractResultDto {
    private Integer validDatas;
    private Integer inValidDatas;

    public CsvExtractResultDto(Integer validDatas, Integer inValidDatas) {
        this.validDatas = validDatas;
        this.inValidDatas = inValidDatas;
    }
}
