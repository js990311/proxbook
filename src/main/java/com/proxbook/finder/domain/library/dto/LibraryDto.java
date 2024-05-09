package com.proxbook.finder.domain.library.dto;

import com.proxbook.finder.domain.library.entity.Library;
import lombok.Getter;

@Getter
public class LibraryDto {
    private String libraryCode;
    private String name;
    private String address;
    private String webpage;
    private Double latitude;
    private Double longitude;

    public LibraryDto(Library library){
        this.libraryCode = library.getId();
        this.name = library.getName();
        this.address = library.getAddress();
        this.webpage = library.getWebpage();
        this.latitude = library.getLatitude();
        this.longitude = library.getLongitude();
    }
}
