package com.proxbook.finder.domain.proxlibrary.dto;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import lombok.Getter;

import java.util.List;

@Getter
public class ProxLibraryDto {
    private LibraryDto library;
    private Double distance;

    public ProxLibraryDto(ProxLibrary proxLibrary) {
        this.library = new LibraryDto(proxLibrary.getLibrary());
        this.distance = proxLibrary.getDistance();
    }
}
