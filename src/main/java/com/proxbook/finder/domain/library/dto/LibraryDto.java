package com.proxbook.finder.domain.library.dto;

import com.proxbook.finder.domain.library.entity.Library;
import lombok.Getter;

@Getter
public class LibraryDto {
    private String libraryCode;
    private String name;
    private String address;
    private String webpage;

    public LibraryDto(Library library){
        this.libraryCode = library.getId();
        this.name = library.getName();
        this.address = library.getAddress();
        this.webpage = library.getWebpage();
    }
}
