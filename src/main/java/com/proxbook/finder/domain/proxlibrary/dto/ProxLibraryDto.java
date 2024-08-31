package com.proxbook.finder.domain.proxlibrary.dto;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "내 주변 도서관 및 해당 도서관까지의 거리 객체")
@Getter
public class ProxLibraryDto {
    @Schema(description = "도서관")
    private LibraryDto library;
    @Schema(description = "거리")
    private Double distance;

    public ProxLibraryDto(ProxLibrary proxLibrary) {
        this.library = LibraryDto.from(proxLibrary.getLibrary());
        this.distance = proxLibrary.getDistance();
    }

}
