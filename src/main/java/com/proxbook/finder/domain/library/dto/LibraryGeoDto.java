package com.proxbook.finder.domain.library.dto;

import com.proxbook.finder.domain.library.entity.Library;
import lombok.Getter;

@Getter
public class LibraryGeoDto {
    private String libraryCode;
    private String name;
    private double latitude;
    private double longitude;

    public LibraryGeoDto(Library library){
        this.libraryCode = library.getId();
        this.name = library.getName();
        this.latitude = library.getLatitude();
        this.longitude = library.getLongitude();
    }
}
