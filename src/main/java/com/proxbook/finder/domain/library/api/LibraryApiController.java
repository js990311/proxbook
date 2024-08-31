package com.proxbook.finder.domain.library.api;

import com.proxbook.finder.domain.library.api.form.LibrarySearchOption;
import com.proxbook.finder.domain.library.api.form.ProxLibraryForm;
import com.proxbook.finder.domain.library.dto.LibraryBookPageDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryPageDto;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import com.proxbook.finder.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/library")
@RestController
public class LibraryApiController {
    private final LibraryService libraryService;
    private final UserProxLibraryService userProxLibraryService;

    @Operation(summary = "도서관을 이름으로 검색")
    @GetMapping
    public BaseResponse<LibraryPageDto> search(
            @Parameter(description = "검색어")
            @RequestParam("query") String query,
            @Parameter(description = "검색옵션", examples = {
                    @ExampleObject(name = "도서관 이름으로 검색", summary = "이름검색", value = "NAME"),
                    @ExampleObject(name = "도서관의 도로명 주소로 검색", summary = "주소검색", value = "ADDRESS"),
                    @ExampleObject(name = "도서관 이름과 주소 모두 사용하여 검색", summary = "이름 & 주소 검색", value = "BOTH")
            })
            @RequestParam("option") LibrarySearchOption option,
            @RequestParam(value = "page", defaultValue = "1") Integer page
            ){
        LibraryPageDto libraries = null;
        if(option.equals(LibrarySearchOption.NAME)){
            libraries = libraryService.readLibraryByLibraryName(query, page);
        }else if(option.equals(LibrarySearchOption.ADDRESS)){
            libraries = libraryService.readLibraryByAddress(query, page);
        }else if(option.equals(LibrarySearchOption.BOTH)){
            libraries = libraryService.readLibraryByLibraryNameOrAddress(query, page);
        }else {
            libraries = null;
        }
        return new BaseResponse<>(libraries);
    }

    @Operation(summary = "도서관 id로 도서관 검색")
    @GetMapping("/{id}")
    public BaseResponse<LibraryDto> findByLibraryId(
            @Parameter(description = "도서관 ID")
            @PathVariable("id") Long id){
        return new BaseResponse<>(libraryService.findByLibraryId(id));
    }

    @Operation(summary = "도서관 소장도서 검색")
    @GetMapping("/{id}/book")
    public BaseResponse<LibraryBookPageDto> findLibraryBookByLibraryId(
            @Parameter(description = "도서관 ID")
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ){
        LibraryBookPageDto libraryBook = libraryService.readLibraryBooksByLibraryId(id, page);
        return new BaseResponse<>(libraryBook);
    }
}
